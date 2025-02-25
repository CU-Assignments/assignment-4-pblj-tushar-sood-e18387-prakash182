import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS || seats[seatNumber]) {
            return false; // Seat is either already booked or out of range
        }
        seats[seatNumber] = true;
        return true;
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String userType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String userType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.userType = userType;
        setPriority(priority);
    }

    @Override
    public void run() {
        synchronized (system) {
            if (system.bookSeat(seatNumber)) {
                System.out.println(userType + " booked seat " + seatNumber);
            } else {
                System.out.println(userType + " failed to book seat " + seatNumber + " (Already taken)");
            }
        }
    }
}

public class TicketBookingSystemMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<Thread> threads = new ArrayList<>();
        
        // Creating booking threads with VIP users having higher priority
        threads.add(new BookingThread(system, 2, "VIP User 1", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 2, "Regular User 1", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 5, "VIP User 2", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 5, "Regular User 2", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 7, "Regular User 3", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 7, "VIP User 3", Thread.MAX_PRIORITY));
        
        // Start all threads
        for (Thread t : threads) {
            t.start();
        }
        
        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
