import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MultithreadProblems {

    static List<String> stockSticker = Arrays.asList("NTAP", "FB", "GOOG", "TSLA", "LUV", "M");
    static List<Integer> stockPrices = Arrays.asList(35, 105, 780, 250, 42, 38);

    static void printList1() {
        //System.out.println(stockSticker);
        for (String s : stockSticker) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    static void printList2() {
        //System.out.println(stockPrices);
        for (int num : stockPrices) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // PROBLEM 1. Using multiple threads to print list 1 and list 2
    // -------------------------------------------------------------------------
    static void simpleMultithreading() {
        try {
            Thread th1 = new Thread(new Runnable() {
                public void run() {
                    printList1();
                }
            });
            th1.setName("th-list1");

            Thread th2 = new Thread(new Runnable() {
                public void run() {
                    printList2();
                }
            });
            th2.setName("th-list2");
            th1.start();
            th2.start();
            th1.join();
            th2.join();
        } catch (Exception e) {
            System.err.println("Exception at thread join");
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 2. Print list1 before printing list 2
    // Thread1 should start first and print everything in list1
    // Thread2 should start after thread1 and print everything in list2
    // https://stackoverflow.com/questions/289434/how-to-make-a-java-thread-wait-for-another-threads-output
    // -------------------------------------------------------------------------
    static void printOneListAfterAnother() {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            Thread th1 = new Thread(new Runnable() {
                public void run() {
                    printList1();
                    latch.countDown();
                }
            });
            th1.setName("th-list1");

            Thread th2 = new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                        printList2();
                    } catch (Exception e) {
                        System.err.println("Exception at CountDownLatch: " + e);
                    }
                }
            });
            th2.setName("th-list2");
            th1.start();
            th2.start();
            th1.join();
            th2.join();
        } catch (Exception e) {
            System.err.println("Exception at thread join");
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 3. Print first entry from stock symbol and then its price
    // https://stackoverflow.com/questions/2536692/a-simple-scenario-using-wait-and-notify-in-java
    // http://tutorials.jenkov.com/java-concurrency/thread-signaling.html
    // -------------------------------------------------------------------------
    boolean stickerSignal = true;
    boolean pricesSignal = false;
    synchronized void printEachStockSticker() {
        try {
            for (int i = 0; i < stockSticker.size(); i++) {
                while (!stickerSignal) {
                    wait();
                }
                System.out.print(stockSticker.get(i) + ":");
                pricesSignal = true;
                notify();
                stickerSignal = false;
            }
        } catch (Exception e) {
            System.err.println("Exception at thread wait");
        }
    }

    synchronized void printEachStockPrice() {
        try {
            for (int i = 0; i < stockPrices.size(); i++) {
                while (!pricesSignal) {
                    wait();
                }
                System.out.print(stockPrices.get(i) + "; ");
                stickerSignal = true;
                notify();
                pricesSignal = false;
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println("Exception at thread wait");
        }
    }

    void printEachStockStickerWithPrice() {
        try {
            Thread th1 = new Thread(new Runnable() {
                public void run() {
                    printEachStockSticker();
                }
            });

            Thread th2 = new Thread(new Runnable() {
                public void run() {
                    printEachStockPrice();
                }
            });

            th1.start();
            th2.start();
            th1.join();
            th2.join();
        } catch (Exception e) {
            System.err.println("Exception at thread join");
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 4. Producer Consumer Problem
    // https://stackoverflow.com/questions/2536692/a-simple-scenario-using-wait-and-notify-in-java
    // -------------------------------------------------------------------------
    static void producerConsumer() {
        try {
            SharedList sharedList = new SharedList(3);
            int iterationCount = 10;
            Thread th1 = new Thread(new Producer(sharedList, iterationCount));
            Thread th2 = new Thread(new Producer(sharedList, iterationCount));
            Thread th3 = new Thread(new Consumer(sharedList, iterationCount));
            Thread th4 = new Thread(new Consumer(sharedList, iterationCount));
            Thread th5 = new Thread(new Consumer(sharedList, iterationCount));
            th1.setName("pro1");
            th2.setName("pro2");
            th3.setName("con3");
            th4.setName("con4");
            th5.setName("con5");
            th1.start();
            th2.start();
            th3.start();
            th4.start();
            th5.start();
            th1.join();
            th2.join();
            th3.join();
            th4.join();
            th5.join();
        } catch (Exception e) {
            System.err.println("Producer Cosumer exception");
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 5. Producer Consumer Problem using blocking queue
    // https://howtodoinjava.com/core-java/multi-threading/producer-consumer-problem-using-blockingqueue/
    // -------------------------------------------------------------------------
    static void producerConsumerBQ() {
        try {
            BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(5);
            // ConsumerBQ con = new ConsumerBQ(bq);
            // ProducerBQ pro = new ProducerBQ(bq);
            int proThreadSize = 3;
            int conThreadSize = 5;
            Thread[] proThreads = new Thread[proThreadSize];
            Thread[] conThreads = new Thread[conThreadSize];
            for (int i = 0; i < proThreadSize; i++) {
                proThreads[i] = new Thread(new ProducerBQ(bq));
                proThreads[i].start();
            }
            for (int i = 0; i < conThreadSize; i++) {
                conThreads[i] = new Thread(new ConsumerBQ(bq));
                conThreads[i].start();
            }
            for (int i = 0; i < proThreadSize; i++) {
                proThreads[i].join();
            }
            for (int i = 0; i < conThreadSize; i++) {
                conThreads[i].join();
            }
        } catch (Exception e) {
        }
    }

    // -------------------------------------------------------------------------
    // PROBLEM 6. N words and M threads. Each thread should print a word
    // -------------------------------------------------------------------------
    static void multiThreadWordProcessing(List<String> words, int currentIndex, List<String> result) {
        if (currentIndex == words.size()) {
            return;
        }
        result.add(words.get(currentIndex));
        currentIndex++;
    }

    static void processWords(List<String> words, int numThreads) {
    }

    // -------------------------------------------------------------------------
    // Main Function
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        // PROBLEM 1. Using multiple threads to print list 1 and list 2
        {
            System.out.println("\nPROBLEM 1. Using multiple threads to print list 1 and list 2");
            simpleMultithreading();
        }

        // PROBLEM 2. Print list1 before printing list 2
        {
            System.out.println("\nPROBLEM 2. Print list1 before printing list 2");
            printOneListAfterAnother();
        }

        // PROBLEM 3. Print first entry from stock symbol and then its price
        {
            System.out.println("\nPROBLEM 3. Print first entry from stock symbol and then its price");
            MultithreadProblems mp = new MultithreadProblems();
            mp.printEachStockStickerWithPrice();
        }

        // PROBLEM 4. Producer Consumer problem
        {
            System.out.println("\nPROBLEM 4. Producer Consumer problem");
            producerConsumer();
        }

        // PROBLEM 5. Producer Consumer problem blocking queue
        {
            System.out.println("\nPROBLEM 5. Producer Consumer problem blocking queue");
            //producerConsumerBQ();
        }

        // PROBLEM 6. N words and M threads. Each thread should print a word
        {
            System.out.println("\nPROBLEM 6. Producer Consumer problem blocking queue");
            List<String> words = {"My", "name", "is", "pragad", ".", "This", "is", "my", "sample", "thread", "problem"};
            void processWords(words, 5);
        }
    }

}

/**
 * Producer Consumer example
 * https://stackoverflow.com/questions/27591043/producer-consumer-program-using-wait-and-notify-in-java
 */
class SharedList {
    private int capacity;
    private List<Integer> sharedList;

    public SharedList(int capacity) {
        this.capacity = capacity;
        sharedList = new ArrayList<>(capacity);
    }

    public List<Integer> getList() {
        return sharedList;
    }

    public int getCapacity() {
        return capacity;
    }
}

/**
 * Producer thread
 */
class Producer implements Runnable {

    private SharedList sl;
    private int iterationCount;

    public Producer(SharedList sl, int iterationCount) {
        this.sl = sl;
        this.iterationCount = iterationCount;
    }

    @Override
    public void run() {
            //for (int i = 0; i < iterationCount; i++) {
        while (true) {
            synchronized (sl) {
                while (sl.getList().size() == sl.getCapacity()) {
                    try {
                        System.out.println("Producer '" + Thread.currentThread().getName() + "' waiting...");
                        sl.wait();
                    } catch (Exception e) {
                    }
                }
                sl.getList().add(5);
                System.out.println("Produced: 5; thread: " + Thread.currentThread().getName());
                // Notify the consumer once we have added one item to the list
                sl.notify();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }
}

/**
 * Consumer thread
 */
class Consumer implements Runnable {

    private SharedList sl;
    private int iterationCount;

    public Consumer(SharedList sl, int iterationCount) {
        this.sl = sl;
        this.iterationCount = iterationCount;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sl) {
                // Wait if the list is empty
                while (sl.getList().isEmpty()) {
                    try {
                        System.out.println("Consumer '" + Thread.currentThread().getName() + "' waiting...");
                        sl.wait();
                    } catch (Exception e) {
                    }
                }
                // Remove the first element
                if (sl.getList().get(0) == -1) {
                    break;
                }
                System.out.println("Consumed: " + sl.getList().remove(0) + "; thread: " +  Thread.currentThread().getName());
                // Notify the producer once we have space left for one item
                sl.notify();
                try {
                    // Commenting the below line makes Consumer to wait
                    //Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }
}

/**
 * Consumer thread using Blocking Queue
 */
class ConsumerBQ implements Runnable {
    BlockingQueue<Integer> sharedBlockingQueue;

    public ConsumerBQ(BlockingQueue<Integer> bq) {
        this.sharedBlockingQueue = bq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sharedBlockingQueue.take();
                System.out.println("C size: " + sharedBlockingQueue.size());
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

/**
 * Producer thread using Blocking Queue
 */
class ProducerBQ implements Runnable {
    BlockingQueue<Integer> sharedBlockingQueue;

    public ProducerBQ(BlockingQueue<Integer> bq) {
        this.sharedBlockingQueue = bq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sharedBlockingQueue.add(1);
                System.out.println("P size: " + sharedBlockingQueue.size());
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

