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
                tasks.get(index - 1).setStatusIcon(true);
                System.out.println(newLine() + "    Nice! I've marked this task as done:");
                getList(tasks);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                tasks.get(index - 1).setStatusIcon(false);
                System.out.println(newLine() + "    OK, I've marked this task as not done yet:");
                getList(tasks);
            } else if (Objects.equals(input, "list")) {
                System.out.print(newLine());
                getList(tasks);
            } else if (!Objects.equals(input, "")) {
                tasks.add(new Task(input));
                System.out.println(newLine() + "    added: " + input + "\n" + newLine());
            }
            input = scanner.nextLine();

        } while(!Objects.equals(input, "bye"));
        System.out.println(newLine() + "    Bye. Hope to see you again soon!\n" + newLine());
    }

    public static String newLine() {
        return "    ____________________________________________________________\n";
    }

    public static void getList(List<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(tasks.get(i - 1).getListItem(i));
        }
        System.out.print(newLine());
    }
}