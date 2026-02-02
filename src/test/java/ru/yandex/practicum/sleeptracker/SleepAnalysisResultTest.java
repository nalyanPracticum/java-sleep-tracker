package test.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SleepAnalysisResultTest {

    @Test
    public void testSleepAnalysisResultGetResultStringResult() {
        SleepAnalysisResult functionsResult = new SleepAnalysisResult("", "голубь");
        Assertions.assertEquals("голубь", functionsResult.result());
    }

    @Test
    public void testSleepAnalysisResultGetResultIntegerResult() {
        SleepAnalysisResult functionsResult = new SleepAnalysisResult("", 15);
        Assertions.assertEquals(15, functionsResult.result());
    }

    @Test
    public void testSleepAnalysisResultGetResultLongResult() {
        SleepAnalysisResult functionsResult = new SleepAnalysisResult("", 380L);
        Assertions.assertEquals(380L, functionsResult.result());
    }
}
