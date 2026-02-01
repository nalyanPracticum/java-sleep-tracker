package main.java.ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public record SleepingSession(LocalDateTime startSleepingSession, LocalDateTime endSleepingSession,
                              SleepQuality qualitySleepingSession) {

    @Override
    public String toString() {
        return "start: " + startSleepingSession +
                " end: " + endSleepingSession +
                " quality: " + qualitySleepingSession;
    }
}