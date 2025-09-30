package lab2;

class TablePrinter {
    // synchronized method ensures only one thread prints at a time
    public synchronized void printTable(int number, String threadName) {
        System.out.println(threadName + " started printing table of " + number);
        for (int i = 1; i <= 10; i++) {
            System.out.println(threadName + ": " + number + " x " + i + " = " + (number * i));
            try {
                Thread.sleep(200); // slow down for visibility
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName + " finished printing table of " + number);
    }
}

class TableThread extends Thread {
    TablePrinter printer;
    String threadName;
    int number;

    TableThread(TablePrinter printer, int number, String name) {
        this.printer = printer;
        this.number = number;
        this.threadName = name;
    }

    public void run() {
        printer.printTable(number, threadName);
    }
}

public class TableDemo {
    public static void main(String[] args) {
        TablePrinter printer = new TablePrinter();

        TableThread t1 = new TableThread(printer, 5, "Thread-1");
        TableThread t2 = new TableThread(printer, 5, "Thread-2");

        t1.start();
        t2.start();
    }
}