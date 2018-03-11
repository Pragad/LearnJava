import java.util.LinkedList;
 
class Producer implements Runnable {
    LinkedList<Integer> list;

    public Producer(LinkedList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (Exception e) {
        }
    }

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (list) {
                // producer thread waits while list is full
                while (list.size()==2)
                    list.wait();

                System.out.println(Thread.currentThread().getName() + "; Producer produced-" + value);

                // to insert the jobs in the list
                list.add(value++);

                // notifies the consumer thread that now it can start consuming
                list.notify();

                // makes the working of program easier
                // to  understand
                Thread.sleep(1000);
            }
        }
    }
}

class Consumer implements Runnable {
    LinkedList<Integer> list;

    public Consumer(LinkedList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (Exception e) {
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (list) {
                // consumer thread waits while list
                // is empty
                while (list.size()==0)
                    list.wait();

                //to retrive the ifrst job in the list
                int val = list.removeFirst();

                System.out.println(Thread.currentThread().getName() + "; Consumer consumed-" + val);

                // Wake up producer thread
                list.notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }
}

public class Threadexample
{
    public static void main(String[] args)
                        throws InterruptedException
    {
        LinkedList<Integer> list = new LinkedList<>();
        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Producer(list));
        Thread t3 = new Thread(new Consumer(list));
        Thread t4 = new Thread(new Consumer(list));
        Thread t5 = new Thread(new Consumer(list));

        t1.setName("p1");
        t2.setName("p2");
        t3.setName("c1");
        t4.setName("c2");
        t5.setName("c3");

        // Start both threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
 
        // t1 finishes before t2
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}
