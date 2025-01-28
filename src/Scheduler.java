import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Scheduler class acts as the server for communication between Fire Incident Subsystem and Drones
public class Scheduler implements Runnable {
    // Queue for tasks from the Fire Incident Subsystem and Drones
    private FireEvent fireEvent;
    private final BlockingQueue<FireEvent> droneResponseQueue = new LinkedBlockingQueue<>();
    private boolean hasTask = false;
    private boolean hasResponses = false;

    // Submit a fire event from the Fire Incident Subsystem -> Drone to scheduler
    public synchronized void submitFireEvent(FireEvent event) {
        try {
            if (hasTask) {
                wait();
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().isInterrupted();
        }

        fireEvent = event;
        hasTask = true;
        System.out.println("[Scheduler] received event");
        notifyAll();
    }

    // Allow a drone to request a task
    public synchronized FireEvent requestTask() {
        // Return a fire event for a drone
        return null;
    }

    // Submit a response from a drone back to the Scheduler
    public synchronized void submitDroneResponse(FireEvent event) {
        // Add drone response to the result queue
    }

    // Get task completion results for the Fire Incident Subsystem
    public synchronized FireEvent getDroneResponse() {
        // Return the result of a drone task
        return null;
    }

    @Override
    public void run() {
        System.out.println("Scheduler is running...");
        // Currently, it just manages the communication as a pass-through
        while (!Thread.currentThread().isInterrupted()) {
            try {
                wait();
            } catch (Exception e) {
                System.err.println("Error in Scheduler: " + e.getMessage());
            }
        }

        System.out.println("Scheduler has stopped.");
    }
}