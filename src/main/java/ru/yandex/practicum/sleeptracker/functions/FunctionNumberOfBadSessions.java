package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class FunctionNumberOfBadSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        String title = "Количество сессий с плохим качеством сна";

        List<SleepingSession> sessions = sleepingSessions.stream()
                .filter(session -> session.qualitySleepingSession().equals(SleepQuality.BAD))
                .toList();

        return new SleepAnalysisResult(title, sessions.size());
    }
}
