// FireEvent class to represent a fire incident event
public record FireEvent(String time, String zoneId, String eventType, int severity) {

    @Override
    public String toString() {
        return String.format("Time: %s, Zone: %s, Event: %s, Severity: %d",
                time, zoneId, eventType, severity);
    }
}