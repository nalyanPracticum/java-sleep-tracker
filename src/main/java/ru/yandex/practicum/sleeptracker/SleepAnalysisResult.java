package main.java.ru.yandex.practicum.sleeptracker;

public record SleepAnalysisResult(String title, Object result) {

    @Override
    public String toString() {
        return title + ": " + result;
    }
}
