package tel;

import java.time.LocalDateTime;

/**
 * A task that works like an event in a calendar.
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates an event.
     *
     * @param description The event description.
     * @param startTime The starting datetime.
     * @param endTime The ending datetime.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        assert startTime != null && endTime != null && startTime.isBefore(endTime) : "End must be after start date!";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event e) {
            return super.equals(o) && e.startTime == this.startTime && e.endTime == this.endTime;
        } else {
            return false;
        }
    }

    @Override
    public String toStorage() {
        return "E | " + super.toStorage() + " | " + this.startTime + " | " + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
