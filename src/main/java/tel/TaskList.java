package tel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A list containing tasks.
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds all tasks with substring in name
     *
     * @param key Substring to use.
     * @return List of tasks with substring.
     */
    public TaskList find(String key) {
        assert key != null : "Key is null!";
        TaskList result = new TaskList();
        tasks.stream()
                .filter(task -> task.contains(key))
                .forEach(result::add);
        return result;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds task to list of tasks.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task is null!";
        if (tasks.stream().noneMatch(task::equals)) {
            tasks.add(task);
        }
    }

    /**
     * Deletes task from list of tasks
     *
     * @param index Index of task to be deleted.
     */
    public void delete(int index) {
        assert index >= 1 && index <= tasks.size() : "Index out of range!";
        tasks.remove(index - 1);
    }

    /**
     * Marks task as done or undone.
     *
     * @param index Position of task in task list.
     * @param bool Doneness of task.
     */
    public void markDone(int index, boolean bool) {
        assert index >= 1 && index <= tasks.size() : "Index out of range!";
        Task task = tasks.get(index - 1);
        task.setStatusIcon(bool);
    }

    /**
     * Converts list of tasks to its file representation.
     *
     * @return String representation of tasks.
     */
    public String toStorage() {
        return tasks.stream()
                .map(Task::toStorage)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> "    " + (i + 1) + "." + tasks.get(i))
                .collect(Collectors.joining("\n"));
    }
}
