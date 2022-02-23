package me.whiteship.refactoring._04_long_parameter_list._16_combine_functinos_into_class;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/*
리팩토링 16 을 통해 새로운 StudyPrinter 클래스에 유사한 메서드들이 모였음

4. Long Parameter List: 16. Combine Functions into Class

여러 함수를 클래스로 묶기
• 비슷한 매개변수 목록을 여러 함수에서 사용하고 있다면 해당 메소드를 모아서 클래스를 만들 수 있다.
• 클래스 내부로 메소드를 옮기고, 데이터를 필드로 만들면 메소드에 전달해야 하는 매개변수 목록도 줄일 수 있다.
* */
public class StudyPrinter {

    private int totalNumberOfEvents;

    private List<Participant> participants;

    public StudyPrinter(int totalNumberOfEvents, List<Participant> participants) {
        this.totalNumberOfEvents = totalNumberOfEvents;
        this.participants = participants;
    }

    public void print() throws IOException {
        try (FileWriter fileWriter = new FileWriter("participants.md");
             PrintWriter writer = new PrintWriter(fileWriter)) {
            this.participants.sort(Comparator.comparing(Participant::username));

            writer.print(header());

            this.participants.forEach(p -> {
                String markdownForHomework = getMarkdownForParticipant(p.username(), p.homework());
                writer.print(markdownForHomework);
            });
        }
    }

    private String getMarkdownForParticipant(String username, Map<Integer, Boolean> homework) {
        return String.format("| %s %s | %.2f%% |\n", username, checkMark(homework), getRate(homework));
    }

    double getRate(Map<Integer, Boolean> homework) {
        long count = homework.values().stream()
                .filter(v -> v == true)
                .count();
        return (double) (count * 100 / this.totalNumberOfEvents);
    }

    /**
     * | 참여자 (420) | 1주차 | 2주차 | 3주차 | 참석율 |
     * | --- | --- | --- | --- | --- |
     */
    private String header() {
        StringBuilder header = new StringBuilder(String.format("| 참여자 (%d) |", this.participants.size()));

        for (int index = 1; index <= this.totalNumberOfEvents; index++) {
            header.append(String.format(" %d주차 |", index));
        }
        header.append(" 참석율 |\n");

        header.append("| --- ".repeat(Math.max(0, this.totalNumberOfEvents + 2)));
        header.append("|\n");

        return header.toString();
    }

    /**
     * |:white_check_mark:|:white_check_mark:|:white_check_mark:|:x:|
     */
    private String checkMark(Map<Integer, Boolean> homework) {
        StringBuilder line = new StringBuilder();
        for (int i = 1 ; i <= this.totalNumberOfEvents ; i++) {
            if(homework.containsKey(i) && homework.get(i)) {
                line.append("|:white_check_mark:");
            } else {
                line.append("|:x:");
            }
        }
        return line.toString();
    }
}
