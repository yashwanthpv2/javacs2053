package lab2;

class MessageSender {
    // synchronized ensures one thread sends the full message at a time
    public synchronized void sendMessage(String msg) {
        System.out.println(Thread.currentThread().getName() + " started sending message.");
        for (int i = 0; i < msg.length(); i++) {
            System.out.print(msg.charAt(i));
            try {
                Thread.sleep(100); // small delay for visibility
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); // new line after message
        System.out.println(Thread.currentThread().getName() + " finished sending message.");
    }
}

class SenderThread extends Thread {
    MessageSender sender;
    String message;

    SenderThread(MessageSender sender, String msg) {
        this.sender = sender;
        this.message = msg;
    }

    public void run() {
        sender.sendMessage(message);
    }
}

public class MessageDemo {
    public static void main(String[] args) {
        MessageSender sender = new MessageSender();

        SenderThread t1 = new SenderThread(sender, "Hello from Thread-1!");
        SenderThread t2 = new SenderThread(sender, "Hi there from Thread-2!");

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
    }
}
