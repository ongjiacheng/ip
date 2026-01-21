import java.util.*;

public class Tel {
    public static void main(String[] args) {
        System.out.println(
                newLine() + "    Hello! I'm Tel.\n    What can I do for you?\n" + newLine()
        );

        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<String> list = new ArrayList<>();
        List<Character> done = new ArrayList<>();
        do {
            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                done.set(index - 1, 'X');
                System.out.println(newLine() + "    Nice! I've marked this task as done:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("    " + i + ". [" + done.get(i - 1) + "] " + list.get(i - 1));
                }
                System.out.print(newLine());
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                done.set(index - 1, ' ');
                System.out.println(newLine() + "    OK, I've marked this task as not done yet:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("    " + i + ". [" + done.get(i - 1) + "] " + list.get(i - 1));
                }
                System.out.print(newLine());
            } else if (Objects.equals(input, "list")) {
                System.out.print(newLine());
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("    " + i + ". " + list.get(i - 1));
                }
                System.out.print(newLine());
            } else if (!Objects.equals(input, "")) {
                list.add(input);
                done.add(' ');
                System.out.println(newLine() + "    added: " + input + "\n" + newLine());
            }
            input = scanner.nextLine();

        } while(!Objects.equals(input, "bye"));
        System.out.println(newLine() + "    Bye. Hope to see you again soon!\n" + newLine());
    }

    public static String newLine() {
        return "    ____________________________________________________________\n";
    }
}