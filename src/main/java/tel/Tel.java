package tel;

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
           Ui.showError(t.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs chatbot in a loop, then dump list of task to storage.
     */
    public void run() {
        Ui.showGreetingMessage();
        int code = 1;
        do {
            try {
                String input = Ui.readCommand();
                code = Parser.parse(input, tasks);
            } catch (TelException t) {
                Ui.showError(t.getMessage());
            }
        } while (code != 0);
        try {
            storage.dump(tasks);
        } catch (TelException t) {
            Ui.showError(t.getMessage());
        } finally {
            Ui.showFarewellMessage();
        }
    }

    /**
     * Runs main program.
     */
    public static void main(String[] args) {
        new Tel("./tel.txt").run();
    }
}