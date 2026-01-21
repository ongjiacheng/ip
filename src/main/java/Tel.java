import java.util.*;

public class Tel {
    public static void main(String[] args) {
        System.out.println(
                newLine() + "    Hello! I'm Tel.\n    What can I do for you?\n" + newLine()
        );

        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<String> list = new ArrayList<>();
        do {
            if (Objects.equals(input, "list")) {
                System.out.print(newLine());
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("    " + i + ". " + list.get(i - 1));
                }
                System.out.print(newLine());
            } else if (!Objects.equals(input, "")) {
                list.add(input);
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