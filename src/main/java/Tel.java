import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.*;

public class Tel {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*
    public Tel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {}
     */

    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        fileReader(tasks);

        System.out.println(
                newLine() + "\n    Hello! I'm Tel.\n    What can I do for you?\n" + newLine()
        );

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        do {
            try {
                if (input.startsWith("mark")) {
                    int index = validateNumber(tasks, input, 5);
                    tasks.markDone(index, true);
                } else if (input.startsWith("unmark")) {
                    int index = validateNumber(tasks, input, 5);
                    tasks.markDone(index, false);
                } else if (input.startsWith("delete")) {
                    int index = validateNumber(tasks, input, 7);
                    tasks.delete(index);
                } else if (input.startsWith("todo")) {
                    tasks.add(new Todo(input.substring(5)));
                } else if (input.startsWith("deadline")) {
                    try {
                        String[] params = validateSplit(input.substring(9), " /by ");
                        LocalDateTime ldt = dateConverter(params[1]);
                        tasks.add(new Deadline(params[0], ldt));
                    } catch (IllegalArgumentException i) {
                        prettyPrint("Invalid input, /by separator required!");
                    }
                } else if (input.startsWith("event")) {
                    try {
                        String[] params1 = validateSplit(input.substring(6), " /from ");
                        String[] params2 = validateSplit(params1[1], " /to ");
                        LocalDateTime ldt1 = dateConverter(params2[0]);
                        LocalDateTime ldt2 = dateConverter(params2[1]);
                        tasks.add(new Event(params1[0], ldt1, ldt2));
                    } catch (IllegalArgumentException i) {
                        prettyPrint("Invalid input, /from & a /to separators required!");
                    }
                } else if (Objects.equals(input, "list")) {
                    System.out.println(tasks);
                } else if (Objects.equals(input, "bye")) {
                    break;
                } else {
                    prettyPrint("Input should start with mark/unmark/todo/deadline/event!");
                }
            } catch (StringIndexOutOfBoundsException s) {
                prettyPrint("Input cannot be empty!");
            } catch (IllegalArgumentException i) {
                prettyPrint("Input must be between 1 & " + tasks.size());
            }
            input = scanner.nextLine();
        } while(!Objects.equals(input, "bye"));
        System.out.println(newLine() + "\n    Bye. Hope to see you again soon!\n" + newLine());
        fileWriter(tasks);
    }

    public static String newLine() {
        return "    ____________________________________________________________";
    }

    public static LocalDateTime dateConverter(String string) {
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
            prettyPrint("Your datetime is wrong! it should be yyyy-mm-dd or dd/MM/yyyy, with HH:mm if necessary.");
        }
        return dt;
    }

    public static void fileReader(TaskList tasks) {
        File f = new File("./tel.txt");
        try (Scanner r = new Scanner(f)) {
            while (r.hasNextLine()) {
                String[] data = r.nextLine().split(" \\| ");
                switch(data[0]) {
                case "D":
                    tasks.add(new Deadline(data[2], LocalDateTime.parse(data[3])));
                    break;
                case "E":
                    tasks.add(new Event(data[2], LocalDateTime.parse(data[3]), LocalDateTime.parse(data[4])));
                    break;
                case "T":
                    tasks.add(new Todo(data[2]));
                    break;
                default:
                    throw new IllegalArgumentException();
                }
                tasks.getTask(tasks.size() - 1).setStatusIcon(Integer.parseInt(data[1]) == 1);
            }
        } catch (FileNotFoundException e) {
            prettyPrint("No task file found! Starting from clean state.");
        } catch (IllegalArgumentException e) {
            prettyPrint("Task file is corrupted! Starting from clean state.");
        }
    }

    public static void fileWriter(TaskList tasks) {
        try {
            FileWriter w = new FileWriter("./tel.txt");
            for (Task task : tasks.getTaskList()) {
                w.write(task.toFile() + "\n");
            }
            w.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            prettyPrint("State is corrupted! Unable to write to task file.");
        }
    }

    public static int validateNumber(TaskList tasks, String input, int start) throws IllegalArgumentException {
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

    public static void prettyPrint(String message) {
        System.out.println(newLine());
        System.out.println("    " + message);
        System.out.println(newLine());
    }
}