package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FunctionMaxSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        String title = "Максимальная продолжительность сессии (в минутах)";

        Optional<Duration> max = sleepingSessions.stream()
                .map(session ->
                        Duration.between(session.startSleepingSession(), session.endSleepingSession()))
                .max(Duration::compareTo);

        return max.map(duration -> new SleepAnalysisResult(title, max.get().toMinutes())).orElseGet(() -> new SleepAnalysisResult(title, 0));
    }
}