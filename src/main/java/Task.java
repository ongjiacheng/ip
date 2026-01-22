public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatusIcon(boolean bool) {
        this.isDone = bool;
    }

    public String getListItem(int index) {
        return "    " + index + ". [" + this.getStatusIcon() + "] " + this.getDescription();
    }
}