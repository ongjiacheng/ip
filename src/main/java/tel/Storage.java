package tel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * This class loads and dumps task list between a text file and the program memory.
 */
public class Storage {
    private final String filePath;
    private final TaskList tasks = new TaskList();

    /**
     * Creates Storage object to allow setting of file path.
     *
     * @param filePath The file path to write and read the list of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load list of tasks from file into the program.
     *
     * @throws TelException If file is corrupted or non-existent.
     */
    public TaskList load() throws TelException {
        File f = new File(filePath);
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
                    throw new TelException("tel.Task file is corrupted! Starting from clean state.");
                }
                tasks.get(tasks.size() - 1).setStatusIcon(Integer.parseInt(data[1]) == 1);
            }
        } catch (FileNotFoundException e) {
            throw new TelException("No task file found! Starting from clean state.");
        } catch (IllegalArgumentException e) {
            throw new TelException("tel.Task file is corrupted! Starting from clean state.");
        }
        return tasks;
    }

    /**
     * Dump list of tasks from program into the file.
     *
     * @throws TelException If program is corrupted.
     */
    public void dump(TaskList tasks) throws TelException {
        try {
            FileWriter w = new FileWriter("./tel.txt");
            w.write(tasks.toFile());
            w.close();
        } catch (IOException e) {
            throw new TelException("State is corrupted! Unable to write to task file.");
        }
    }
}
