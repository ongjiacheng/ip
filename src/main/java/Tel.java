import java.util.*;

public class Tel {
    public static void main(String[] args) {
        System.out.println(
                newLine() + "    Hello! I'm Tel.\n    What can I do for you?\n" + newLine()
        );

        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<Task> tasks = new ArrayList<>();
        do {
            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                doner(tasks, index, true);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                doner(tasks, index, true);
            } else if (input.startsWith("todo")) {
                Task task = new Todo(input.substring(5));
                adder(tasks, task);
            } else if (input.startsWith("deadline")) {
                List<String> params = List.of(input.substring(9).split("/"));
                Task task = new Deadline(params.get(0), params.get(1).substring(3));
                adder(tasks, task);
            } else if (input.startsWith("event")) {
                List<String> params = List.of(input.substring(6).split("/"));
                Task task = new Event(params.get(0), params.get(1).substring(5), params.get(2).substring(3));
                adder(tasks, task);
            } else if (Objects.equals(input, "list")) {
                getList(tasks);
            } else if (!Objects.equals(input, "")) {
                Task task = new Todo(input);
                adder(tasks, task);
            }
            input = scanner.nextLine();

        } while(!Objects.equals(input, "bye"));
        System.out.println(newLine() + "\n    Bye. Hope to see you again soon!\n" + newLine());
    }

    public static String newLine() {
        return "    ____________________________________________________________";
    }

    public static void adder(List<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println(newLine());
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1));
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(newLine());
    }

    public static void getList(List<Task> tasks) {
        System.out.println(newLine());
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i - 1));
        }
        System.out.println(newLine());
    }

    public static void doner(List<Task> tasks, int index, boolean bool) {
        if (bool) {
            tasks.get(index - 1).setStatusIcon(true);
            System.out.println(newLine() + "\n    Nice! I've marked this task as done:");
        } else {
            tasks.get(index - 1).setStatusIcon(false);
            System.out.println(newLine() + "\n    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + tasks.get(index - 1));
        System.out.println(newLine());
    }
}