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
            Thread th2 = new Thread(new Consumer(sharedList, iterationCount));
            Thread th3 = new Thread(new Consumer(sharedList, iterationCount));
            th1.setName("th1");
            th2.setName("th2");
            th3.setName("th3");
            th1.start();
            th2.start();
            th3.start();
            th1.join();
            th2.join();
            th3.join();
        } catch (Exception e) {
            System.err.println("Producer Cosumer exception");
        }
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
        for (int i = 0; i < iterationCount; i++) {
            System.out.println("Producer '" + Thread.currentThread().getName()  + "' iteration i: " + i);
            synchronized (sl) {
                while (sl.getList().size() == sl.getCapacity()) {
                    try {
                        System.out.println("Producer '" + Thread.currentThread().getName() + "' waiting...");
                        sl.wait();
                    } catch (Exception e) {
                    }
                }
                sl.getList().add(i);
                System.out.println("Produced: " + i + "; thread: " + Thread.currentThread().getName());
                // Notify the consumer once we have added one item to the list
                if (sl.getList().size() == 1) {
                    sl.notifyAll();
                }
                try {
                    //System.out.println("Producer sleeping for a second");
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
        for (int i = 0; i < iterationCount; i++) {
            System.out.println("Consumer '" + Thread.currentThread().getName()  + "' iteration i: " + i);
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
                System.out.println("Consumed: " + sl.getList().remove(0) + "; thread: " +  Thread.currentThread().getName());
                // Notify the producer once we have space left for one item
                if (sl.getList().size() == sl.getCapacity() - 1) {
                    sl.notifyAll();
                }
                try {
                    //System.out.println("Consumer sleeping for a second");
                    // Commenting the below line makes Consumer to wait
                    //Thread.sleep(500);
                } catch (Exception e) {
                }

            }
        }
    }
}

