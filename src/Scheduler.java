import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Scheduler class acts as the server for communication between Fire Incident Subsystem and Drones
public class Scheduler implements Runnable {
    // Queue for tasks from the Fire Incident Subsystem and Drones
    private FireEvent fireEvent;
    private FireEvent responseEvent;
    private boolean hasTask = false;
    private boolean hasResponse = false;

    // Submit a fire event from the Fire Incident Subsystem -> Drone to scheduler
    public synchronized void submitFireEvent(FireEvent event) {
        try {
            if (hasTask) { // If a task already exists
                wait();
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }

        fireEvent = event;
        hasTask = true;
        System.out.println("[Scheduler] received event");
        notifyAll();
    }

    // Allow a drone to request a task
    public synchronized FireEvent requestTask() throws InterruptedException {
        if (!hasTask) { // Wait until a task is available
            wait();
        }
        FireEvent eventToSend = fireEvent;
        fireEvent = null;
        if (fireEvent == null) {
            hasTask = false;
        }
        System.out.println("[Scheduler] Drone retrieved task");
        notifyAll(); // Notify any other threads that might be waiting

        return eventToSend;
    }

    // Submit a response from a drone back to the Scheduler
    public synchronized void submitDroneResponse(FireEvent event) {
        try {
            if (hasResponse) {
                wait();
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }

        hasResponse = true;
        responseEvent = event;
        System.out.println("[Scheduler] received event");
        notifyAll();
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