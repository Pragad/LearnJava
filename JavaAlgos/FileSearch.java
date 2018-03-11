package com.scalyr.s3search;

import com.scalyr.s3search.s3simulation.SimulatedS3Client;
import com.scalyr.s3search.s3simulation.SimulatedS3Client.FlakyNetworkException;
import com.scalyr.s3search.textsearch.TextSearcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A naive single-threaded implementation of searching for a string in multiple S3 objects.
 *
 * A partial list of the things it does not do:
 *
 * - Measure execution time
 * - Handle network exceptions
 * - Use threads or tasks
 *
 */
public class Main {
    public static final double NANOS_PER_MS = 1_000_000;

    public static void main(String[] args) throws FlakyNetworkException {
        String searchTerm = args.length > 0 ? args[0] : "pewter";

        SimulatedS3Client s3Client = new SimulatedS3Client();

        int result = countMatchesInEpochs(0, 100, searchTerm, s3Client);

        System.out.format("%d matches found for '%s' and variants%n", result, searchTerm);
    }

    static class SearchWorker implements Callable<Integer> {
        private BlockingQueue<String> sharedQueue;
        private SimulatedS3Client s3Client;
        private TextSearcher searcher;

        public SearchWorker(BlockingQueue<String> sharedQueue, SimulatedS3Client s3Client, TextSearcher searcher) {
            this.sharedQueue = sharedQueue;
            this.searcher = searcher;
            this.s3Client = s3Client;
        }

