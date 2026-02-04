package tel;

import java.time.LocalDateTime;

/**
 * A task that works like a deadline to an assignment.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a deadline.
     *
     * @param description The event description.
     * @param by A datetime deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
