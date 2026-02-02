package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FunctionMinSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        String title = "Минимальная продолжительность сессии (в минутах)";

        Optional<Duration> min = sleepingSessions.stream()
                .map(session ->
                        Duration.between(session.startSleepingSession(), session.endSleepingSession()))
                .min(Duration::compareTo);

        return min.map(duration -> new SleepAnalysisResult(title, duration.toMinutes())).orElseGet(() -> new SleepAnalysisResult(title, 0));
    }
}
