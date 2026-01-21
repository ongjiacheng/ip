import java.util.Objects;
import java.util.Scanner;

public class Tel {
    public static void main(String[] args) {
        System.out.println(greeting());

        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            if (!Objects.equals(input, "")) {
                System.out.println(newLine() + "    " + input + "\n" + newLine());
            }
            input = scanner.nextLine();

        } while(!Objects.equals(input, "bye"));
        System.out.println(farewell());
    }

    public static String newLine() {
        return "    ____________________________________________________________\n";
    }

    public static String greeting() {
        return newLine() + "    Hello! I'm Tel.\n    What can I do for you?\n" + newLine();
    }

    public static String farewell() {
        return newLine() + "    Bye. Hope to see you again soon!\n" + newLine();
    }
}
