import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index - 1);
    }

    public void markDone(int index, boolean bool) {
        Task task = tasks.get(index - 1);
        task.setStatusIcon(bool);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            str.append("    ").append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        return str.toString().stripTrailing();
    }
}