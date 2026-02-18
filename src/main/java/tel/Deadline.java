package tel;

import java.time.LocalDateTime;

/**
 * A task that works like a deadline to an assignment.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Creates a deadline.
     *
     * @param description The event description.
     * @param deadline A datetime deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        assert deadline != null : "Deadline is empty!";
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline d) {
            return super.equals(o) && d.deadline == this.deadline;
        } else {
            return false;
        }
    }

    @Override
    public String toStorage() {
        return "D | " + super.toStorage() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
