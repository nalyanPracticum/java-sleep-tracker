package main.java.ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepAnalysisResult {
    public String title;
    public Object result;

    public SleepAnalysisResult(String title, Object result) {
        this.title = title;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    @Override
    public String toString() {
        return title + ": " + result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepAnalysisResult result1 = (SleepAnalysisResult) o;
        return Objects.equals(title, result1.title) && Objects.equals(result, result1.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, result);
    }
}
