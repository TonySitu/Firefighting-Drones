public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();

        Thread schedulerThread = new Thread(scheduler);
        Thread fireIncidentThread = new Thread(new FireIncidentSubsystem(scheduler));
        Thread droneThread = new Thread(new DroneSubsystem(scheduler));

        schedulerThread.start();
        fireIncidentThread.start();
        droneThread.start();

        try {
            fireIncidentThread.join();
            droneThread.interrupt();
            schedulerThread.interrupt();
            droneThread.join();
            schedulerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("System shut down.");
    }
}