import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
        System.out.println(Tel.newLine());
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1));
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(Tel.newLine());
    }

    public void delete(int index) {
        System.out.println(Tel.newLine());
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(Tel.newLine());
    }

    public void markDone(int index, boolean bool) {
        tasks.get(index - 1).setStatusIcon(bool);
        if (bool) {
            System.out.println(Tel.newLine() + "\n    Nice! I've marked this task as done:");
        } else {
            System.out.println(Tel.newLine() + "\n    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + tasks.get(index - 1));
        System.out.println(Tel.newLine());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(Tel.newLine() + "\n    Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            str.append("    ").append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        str.append(Tel.newLine()).append("\n");
        return str.toString();
    }
}