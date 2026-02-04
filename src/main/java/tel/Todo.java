package tel;

/**
 * A task that works like an entry in a to-do list.
 */
public class Todo extends Task {

    /**
     * Creates a to-do item.
     *
     * @param description The item description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
