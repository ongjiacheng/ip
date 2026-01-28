import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Tel {
    public static void main(String[] args) {
        List<Task> tasks = fileReader();

        System.out.println(
                newLine() + "\n    Hello! I'm Tel.\n    What can I do for you?\n" + newLine()
        );

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
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
                        prettyPrint("Invalid input, /by separator required!");
                    }
                } else if (input.startsWith("event")) {
                    try {
                        String[] params1 = validateSplit(input.substring(6), " /from ");
                        String[] params2 = validateSplit(params1[1], " /to ");
                        Task task = new Event(params1[0], params2[0], params2[1]);
                        adder(tasks, task);
                    } catch (IllegalArgumentException i) {
                        prettyPrint("Invalid input, /from & a /to separators required!");
                    }
                } else if (Objects.equals(input, "list")) {
                    getList(tasks);
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

    public static List<Task> fileReader() {
        List<Task> tasks = new ArrayList<>();
        File f = new File("./tel.txt");
        try (Scanner r = new Scanner(f)) {
            while (r.hasNextLine()) {
                String[] data = r.nextLine().split(" \\| ");
                switch(data[0]) {
                case "D":
                    tasks.add(new Deadline(data[2], data[3]));
                    break;
                case "E":
                    tasks.add(new Event(data[2], data[3], data[4]));
                    break;
                case "T":
                    tasks.add(new Todo(data[2]));
                    break;
                default:
                    throw new IllegalArgumentException();
                }
                tasks.get(tasks.size() - 1).setStatusIcon(Integer.parseInt(data[1]) == 1);
            }
        } catch (FileNotFoundException e) {
            prettyPrint("No task file found! Starting from clean state.");
        } catch (IllegalArgumentException e) {
            prettyPrint("Task file is corrupted! Starting from clean state.");
        }
        return tasks;
    }

    public static void fileWriter(List<Task> tasks) {
        try {
            FileWriter w = new FileWriter("./tel.txt");
            for (Task task : tasks) {
                w.write(task.toFile() + "\n");
            }
            w.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            prettyPrint("State is corrupted! Unable to write to task file.");
        }
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

    public static void prettyPrint(String message) {
        System.out.println(newLine());
        System.out.println("    " + message);
        System.out.println(newLine());
    }
}