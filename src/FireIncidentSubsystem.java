import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FireIncidentSubsystem implements Runnable {

    private final Scheduler scheduler;

    public FireIncidentSubsystem(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()); {
            try (BufferedReader reader = new BufferedReader(new FileReader("fire_events.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        FireEvent event = new FireEvent(parts[0].trim(), parts[1].trim(), parts[2].trim(), Integer.parseInt(parts[3].trim()));
                        scheduler.submitFireEvent(event);
                        System.out.println("[FireIncidentSubsystem] submitted fire event");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FireEvent response = scheduler.getDroneResponse();
                if (response != null) {
                    System.out.println("Fire Incident Subsystem received response: " + response);
                }
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
        }
    }
}