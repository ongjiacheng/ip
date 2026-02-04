package seedu.tel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import tel.Parser;

public class ParserTest {
    @Test
    public void validateDate_iso_convertDateToDateTime() {
        assertEquals(
                LocalDateTime.of(2026, 1, 29, 0, 0),
                Parser.validateDate("2026-01-29")
        );
    }
    @Test
    public void validateSplit_twoSeparators_splitIntoArray() {
        String[] expected = new String[] {"task", "2026-01-29T20:00"};
        String[] actual = Parser.validateSplit("task /by 2026-01-29T20:00", " /by ");
        assertArrayEquals(expected, actual);
    }
}
