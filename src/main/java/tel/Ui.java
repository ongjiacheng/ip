package tel;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Ensures input is valid and formats the output seen by the user.
 */
public class Ui {
    private static final String NEW_LINE = "    ____________________________________________________________";

    /**
     * Prints a single-line message with padding and decorative new lines.
     *
     * @param message Message to be decorated.
     */
    public static void prettyPrint(String message) {
        System.out.println(NEW_LINE);
        System.out.println("    " + message);
        System.out.println(NEW_LINE);
    }

    /**
     * Prints a multi-line message with padding and decorative new lines.
     *
     * @param message Message to be decorated.
     */
    public static void prettyPrint(String[] message) {
        System.out.println(NEW_LINE);
        for (String line : message) {
            System.out.println("    " + line);
        }
        System.out.println(NEW_LINE);
    }

    /**
     * Reads and returns the user input string if it is valid.
     *
     * @return Valid user input string.
     * @throws TelException If input is empty or not parseable.
     */
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

    /**
     * Displays a message verifying that a task has been added.
     * To be used after the TaskList.add() method when user requests addition.
     * Displays the latest task in the list of tasks.
     *
     * @param tasks A list of tasks.
     */
    public static void showAddMessage(TaskList tasks) {
        prettyPrint(new String[] {
            "Got it. I've added this task:",
            String.valueOf(tasks.get(tasks.size() - 1)),
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Displays a message verifying that a task has been deleted.
     * To be used before the TaskList.delete() method when user requests deletion.
     * Displays the task to be deleted in the list of tasks.
     *
     * @param task A task to be deleted.
     * @param tasks A list of tasks.
     */
    public static void showDeleteMessage(Task task, TaskList tasks) {
        prettyPrint(new String[] {
            "Noted. I've removed this task:",
            task.toString(),
            "Now you have " + (tasks.size() - 1) + " tasks in the list."
        });
    }

    /**
     * Displays an error message.
     * To be used with exceptions as a syntactic sugar for prettyPrint().
     *
     * @param message An error message.
     */
    public static void showError(String message) {
        prettyPrint(message);
    }

    /**
     * Displays a message with all tasks fulfilling the query.
     *
     * @param tasks A list of tasks.
     */
    public static void showFindMessage(TaskList tasks) {
        prettyPrint(new String[] {
            "Here are the matching tasks in your list",
            tasks.toString()
        });
    }

    /**
     * Display a message with all tasks.
     *
     * @param tasks A list of tasks.
     */
    public static void showListMessage(TaskList tasks) {
        prettyPrint(new String[] {
            "Here are the tasks in your list",
            tasks.toString()
        });
    }

    /**
     * Displays a message verifying that a task has been marked as done or undone.
     * To be used after TaskList.markDone().
     *
     * @param task A task which is marked or unmarked.
     */
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

    /**
     * Says a greeting to the user.
     */
    public static void showGreetingMessage() {
        prettyPrint(new String[]{"Hello! I'm Tel.", "What can I do for you?"});
    }

    /**
     * Says a farewell to the user.
     */
    public static void showFarewellMessage() {
        prettyPrint("Bye. Hope to see you again soon!");
    }
}
