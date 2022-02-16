package me.whiteship.refactoring._03_long_function._09_preserve_whole_object;

import java.util.HashMap;
import java.util.Map;

public record Participant(String username, Map<Integer, Boolean> homework) {
    public Participant(String username) {
        this(username, new HashMap<>());
    }

    public void setHomeworkDone(int index) {
        this.homework.put(index, true);
    }

    // 인텔리제이 refactor - move instance method 기능을 이용해 연관이 있는 Participant 메소드로 이동시킴
    // 물론 내마음처럼 찰떡같이 해주지는 않으니 옮겨놓고 조정 필요
    // 이 메서드가 과연 그 클래스에 의존하는게 맞는가? 이 위치(원래 StudyDashboard에 있었음)에 있는게 맞는가 대한 고민을 하다가 Participant 로 옮김
    double getRate(int totalNumberOfEvents) {
        long count = homework().values().stream()
                .filter(v -> v == true)
                .count();
        return (double) (count * 100 / totalNumberOfEvents);
    }
}
