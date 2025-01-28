// FireIncidentSubsystem reads input events and communicates with the Scheduler
public class FireIncidentSubsystem implements Runnable {

    private final Scheduler scheduler;

    // Constructor
    public FireIncidentSubsystem(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        // Logic for reading events and sending them to the Scheduler
    }
}