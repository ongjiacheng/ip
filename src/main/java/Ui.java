import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Ui {
    private static final String NEW_LINE = "    ____________________________________________________________";

    public static void prettyPrint(String message) {
        System.out.println(NEW_LINE);
        System.out.println("    " + message);
        System.out.println(NEW_LINE);
    }

    public static void prettyPrint(String[] message) {
        System.out.println(NEW_LINE);
        for (String line : message) {
            System.out.println("    " + line);
        }
        System.out.println(NEW_LINE);
    }

    public static String readCommand() throws TelException {
        String input;
        try {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new TelException("Input should start with mark/unmark/todo/deadline/event!");
        }
        return input;
    }

    public static void showAddMessage(TaskList tasks) {
        prettyPrint(new String[] {
                "Got it. I've added this task:",
                String.valueOf(tasks.get(tasks.size() - 1)),
                "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    public static void showDeleteMessage(Task task, TaskList tasks) {
        prettyPrint(new String[] {
                "Noted. I've removed this task:",
                task.toString(),
                "Now you have " + (tasks.size() - 1) + " tasks in the list."
        });
    }

    public static void showError(String message) {
        prettyPrint(message);
    }

    public static void showMarkMessage(Task task) {
        String[] message = new String[2];
        if (Objects.equals(task.getStatusIcon(), 'X')) {
            message[0] = "Nice! I've marked this task as done:";
        } else {
            message[0] = "OK, I've marked this task as not done yet:";
        }
        message[1] = task.toString();
        prettyPrint(message);
    }

    public static void showGreetingMessage() {
        prettyPrint(new String[]{"Hello! I'm Tel.", "What can I do for you?"});
    }

    public static void showFarewellMessage() {
        prettyPrint("Bye. Hope to see you again soon!");
    }
}
