
public class DroneSubsystem implements Runnable {

    private final Scheduler scheduler;

    public DroneSubsystem(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("[Drone] Requesting task");
                FireEvent task = scheduler.requestTask();
                if (task != null) {
                    System.out.println("[Drone] received task");

                    Thread.sleep(2000); // Simulating work by sleeping

                    // Acknowledge task completion
                    scheduler.submitDroneResponse(task);
                    System.out.println("[Drone] completed task");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                break; // Exit the loop on interruption
            }
        }
    }
}