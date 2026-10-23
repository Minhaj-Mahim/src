import java.util.Random;

public class ViewingStand {

    boolean[] seats = new boolean[5];

    public synchronized int findSeat() {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == false) {
                seats[i] = true;
                return i;
            }
        }
        return -1;
    }

    public synchronized void leaveSeat(int seat) {
        seats[seat] = false;
    }

    public static void main(String[] args) {

        ViewingStand stand = new ViewingStand();

        for (int i = 1; i <= 10; i++) {
            int visitor = i;

            Thread t = new Thread(() -> {

                int seat = -1;

                while (seat == -1) {
                    seat = stand.findSeat();
                }

                System.out.println("Visitor " + visitor + " got seat " + (seat + 1));

                try {
                    Thread.sleep(500 + new Random().nextInt(1500));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                stand.leaveSeat(seat);

                System.out.println("Visitor " + visitor + " left seat " + (seat + 1));

            });

            t.start();
        }
    }
}
