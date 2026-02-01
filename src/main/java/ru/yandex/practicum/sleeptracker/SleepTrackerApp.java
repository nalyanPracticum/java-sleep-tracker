package main.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.functions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final String FILE_NAME = "!finalProject/Sprint_8/java-sleep-tracker/src/main/resources/sleep_log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private static final String SEPARATOR = ";";

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> FUNCTIONS_LIST = List.of(
            new FunctionOfCountingSessions(),
            new FunctionMinSessionDuration(),
            new FunctionMaxSessionDuration(),
            new FunctionAverageSessionLength(),
            new FunctionNumberOfBadSessions(),
            new FunctionNumberOfSleeplessNights(),
            new FunctionUsersChronotype()
    );

    public static void main(String[] args) {

        SleepTrackerApp tracker = new SleepTrackerApp();

        try {
            List<SleepingSession> sleepingSessions = tracker.readFile(FILE_NAME);
            tracker.launchingFunctions(sleepingSessions);
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private List<SleepingSession> readFile(String fileName) {

        List<SleepingSession> sessions = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(fileReader)) {

            sessions = br.lines()
                    .map(this::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sessions;
    }

    public Optional<SleepingSession> parseLine(String line) {
        try {
            String[] partOfLine = line.split(SEPARATOR);

            LocalDateTime startSleepingSession = LocalDateTime.parse(partOfLine[0].trim(), FORMATTER);
            LocalDateTime endSleepingSession = LocalDateTime.parse(partOfLine[1].trim(), FORMATTER);
            SleepQuality qualitySleepingSession = SleepQuality.valueOf(partOfLine[2].trim());

            return Optional.of(new SleepingSession(startSleepingSession, endSleepingSession, qualitySleepingSession));

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<SleepAnalysisResult> launchingFunctions(List<SleepingSession> sleepingSessions) {
        return FUNCTIONS_LIST.stream()
                .map(function -> function.apply(sleepingSessions))
                .peek(System.out::println)
                .toList();
    }
}