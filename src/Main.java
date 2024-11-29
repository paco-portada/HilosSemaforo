import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // creating a Semaphore object
        // with number of permits 1
        Semaphore semaphore = new Semaphore(1);

        // creating two threads with name A and B
        // Note that thread A will increment the count
        // and thread B will decrement the count
        MyThreadUp mt1 = new MyThreadUp(semaphore, "Up");
        MyThreadDown mt2 = new MyThreadDown(semaphore, "Down");

        // stating threads A and B
        mt1.start();
        mt2.start();

        // waiting for threads A and B
        try {
            mt1.join();
            mt2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // count will always remain 0 after
        // both threads will complete their execution
        System.out.println("count: " + Shared.count);
    }
}