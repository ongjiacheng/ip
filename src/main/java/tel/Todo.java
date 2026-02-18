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
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return super.equals(o);
        } else {
            return false;
        }
    }

    @Override
    public String toStorage() {
        return "T | " + super.toStorage();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
