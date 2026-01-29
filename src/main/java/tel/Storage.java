package tel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final TaskList tasks = new TaskList();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void dump(TaskList tasks) throws TelException {
        try {
            FileWriter w = new FileWriter("./tel.txt");
            for (Task task : tasks.getList()) {
                w.write(task.toFile() + "\n");
            }
            w.close();
        } catch (IOException e) {
            throw new TelException("State is corrupted! Unable to write to task file.");
        }
    }
}
