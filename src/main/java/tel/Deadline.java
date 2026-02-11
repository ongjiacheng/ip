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
        assert by != null : "Deadline is empty!";
        this.by = by;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline d) {
            return super.equals(o) && d.by == this.by;
        } else {
            return false;
        }
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
