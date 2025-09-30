package lab2;


class BankAccount {
    private int balance = 1000;

    // withdraw without sync would cause race condition
    public synchronized void withdraw(String person, int amount) {
        System.out.println(person + " is trying to withdraw: " + amount);

        if (balance >= amount) {
            System.out.println(person + " proceeding with withdrawal...");
            try { Thread.sleep(1000); } // simulate delay
            catch (InterruptedException e) { e.printStackTrace(); }

            balance -= amount;
            System.out.println(person + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println("Sorry " + person + ", insufficient balance. Current: " + balance);
        }
    }
}

class WithdrawThread extends Thread {
    BankAccount account;
    String person;
    int amount;

    WithdrawThread(BankAccount acc, String name, int amt) {
        account = acc;
        person = name;
        amount = amt;
    }

    public void run() {
        account.withdraw(person, amount);
    }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        WithdrawThread t1 = new WithdrawThread(account, "Person-1", 700);
        WithdrawThread t2 = new WithdrawThread(account, "Person-2", 500);

        t1.start();
        t2.start();
    }
}