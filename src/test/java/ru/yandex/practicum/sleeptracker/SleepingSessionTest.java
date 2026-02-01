package test.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.SleepingSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static main.java.ru.yandex.practicum.sleeptracker.SleepTrackerApp.FORMATTER;

public class SleepingSessionTest {

    SleepingSession session = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", FORMATTER), LocalDateTime.parse("02.10.25 07:30", FORMATTER), SleepQuality.GOOD);


    @Test
    public void testSleepingSessionTestGetStart() {
        LocalDateTime result = session.startSleepingSession();
        Assertions.assertEquals(LocalDateTime.parse("01.10.25 23:15", FORMATTER), result);
    }

    @Test
    public void testSleepingSessionTestGetEnd() {
        LocalDateTime result = session.endSleepingSession();
        Assertions.assertEquals(LocalDateTime.parse("02.10.25 07:30", FORMATTER), result);
    }

    @Test
    public void testSleepingSessionTestGetQuality() {
        SleepQuality result = session.qualitySleepingSession();
        Assertions.assertEquals(SleepQuality.GOOD, result);
    }

}
