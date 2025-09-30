package lab2;

class TicketBooking {
    private int ticketsAvailable = 5;

    // synchronized to prevent multiple threads booking the same ticket
    public synchronized void bookTicket(String user) {
        System.out.println(user + " is trying to book a ticket...");
        if (ticketsAvailable > 0) {
            try {
                Thread.sleep(500); // simulate processing delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ticketsAvailable--;
            System.out.println(user + " successfully booked a ticket. Tickets left: " + ticketsAvailable);
        } else {
            System.out.println("Sorry " + user + ", tickets are sold out!");
        }
    }
}

class UserThread extends Thread {
    TicketBooking booking;
    String user;

    UserThread(TicketBooking booking, String user) {
        this.booking = booking;
        this.user = user;
    }

    public void run() {
        booking.bookTicket(user);
    }
}

public class TicketDemo {
    public static void main(String[] args) {
        TicketBooking booking = new TicketBooking();

        // create multiple user threads
        UserThread u1 = new UserThread(booking, "User-1");
        UserThread u2 = new UserThread(booking, "User-2");
        UserThread u3 = new UserThread(booking, "User-3");
        UserThread u4 = new UserThread(booking, "User-4");
        UserThread u5 = new UserThread(booking, "User-5");
        UserThread u6 = new UserThread(booking, "User-6"); // extra to test sold out

        // start all threads
        u1.start();
        u2.start();
        u3.start();
        u4.start();
        u5.start();
        u6.start();
    }
}
