import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// DroneSubsystem class representing a drone that interacts with the Scheduler
public class DroneSubsystem implements Runnable {

    private final Scheduler scheduler;

    public DroneSubsystem(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                FireEvent task = scheduler.requestTask();
                if (task != null) {
                    System.out.println("Drone received task: " + task);

                    Thread.sleep(2000); // Simulating work by sleeping

                    // Acknowledge task completion
                    scheduler.submitDroneResponse(task);
                    System.out.println("Drone completed task: " + task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                break; // Exit the loop on interruption
            }
        }
    }
}