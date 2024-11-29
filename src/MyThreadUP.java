import java.util.concurrent.Semaphore;

class MyThreadUp extends Thread {
    Semaphore semaphore;
    String threadName;

    public MyThreadUp(Semaphore s, String threadName) {
        super(threadName);
        this.semaphore = s;
        this.threadName = threadName;
    }

    @Override
    public void run() {

        System.out.println("Starting " + threadName);
        try {
            // First, get a permit.
            System.out.println(threadName + " is waiting for a permit.");

            // acquiring the lock
            semaphore.acquire();

            System.out.println(threadName + " gets a permit.");

            // Now, accessing the shared resource.
            // other waiting threads will wait, until this thread release the lock
            for (int i = 0; i < 5; i++) {
                Shared.count ++;
                System.out.println(threadName + ": " + Shared.count);

                // Now, allowing a context switch -- if possible.
                // for others threads to execute
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Release the permit.
        System.out.println(threadName + " releases the permit.");
        semaphore.release();
    }
}
