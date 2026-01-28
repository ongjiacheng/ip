import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public String add(Task task) {
        this.tasks.add(task);
        return Tel.newLine() + "\n    Got it. I've added this task:\n      " + tasks.get(tasks.size() - 1) +
                "\n    Now you have " + tasks.size() + " tasks in the list.\n" + Tel.newLine();
    }

    public String delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return Tel.newLine() + "\n    Noted. I've removed this task:\n      " + task +
                "\n    Now you have " + (tasks.size() - 1) + " tasks in the list.\n" + Tel.newLine();
    }

    public String markDone(int index, boolean bool) {
        tasks.get(index - 1).setStatusIcon(bool);
        String string;
        if (bool) {
            string = Tel.newLine() + "\n    Nice! I've marked this task as done:";
        } else {
            string = Tel.newLine() + "\n    OK, I've marked this task as not done yet:";
        }
        string += "\n      " + tasks.get(index - 1) + "\n" + Tel.newLine();
        return string;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(Tel.newLine()).append("\n    Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            str.append("    ").append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        str.append(Tel.newLine());
        return str.toString();
    }
}