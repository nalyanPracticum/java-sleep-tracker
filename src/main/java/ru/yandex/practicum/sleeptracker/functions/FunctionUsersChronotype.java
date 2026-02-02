package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class FunctionUsersChronotype implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        String title = "Хронотип";
        String result;
        int allSessions = sleepingSessions.size();

        int owlChronotype = sleepingSessions.stream()
                .filter(session -> (session.startSleepingSession().getHour() == 23
                        || session.startSleepingSession().getHour() < 6)
                        && session.endSleepingSession().getHour() >= 9)
                .toList().size();

        int larkChronotype = sleepingSessions.stream()
                .filter(session -> (session.startSleepingSession().getHour() < 22 &&
                        session.startSleepingSession().getHour() >= 6
                        && session.endSleepingSession().getHour() < 7))
                .toList().size();

        int pigeonChronotype = allSessions - owlChronotype - larkChronotype;

        if (owlChronotype > larkChronotype && owlChronotype > pigeonChronotype) result = "сова";
        else if (larkChronotype > owlChronotype && larkChronotype > pigeonChronotype) result = "жаворонок";
        else result = "голубь";

        return new SleepAnalysisResult(title, result);
    }
}
