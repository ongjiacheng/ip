package tel;

public class Tel {
    private final Storage storage;
    private TaskList tasks;

    public Tel(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (TelException t) {
           Ui.showError(t.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showGreetingMessage();
        try {
            int code;
            do {
                String input = Ui.readCommand();
                code = Parser.parse(input, tasks);
            } while (code != 0);
            storage.dump(tasks);
        } catch (TelException t) {
            Ui.showError(t.getMessage());
        } finally {
            Ui.showFarewellMessage();
        }
    }

    public static void main(String[] args) {
        new Tel("./tel.txt").run();
    }
}