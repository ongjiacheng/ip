package tel;

import java.util.Objects;

/**
 * The base class for the Tel chatbot.
 */
public class Tel {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Initializes chatbot and load list of task from storage.
     */
    public Tel(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (TelException t) {
            String message = Ui.showError(t.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Terminates chatbot and dump list of task into storage.
     */
    public String exit() {
        String message = "";
        try {
            storage.dump(tasks);
        } catch (TelException t) {
            message += Ui.showError(t.getMessage()) + "\n";
        } finally {
            message += Ui.showFarewellMessage();
        }
        return message;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String output = Parser.parse(input, tasks);
            if (Objects.equals(output, Ui.showFarewellMessage())) {
                return exit();
            } else {
                return output;
            }
        } catch (TelException t) {
            return Ui.showError(t.getMessage());
        }
    }
}
