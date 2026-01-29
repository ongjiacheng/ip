package tel;

/**
 * A task which is extensible.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public char getStatusIcon() {
        return (isDone ? 'X' : ' ');
    }

    public void setStatusIcon(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Writes the task description in a file-readable format.
     *
     * @return The string containing the task attributes.
     */
    public String toFile() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}