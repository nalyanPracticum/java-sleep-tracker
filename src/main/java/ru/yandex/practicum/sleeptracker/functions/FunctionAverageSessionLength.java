package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FunctionAverageSessionLength implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        String title = "Средняя продолжительность сессии (в минутах)";

        Long average = sleepingSessions.stream()
                .map(session ->
                        Duration.between(session.startSleepingSession(), session.endSleepingSession()).toMinutes())
                .reduce(0L, Long::sum) / sleepingSessions.size();

        return new SleepAnalysisResult(title, average);
    }
}
