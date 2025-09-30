package lab2;

class Counter {
    private int count = 0;

    // synchronized to make increment thread-safe
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    Counter counter;
    int times;

    CounterThread(Counter counter, int times) {
        this.counter = counter;
        this.times = times;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            counter.increment();
        }
    }
}

public class CounterDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();

        CounterThread t1 = new CounterThread(counter, 1000);
        CounterThread t2 = new CounterThread(counter, 1000);

        t1.start();
        t2.start();

        try {
            t1.join(); // wait for threads to finish
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}
