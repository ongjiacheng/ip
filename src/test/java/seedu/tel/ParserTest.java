package seedu.tel;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import tel.Parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void validateDateTest() {
        assertEquals(
                LocalDateTime.of(2026, 1, 29, 0, 0),
                Parser.validateDate("2026-01-29")
        );
    }
    
    @Test
    public void validateSplitTest() {
        String[] expected = new String[] {"task", "2026-01-29T20:00"};
        String[] actual = Parser.validateSplit("task /by 2026-01-29T20:00", " /by ");
        assertArrayEquals(expected, actual);
    }
}