        @Override
        public Integer call() {
            String file = "";
            while(true){
                try {
                    file = sharedQueue.take();
                    byte[] epochData = s3Client.readFileFromS3("s3SimulationFiles", file);
                    return searcher.countMatchesInBlob(epochData, 0, epochData.length);
                } catch (FlakyNetworkException e) {
                    e.printStackTrace();
                    sharedQueue.add(file);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int countMatchesInEpochs(int startEpoch, int endEpoch, String searchString,
            SimulatedS3Client s3Client) throws FlakyNetworkException {
        long startTime = System.nanoTime();
        TextSearcher searcher = new TextSearcher(searchString);

        int THREAD_POOL_SIZE = 3;
        int BLOCKING_QUEUE_SIZE = 100;
        BlockingQueue<String> sharedQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_SIZE);
        ExecutorService execService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int epochIndex = startEpoch; epochIndex < endEpoch; epochIndex++) {
            sharedQueue.add("epoch_" + epochIndex);
        }

        for (int i = 0; i < BLOCKING_QUEUE_SIZE; i++) {
            futures.add(execService.submit(new SearchWorker(sharedQueue, s3Client, searcher)));
        }

        int total = 0;
        for (Future<Integer> future : futures) {
            try {
                total += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total count: " + total);
        execService.shutdown();
        long endTime = System.nanoTime();
        System.out.println("Execution Time=" + (endTime - startTime) / NANOS_PER_MS);
        return total;
    }
}

// File Search optimized version
package com.scalyr.s3search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.scalyr.s3search.s3simulation.SimulatedS3Client;
import com.scalyr.s3search.s3simulation.SimulatedS3Client.FlakyNetworkException;
import com.scalyr.s3search.textsearch.TextSearcher;

/**
 * A naive single-threaded implementation of searching for a string in multiple S3 objects.
 *
 * A partial list of the things it does not do:
 *
 * - Measure execution time
 * - Handle network exceptions
 * - Use threads or tasks
 *
 */
public class Main {

    public static final double NANOS_PER_MS = 1_000_000;

    // From testing found that the optimal value for network fetch pool size is between 10 and 25
    // The optimal value for file search pool size is 8
    private static int NETWORK_FETCH_POOL_SIZE = 10;
    private static int FILE_SEARCH_POOL_SIZE = 8;

    public static void main(String[] args) {
        String searchTerm = args.length > 0 ? args[0] : "pewter";

        SimulatedS3Client s3Client = new SimulatedS3Client();
        int result = countMatchesInEpochs(0, 100, searchTerm, s3Client);
        System.out.format("%d matches found for '%s' and variants%n", result, searchTerm);

        // The below line take care of trying out various values for the pool size. This helps in finding
        // the optimal number of threads
        // findBestPoolSize(0, 100, searchTerm, s3Client);
    }

    /**
     * FileFetchWorker class take care of fetching a blob of text from S3 bucket
     * After fetching a blob of text, an executor service is submitted to perform string search
     * @author pragad
     */
    static class FileFetchWorker implements Callable <Future<Integer>> {
        private SimulatedS3Client s3Client;
        private String fileName;
        private ExecutorService fileSearchExecService;
        private TextSearcher textSearcher;

        FileFetchWorker(SimulatedS3Client s3Client, String fileName, ExecutorService fileSearchExecService, TextSearcher textSearcher) {
            this.s3Client = s3Client;
            this.fileName = fileName;
            this.fileSearchExecService = fileSearchExecService;
            this.textSearcher = textSearcher;
        }

        @Override
        public Future<Integer> call() throws Exception {
            // TODO: Give-up after specific number of retry attempts. Sleep for sometime before attempting a retry
            while (true) {
                try {
                    byte[] fileBlob = s3Client.readFileFromS3("s3SimulationFiles", fileName);
                    Callable<Integer> fileSearchCallable = new FileSearchWorker(textSearcher, fileBlob);
                    return fileSearchExecService.submit(fileSearchCallable);
                } catch (FlakyNetworkException e) {
                    System.err.println("Caught flaky network exception=" + e.getStackTrace());
                    continue;
                }
            }
        }
    }

    /**
     * FileSearchWorker take care of searching a string in a blob of bytes
     * @author pragad
     */
    static class FileSearchWorker implements Callable <Integer> {
        private byte[] searchBlob;
        private TextSearcher textSearcher;

        public FileSearchWorker(TextSearcher textSearcher, byte[] searchBlob) {
            this.textSearcher = textSearcher;
            this.searchBlob = searchBlob;
        }

        @Override
        public Integer call() throws Exception {
            return textSearcher.countMatchesInBlob(searchBlob, 0, searchBlob.length);
        }
    }

    /**
     * Return the number of matches for searchString in each of a series of "epochs" stored in Amazon S3.
     *
     * @param startEpoch Index of the first epoch to search (inclusive).
     * @param endEpoch Index just after the last epoch to search (exclusive).
     * @param searchString String to search for.
     * @param s3Client Accessor used to read the files to search.
     * @return The count of matches across all epochs.
     */
    // The below line is used to find the optimal pool size by passing it as an argument
    // public static int countMatchesInEpochs(int startEpoch, int endEpoch, String searchString, SimulatedS3Client s3Client, int netPoolSize, int filePoolSize) {
    public static int countMatchesInEpochs(int startEpoch, int endEpoch, String searchString, SimulatedS3Client s3Client) {
        long startTime = System.nanoTime();
        TextSearcher searcher = new TextSearcher(searchString);
        ExecutorService fetchExecService = Executors.newFixedThreadPool(NETWORK_FETCH_POOL_SIZE);
        ExecutorService fileSearchExecService = Executors.newFixedThreadPool(FILE_SEARCH_POOL_SIZE);
        List<Future<Future<Integer>>> fileSearchFutures = new ArrayList<>();

        // Go over each file and accumulate the word count
        for (int epochIndex = startEpoch; epochIndex < endEpoch; epochIndex++) {
            Callable<Future<Integer>> fileFetchCallable = new FileFetchWorker(s3Client, "epoch_" + epochIndex, fileSearchExecService, searcher);
            fileSearchFutures.add(fetchExecService.submit(fileFetchCallable));
        }

        // Get the total word count from the future object that we got
        int totalWordCount = 0;
        for (Future<Future<Integer>> future : fileSearchFutures) {
            try {
                totalWordCount += future.get().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fetchExecService.shutdown();
        fileSearchExecService.shutdown();
        long endTime = System.nanoTime();
        System.out.println("Execution Time=" + (endTime - startTime) / NANOS_PER_MS);

        return totalWordCount;
    }

    /**
     *
     * This is a utility function to find the optimal values for the pool size by trying out various values
     * @param startEpoch Index of the first epoch to search (inclusive).
     * @param endEpoch Index just after the last epoch to search (exclusive).
     * @param searchString String to search for.
     * @param s3Client Accessor used to read the files to searcht
     */
    private static void findBestPoolSize(int startEpoch, int endEpoch, String searchString, SimulatedS3Client s3Client) {
        for (int networkFetchPoolIndex = 25; networkFetchPoolIndex < NETWORK_FETCH_POOL_SIZE; networkFetchPoolIndex++) {
            // Narrowed down file pool size to 8 as that is an optimal value
            for (int fileSearchPoolIndex = 8; fileSearchPoolIndex < 9; fileSearchPoolIndex++) {
                long startTime = System.nanoTime();
                // The signature of the method has changed. So commenting out below line
                // countMatchesInEpochs(startEpoch, endEpoch, searchString, s3Client, networkFetchPoolIndex, fileSearchPoolIndex);
                long endTime = System.nanoTime();
                System.out.println("Network Pool Size=" + networkFetchPoolIndex + "; File Pool Size=" +
                                   fileSearchPoolIndex + "; Execution Time=" + (endTime - startTime) / NANOS_PER_MS);

            }
        }
    }
}

