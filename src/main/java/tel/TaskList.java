package tel;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public int size() {
        return this.tasks.size();
    }

    public TaskList find(String key) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.contains(key)) {
                result.add(task);
            }
        }
        return result;
    }

    public Task get(int index) {
        return this.tasks.get(index);
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

    public String toFile() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.toFile()).append("\n");
        }
        return str.toString().stripTrailing();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            str.append("    ").append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        return str.toString().strip();
    }
}