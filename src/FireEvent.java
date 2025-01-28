// FireEvent class to represent a fire incident event
public class FireEvent {

    private final String time;
    private final String zoneId;
    private final String eventType;
    private final int severity;

    public FireEvent(String time, String zoneId, String eventType, int severity) {
        this.time = time;
        this.zoneId = zoneId;
        this.eventType = eventType;
        this.severity = severity;
    }

    // Getters and Setters (if necessary)
}