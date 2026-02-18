package tel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class contains validation and parsing features the program uses.
 */
public class Parser {

    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter STD_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter ISO_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter STD_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Validates the input string and call functions to do actions.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @return 1 if parseable, 0 if not.
     */
    public static String parse(String input, TaskList tasks) throws TelException {
        String trimmedInput = input.trim();
        String[] parts = trimmedInput.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        try {
            return switch (command) {
            case "mark" -> markTask(args, tasks, true);
            case "unmark" -> markTask(args, tasks, false);
            case "delete" -> deleteTask(args, tasks);
            case "todo" -> createToDo(args, tasks);
            case "deadline" -> createDeadline(args, tasks);
            case "event" -> createEvent(args, tasks);
            case "find" -> findTask(args, tasks);
            case "list" -> Ui.showListMessage(tasks);
            case "bye" -> Ui.showFarewellMessage();
            default -> throw new TelException(
                    "Input should start with:\n"
                    + "bye, deadline, delete, event, find, list, mark, todo, unmark");
            };
        } catch (NumberFormatException e) {
            throw new TelException("Please provide a valid task number.");
        }
    }

    /**
     * Method to create deadline.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @return Message to be displayed.
     */
    private static String createDeadline(String input, TaskList tasks) throws TelException {
        if (input.isEmpty()) {
            throw new TelException("The description of a deadline cannot be empty.");
        }
        try {
            String[] params = validateSplit(input, " /by ");
            LocalDateTime ldt = validateDate(params[1].trim());
            tasks.add(new Deadline(params[0].trim(), ldt));
            return Ui.showAddMessage(tasks);
        } catch (IllegalArgumentException i) {
            throw new TelException("/by separator required!");
        }
    }

    /**
     * Method to create event.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @return Message to be displayed.
     */
    private static String createEvent(String input, TaskList tasks) throws TelException {
        if (input.isEmpty()) {
            throw new TelException("The description of an event cannot be empty.");
        }
        try {
            String[] params1 = validateSplit(input, " /from ");
            String[] params2 = validateSplit(params1[1], " /to ");
            LocalDateTime ldt1 = validateDate(params2[0].trim());
            LocalDateTime ldt2 = validateDate(params2[1].trim());
            tasks.add(new Event(params1[0].trim(), ldt1, ldt2));
            return Ui.showAddMessage(tasks);
        } catch (IllegalArgumentException i) {
            throw new TelException("/from & a /to separators required!");
        }
    }

    /**
     * Method to create to-do.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @return Message to be displayed.
     */
    private static String createToDo(String input, TaskList tasks) throws TelException {
        if (input.isEmpty()) {
            throw new TelException("The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(input.trim()));
        return Ui.showAddMessage(tasks);
    }

    /**
     * Method to delete task.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @return Message to be displayed.
     */
    private static String deleteTask(String input, TaskList tasks) throws TelException {
        int index = validateNumber(tasks, input);
        String message = Ui.showDeleteMessage(tasks.get(index - 1), tasks);
        tasks.delete(index);
        return message;
    }

    /**
     * Method to find task.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @return Message to be displayed.
     */
    private static String findTask(String input, TaskList tasks) throws TelException {
        if (input.isEmpty()) {
            throw new TelException("Please provide a keyword to find.");
        }
        TaskList query = tasks.find(input.trim());
        return Ui.showFindMessage(query);
    }

    /**
     * Method to mark task as done or undone.
     *
     * @param input The input string entered by the user.
     * @param tasks The task list.
     * @param bool The status of the task to be changed to.
     * @return Message to be displayed.
     */
    private static String markTask(String input, TaskList tasks, boolean bool) throws TelException {
        int index = validateNumber(tasks, input);
        tasks.markDone(index, bool);
        return Ui.showMarkMessage(tasks.get(index - 1));
    }

    /**
     * Validates the date string and converts it into a LocalDateTime object.
     *
     * @param string The date string entered by the user.
     * @return A LocalDateTime object. Null if string is invalid.
     */
    public static LocalDateTime validateDate(String string) throws TelException {
        DateTimeFormatter[] formatters = {ISO_DATETIME, STD_DATETIME, ISO_DATE, STD_DATE};

        for (DateTimeFormatter formatter : formatters) {
            try {
                if (formatter == ISO_DATE || formatter == STD_DATE) {
                    return LocalDate.parse(string, formatter).atStartOfDay();
                }
                return LocalDateTime.parse(string, formatter);
            } catch (DateTimeParseException ignored) {
                // Continue to next formatter
            }
        }
        throw new TelException(
                "Your datetime is wrong! it should be yyyy-MM-dd or dd/MM/yyyy, with HH:mm if necessary."
        );
    }

    /**
     * Validates task number in command to be within the range of the list of tasks.
     *
     * @param tasks The list of tasks.
     * @param input The command entered by the user.
     * @return An integer.
     * @throws TelException If input fails format or range check.
     */
    public static int validateNumber(TaskList tasks, String input) throws TelException {
        try {
            int index = Integer.parseInt(input.trim());
            if (!(1 <= index && index <= tasks.size())) {
                throw new TelException("Input must be between 1 & " + tasks.size());
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TelException("Please provide a valid numeric task index.");
        }
    }

    /**
     * Validates command can be split by separator and separates it if so.
     *
     * @param input The command entered by the user.
     * @param separator The string separator to split the command into two.
     * @return An array of size 2 with the two substrings.
     * @throws IllegalArgumentException If input fails format check.
     */
    public static String[] validateSplit(String input, String separator) throws IllegalArgumentException {
        String[] params = input.split(separator, 2);
        if (params.length == 2 && !params[0].isBlank() && !params[1].isBlank()) {
            return params;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
