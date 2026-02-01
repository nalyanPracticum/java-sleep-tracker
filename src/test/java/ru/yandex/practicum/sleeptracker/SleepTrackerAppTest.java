package test.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.SleepTrackerApp;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SleepTrackerAppTest {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    @Test
    public void testSleepTrackerAppTestParseLine() {

        String line = "01.10.25 23:15;02.10.25 07:30;GOOD";

        Optional<SleepingSession> expectedResult = Optional.of(new SleepingSession(LocalDateTime.parse("01.10.25 23:15", FORMATTER), LocalDateTime.parse("02.10.25 07:30", FORMATTER), SleepQuality.GOOD));

        SleepTrackerApp tracker = new SleepTrackerApp();
        Optional<SleepingSession> result = tracker.parseLine(line);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testSleepTrackerAppTestParseLineWithSpaces() {

        String line = "01.10.25 23:15   ;      02.10.25 07:30         ;     GOOD";

        Optional<SleepingSession> expectedResult = Optional.of(new SleepingSession(LocalDateTime.parse("01.10.25 23:15", FORMATTER), LocalDateTime.parse("02.10.25 07:30", FORMATTER), SleepQuality.GOOD));

        SleepTrackerApp tracker = new SleepTrackerApp();
        Optional<SleepingSession> result = tracker.parseLine(line);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testSleepTrackerAppTestParseLineEmptyLine() {

        String line = "";

        Optional<SleepingSession> expectedResult = Optional.empty();

        SleepTrackerApp tracker = new SleepTrackerApp();
        Optional<SleepingSession> result = tracker.parseLine(line);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testlaunchingFunctions() {

        SleepTrackerApp tracker = new SleepTrackerApp();

        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("06.10.25 23:30", FORMATTER),
                LocalDateTime.parse("07.10.25 05:50", FORMATTER), SleepQuality.BAD)); // продолжительность 380 / голубь

        List<SleepAnalysisResult> results = tracker.launchingFunctions(sleepingSessions);

        Object[] expectedResult = {1, 380L, 380L, 380L, 1, 1,"голубь"};

        for (int i = 0; i < expectedResult.length; i++) {
            Assertions.assertEquals(expectedResult[i], results.get(i).getResult());
        }
    }
}