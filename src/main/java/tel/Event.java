package tel;

import java.time.LocalDateTime;

/**
 * A task that works like an event in a calendar.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates an event.
     *
     * @param description The event description.
     * @param start The starting datetime.
     * @param end The ending datetime.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        assert start != null && end != null && start.isBefore(end) : "End date must be after start date!";
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event e) {
            return super.equals(o) && e.start == this.start && e.end == this.end;
        } else {
            return false;
        }
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
