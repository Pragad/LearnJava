import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreading {
    private static final long NANO_SECS_IN_MILLIS = 1000000;

    private static BlockingQueue<Integer> sharedQueue;
    private static AtomicInteger atomicCount;

    public static class WordLengthCallable implements Callable<Integer> {
        private String word;
        public WordLengthCallable(String word) {
            this.word = word;
        }
        @Override
        public Integer call() {
            return Integer.valueOf(word.length());
        }
    }

    static class Consumer implements Callable<Integer> {
        private BlockingQueue<Integer> sharedQueue;

        public Consumer(BlockingQueue<Integer> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public Integer call() {
            while(true){
                try {
                    return sharedQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer2 implements Runnable {
        @Override
        public void run() {
            try {
                //System.out.println("Consumer 2 Run");
                atomicCount.addAndGet(sharedQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Consumer 2 Done");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Multi threading problems");
        {
            ExecutorService pool = Executors.newFixedThreadPool(30);
            Set<Future<Integer>> set = new HashSet<>();
            List<String> list = new ArrayList<>();
            list.add("as");
            list.add("qwer");
            list.add("asdf");
            list.add("zxc");
            list.add("poiut");
            list.add("a");
            for (String word: list) {
                Callable<Integer> callable = new WordLengthCallable(word);
                Future<Integer> future = pool.submit(callable);
                set.add(future);
            }
            int sum = 0;
            for (Future<Integer> future : set) {
                sum += future.get();
            }
            System.out.printf("The sum of lengths is %s%n", sum);
            pool.shutdown();
        }

        {
            long startTime = System.nanoTime();
            int THREAD_POOL_SIZE = 2;
            int BLOCKING_QUEUE_SIZE = 100;
            BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_SIZE);
            ExecutorService execService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            List<Future<Integer>> futures = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                sharedQueue.add(i);
            }

            for (int i = 0; i < BLOCKING_QUEUE_SIZE; i++) {
                futures.add(execService.submit(new Consumer(sharedQueue)));
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
            System.out.println("Execution Time=" + (endTime - startTime) / NANO_SECS_IN_MILLIS);
        }

        /*
        {
            int THREAD_POOL_SIZE = 10;
            ExecutorService execService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            int BLOCKING_QUEUE_SIZE = 100;
            sharedQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_SIZE);
            atomicCount = new AtomicInteger(0);
            for (int i = 0; i < 100; i++) {
                sharedQueue.add(i);
            }

            for (int i = 0; i < BLOCKING_QUEUE_SIZE; i++) {
                execService.submit(new Consumer2());
            }
            execService.shutdown();
            System.out.println("Atomic count: " + atomicCount.get());
        }
        */
    }
}