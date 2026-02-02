package test.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;
import main.java.ru.yandex.practicum.sleeptracker.functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class FunctionsTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void testOptions() {
        sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("06.10.25 23:30", FORMATTER),
                LocalDateTime.parse("07.10.25 05:50", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380 / голубь
        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("08.10.25 06:00", FORMATTER),
                LocalDateTime.parse("08.10.25 12:50", FORMATTER), SleepQuality.BAD)); // продолжительность 410 / бессонная / голубь
        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("09.10.25 23:30", FORMATTER),
                LocalDateTime.parse("10.10.25 00:00", FORMATTER), SleepQuality.GOOD)); // минимальная продолжительность 30 / голубь
        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("16.10.25 19:00", FORMATTER),
                LocalDateTime.parse("17.10.25 12:30", FORMATTER), SleepQuality.BAD)); // максимальная продолжительность 1050 / голубь

        // средняя продолжительность 467
        // сессий без сна: 9
    }

    @Test
    public void testFunctionOfCountingSessions() {

        SleepAnalysisResult result = new FunctionOfCountingSessions().apply(sleepingSessions);

        Assertions.assertEquals(4, result.result());
    }

    @Test
    public void testFunctionMinSessionDuration() {

        SleepAnalysisResult result = new FunctionMinSessionDuration().apply(sleepingSessions);

        Assertions.assertEquals(30L, result.result());
    }

    @Test
    public void testFunctionMaxSessionDuration() {

        SleepAnalysisResult result = new FunctionMaxSessionDuration().apply(sleepingSessions);

        Assertions.assertEquals(1050L, result.result());
    }

    @Test
    public void testFunctionAverageSessionLength() {

        SleepAnalysisResult result = new FunctionAverageSessionLength().apply(sleepingSessions);

        Assertions.assertEquals(467L, result.result());
    }

    @Test
    public void testFunctionNumberOfBadSessions() {

        SleepAnalysisResult result = new FunctionNumberOfBadSessions().apply(sleepingSessions);

        Assertions.assertEquals(2, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginnigLater12() {

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(9, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginnigEarlier12() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("05.10.25 11:00", FORMATTER),
                LocalDateTime.parse("05.10.25 12:50", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(11, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginnigTo12() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("05.10.25 12:00", FORMATTER),
                LocalDateTime.parse("05.10.25 12:50", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(10, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginAndEndToNight() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("06.10.25 01:00", FORMATTER),
                LocalDateTime.parse("06.10.25 05:00", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(9, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginEarler24EndToNight() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("05.10.25 23:50", FORMATTER),
                LocalDateTime.parse("06.10.25 05:00", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(9, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginLater24EndLater6() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("06.10.25 01:00", FORMATTER),
                LocalDateTime.parse("06.10.25 07:00", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(9, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBegin24End6() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("06.10.25 00:00", FORMATTER),
                LocalDateTime.parse("06.10.25 06:00", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(9, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginEndofNight() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("06.10.25 05:59", FORMATTER),
                LocalDateTime.parse("06.10.25 06:00", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(9, result.result());
    }

    @Test
    public void testFunctionNumberOfSleeplessNightsWithSessionBeginAfterNight() {

        sleepingSessions.addFirst(new SleepingSession(LocalDateTime.parse("06.10.25 06:00", FORMATTER),
                LocalDateTime.parse("06.10.25 06:30", FORMATTER), SleepQuality.NORMAL)); // продолжительность 380

        SleepAnalysisResult result = new FunctionNumberOfSleeplessNights().apply(sleepingSessions);

        Assertions.assertEquals(10, result.result());
    }

    @Test
    public void testFunctionUsersChronotypeEqualityOfConditions() {

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("18.10.25 23:00", FORMATTER),
                LocalDateTime.parse("19.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("20.10.25 23:00", FORMATTER),
                LocalDateTime.parse("21.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("22.10.25 23:00", FORMATTER),
                LocalDateTime.parse("23.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("24.10.25 23:00", FORMATTER),
                LocalDateTime.parse("25.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        SleepAnalysisResult result = new FunctionUsersChronotype().apply(sleepingSessions);

        Assertions.assertEquals("голубь", result.result());
    }

    @Test
    public void testFunctionUsersChronotypeOwl() {

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("18.10.25 23:00", FORMATTER),
                LocalDateTime.parse("19.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("20.10.25 23:00", FORMATTER),
                LocalDateTime.parse("21.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("22.10.25 23:00", FORMATTER),
                LocalDateTime.parse("23.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("24.10.25 23:00", FORMATTER),
                LocalDateTime.parse("25.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("26.10.25 23:00", FORMATTER),
                LocalDateTime.parse("27.10.25 09:30", FORMATTER), SleepQuality.NORMAL));

        SleepAnalysisResult result = new FunctionUsersChronotype().apply(sleepingSessions);

        Assertions.assertEquals("сова", result.result());
    }

    @Test
    public void testFunctionUsersChronotypeLark() {

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("18.10.25 21:00", FORMATTER),
                LocalDateTime.parse("19.10.25 06:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("20.10.25 21:00", FORMATTER),
                LocalDateTime.parse("21.10.25 06:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("22.10.25 21:00", FORMATTER),
                LocalDateTime.parse("23.10.25 06:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("24.10.25 21:00", FORMATTER),
                LocalDateTime.parse("25.10.25 06:30", FORMATTER), SleepQuality.NORMAL));

        sleepingSessions.add(new SleepingSession(LocalDateTime.parse("26.10.25 21:00", FORMATTER),
                LocalDateTime.parse("27.10.25 06:30", FORMATTER), SleepQuality.NORMAL));

        SleepAnalysisResult result = new FunctionUsersChronotype().apply(sleepingSessions);

        Assertions.assertEquals("жаворонок", result.result());
    }








}
