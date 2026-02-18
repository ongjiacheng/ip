package seedu.tel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import tel.Deadline;
import tel.Event;
import tel.Task;

public class TaskTest {
    @Test
    public void deadline_project_newDeadline() {
        LocalDateTime dt = LocalDateTime.of(2026, 1, 29, 20, 0);
        Task task = new Deadline("Project", dt);
        task.setStatusIcon(true);
        assertEquals("[D][X] Project (by: 2026-01-29T20:00)", task.toString());
    }

    @Test
    public void createEvent_meeting_newEvent() {
        LocalDateTime dt1 = LocalDateTime.of(2026, 1, 29, 20, 0);
        LocalDateTime dt2 = LocalDateTime.of(2026, 1, 29, 22, 0);
        Task task = new Event("Meeting", dt1, dt2);
        task.setStatusIcon(true);
        assertEquals("[E][X] Meeting (from: 2026-01-29T20:00 to: 2026-01-29T22:00)", task.toString());
    }
}
