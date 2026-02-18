package tel;

import java.util.Objects;

/**
 * Ensures input is valid and formats the output seen by the user.
 */
public class Ui {
    private static final String NEW_LINE = "______________________________";

    /**
     * Prints a single-line message with padding and decorative new lines.
     *
     * @param message Message to be decorated.
     * @return Padded message.
     */
    public static String prettyPrint(String message) {
        return NEW_LINE + "\n" + message + "\n" + NEW_LINE;
    }

    /**
     * Prints a multi-line message with padding and decorative new lines.
     *
     * @param message Message to be decorated.
     * @return Padded message.
     */
    public static String prettyPrint(String[] message) {
        StringBuilder sb = new StringBuilder(NEW_LINE).append("\n");
        for (String line : message) {
            sb.append(line).append("\n");
        }
        sb.append(NEW_LINE).append("\n");
        return sb.toString();
    }

    /**
     * Displays a message verifying that a task has been added.
     * To be used after the TaskList.add() method when user requests addition.
     * Displays the latest task in the list of tasks.
     *
     * @param tasks A list of tasks.
     * @return Formatted message.
     */
    public static String showAddMessage(TaskList tasks) {
        return prettyPrint(new String[] {
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
     * @return Formatted message.
     */
    public static String showDeleteMessage(Task task, TaskList tasks) {
        return prettyPrint(new String[] {
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
     * @return Formatted message.
     */
    public static String showError(String message) {
        return prettyPrint(message);
    }

    /**
     * Displays a message with all tasks fulfilling the query.
     *
     * @param tasks A list of tasks.
     * @return Formatted message.
     */
    public static String showFindMessage(TaskList tasks) {
        return prettyPrint(new String[] {
            "Here are the matching tasks in your list",
            tasks.toString()
        });
    }

    /**
     * Display a message with all tasks.
     *
     * @param tasks A list of tasks.
     * @return Formatted message.
     */
    public static String showListMessage(TaskList tasks) {
        return prettyPrint(new String[] {
            "Here are the tasks in your list",
            tasks.toString()
        });
    }

    /**
     * Displays a message verifying that a task has been marked as done or undone.
     * To be used after TaskList.markDone().
     *
     * @param task A task which is marked or unmarked.
     * @return Formatted message.
     */
    public static String showMarkMessage(Task task) {
        String[] message = new String[2];
        if (Objects.equals(task.getStatusIcon(), 'X')) {
            message[0] = "Nice! I've marked this task as done:";
        } else {
            message[0] = "OK, I've marked this task as not done yet:";
        }
        message[1] = task.toString();
        return prettyPrint(message);
    }

    /**
     * Says a greeting to the user.
     *
     * @return Formatted message.
     */
    public static String showGreetingMessage() {
        return prettyPrint(new String[]{"Hello! I'm Tel.", "What can I do for you?"});
    }

    /**
     * Says a farewell to the user.
     *
     * @return Formatted message.
     */
    public static String showFarewellMessage() {
        return prettyPrint("Bye. Hope to see you again soon!");
    }
}
