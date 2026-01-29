package tel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * This class contains validation and parsing features the program uses.
 */
public class Parser {
    /**
     * Parses user input into program instructions.
     *
     * @param input The user input.
     * @param tasks The list of tasks.
     * @return An integer status code of either 0 (exit) or 1 (continue).
     * @throws TelException If input has invalid commands or separators.
     */
    public static int parse(String input, TaskList tasks) throws TelException {
        try {
            if (input.startsWith("mark")) {
                int index = validateNumber(tasks, input, 5);
                tasks.markDone(index, true);
                Ui.showMarkMessage(tasks.get(index - 1));
            } else if (input.startsWith("unmark")) {
                int index = validateNumber(tasks, input, 5);
                tasks.markDone(index, false);
                Ui.showMarkMessage(tasks.get(index - 1));
            } else if (input.startsWith("delete")) {
                int index = validateNumber(tasks, input, 7);
                Ui.showDeleteMessage(tasks.get(index), tasks);
                tasks.delete(index);
            } else if (input.startsWith("todo")) {
                tasks.add(new Todo(input.substring(5)));
                Ui.showAddMessage(tasks);
            } else if (input.startsWith("deadline")) {
                try {
                    String[] params = validateSplit(input.substring(9), " /by ");
                    LocalDateTime ldt = validateDate(params[1]);
                    tasks.add(new Deadline(params[0], ldt));
                    Ui.showAddMessage(tasks);
                } catch (IndexOutOfBoundsException | IllegalArgumentException i) {
                    throw new TelException("/by separator required!");
                }
            } else if (input.startsWith("event")) {
                try {
                    String[] params1 = validateSplit(input.substring(6), " /from ");
                    String[] params2 = validateSplit(params1[1], " /to ");
                    LocalDateTime ldt1 = validateDate(params2[0]);
                    LocalDateTime ldt2 = validateDate(params2[1]);
                    tasks.add(new Event(params1[0], ldt1, ldt2));
                    Ui.showAddMessage(tasks);
                } catch (IndexOutOfBoundsException | IllegalArgumentException i) {
                    throw new TelException("/from & a /to separators required!");
                }
            } else if (Objects.equals(input, "list")) {
                Ui.prettyPrint(tasks.toString());
            } else if (Objects.equals(input, "bye")) {
                return 0;
            } else {
                throw new TelException("Input should start with mark/unmark/todo/deadline/event!");
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new TelException("Input cannot be empty!");
        } catch (IllegalArgumentException i) {
            throw new TelException("Input must be between 1 & " + tasks.size());
        }
        return 1;
    }

    /**
     * Validates the date string and converts it into a LocalDateTime object.
     *
     * @param string The date string entered by the user.
     * @return A LocalDateTime object. Null if string is invalid.
     */
    public static LocalDateTime validateDate(String string) {
        LocalDateTime dt = null;
        try {
            boolean check_iso = string.charAt(4) == '-' && string.charAt(7) == '-';
            boolean check_std = string.charAt(2) == '/' && string.charAt(5) == '/';
            if (string.length() == 10) {
                LocalDate d = null;
                if (check_iso) {
                    d = LocalDate.parse(string);
                } else if (check_std) {
                    d = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                assert d != null;
                dt = d.atStartOfDay();
            } else if (string.length() == 16) {
                if (check_iso) {
                    dt = LocalDateTime.parse(string);
                } else if (check_std) {
                    dt = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                }
            }
        } catch (DateTimeParseException | NullPointerException n) {
            Ui.prettyPrint("Your datetime is wrong! it should be yyyy-mm-dd or dd/MM/yyyy, with HH:mm if necessary.");
        }
        return dt;
    }

    /**
     * Validates task number in command to be within the range of the list of tasks.
     *
     * @param tasks The list of tasks.
     * @param input The command entered by the user.
     * @param start The starting location of the integer in the substring.
     * @return An integer.
     * @throws IllegalArgumentException If input fails format or range check.
     */
    public static int validateNumber(TaskList tasks, String input, int start) throws IllegalArgumentException {
        int index = Integer.parseInt(input.substring(start));
        if (!(1 <= index && index <= tasks.size())) {
            throw new IllegalArgumentException();
        }
        return index;
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
        String[] params = input.split(separator, -1);
        if (params.length == 2) {
            return params;
        } else {
            throw new IllegalArgumentException();
        }
    }
}