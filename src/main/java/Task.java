public class Task {
    protected String description;
    protected boolean isDone;

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

    public String toFile() { return (this.isDone ? 1 : 0) + " | " + this.description; }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}