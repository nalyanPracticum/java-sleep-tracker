package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class FunctionNumberOfSleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {

    String title = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        LocalDate startPeriod = sleepingSessions.getFirst().startSleepingSession().toLocalDate();
        LocalDate endPeriod = sleepingSessions.getLast().endSleepingSession().toLocalDate();
        int allPeriods = Period.between(startPeriod, endPeriod).getDays() + 1;

        if (sleepingSessions.getFirst().startSleepingSession().getHour() < 12) {
            allPeriods ++;
        }

        List<SleepingSession> sleepNights = sleepingSessions.stream()
                .filter(session -> (session.startSleepingSession().getDayOfMonth() != session.endSleepingSession().getDayOfMonth())
                        || (session.startSleepingSession().getHour() < 6))
                .toList();

        return new SleepAnalysisResult(title, allPeriods - sleepNights.size());
    }
}
