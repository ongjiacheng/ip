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
        assert description != null : "Description is null!";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Check if substring is within the task description.
     *
     * @param key The substring to check.
     */
    public boolean contains(String key) {
        assert description != null : "Description is null!";
        return description.contains(key);
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
        assert description != null : "Description is null!";
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task t) {
            return t.description.equals(this.description) && t.isDone == this.isDone;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        assert description != null : "Description is null!";
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
