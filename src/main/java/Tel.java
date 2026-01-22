import java.util.*;

public class Tel {
    public static void main(String[] args) {
        System.out.println(
                newLine() + "\n    Hello! I'm Tel.\n    What can I do for you?\n" + newLine()
        );

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Task> tasks = new ArrayList<>();
        do {
            try {
                if (input.startsWith("mark")) {
                    int index = validateNumber(tasks, input, 5);
                    doner(tasks, index, true);
                } else if (input.startsWith("unmark")) {
                    int index = validateNumber(tasks, input, 5);
                    doner(tasks, index, false);
                } else if (input.startsWith("delete")) {
                    int index = validateNumber(tasks, input, 7);
                    deleter(tasks, index);
                } else if (input.startsWith("todo")) {
                    Task task = new Todo(input.substring(5));
                    adder(tasks, task);
                } else if (input.startsWith("deadline")) {
                    try {
                        String[] params = validateSplit(input.substring(9), " /by ");
                        Task task = new Deadline(params[0], params[1]);
                        adder(tasks, task);
                    } catch (IllegalArgumentException i) {
                        prettyError("should have a /by separator!");
                    }
                } else if (input.startsWith("event")) {
                    try {
                        String[] params1 = validateSplit(input.substring(6), " /from ");
                        String[] params2 = validateSplit(params1[1], " /to ");
                        Task task = new Event(params1[0], params2[0], params2[1]);
                        adder(tasks, task);
                    } catch (IllegalArgumentException i) {
                        prettyError("should have a /from & a /to separator!");
                    }
                } else if (Objects.equals(input, "list")) {
                    getList(tasks);
                } else {
                    prettyError("should start with mark/unmark/todo/deadline/event!");
                }
            } catch (StringIndexOutOfBoundsException s) {
                prettyError("cannot be empty!");
            } catch (IllegalArgumentException i) {
                prettyError("needs a number from 1 to " + tasks.size());
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

    public static void deleter(List<Task> tasks, int index) {
        System.out.println(newLine());
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(newLine());
    }

    public static void doner(List<Task> tasks, int index, boolean bool) {
        tasks.get(index - 1).setStatusIcon(bool);
        if (bool) {
            System.out.println(newLine() + "\n    Nice! I've marked this task as done:");
        } else {
            System.out.println(newLine() + "\n    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + tasks.get(index - 1));
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

    public static int validateNumber(List<Task> tasks, String input, int start) throws IllegalArgumentException {
        int index = Integer.parseInt(input.substring(start));
        if (!(1 <= index && index <= tasks.size())) {
            throw new IllegalArgumentException();
        }
        return index;
    }

    public static String[] validateSplit(String input, String separator) throws IllegalArgumentException {
        String[] params = input.split(separator, -1);
        if (params.length == 2) {
            return params;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void prettyError(String message) {
        System.out.println(newLine());
        System.out.println("    Your input is invalid, it " + message);
        System.out.println(newLine());
    }
}