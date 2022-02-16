package me.whiteship.refactoring._03_long_function._13_replace_conditional_with_polymorphism;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyDashboard {

    private final int totalNumberOfEvents;
    private final List<Participant> participants;

    public StudyDashboard(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;
        participants = new CopyOnWriteArrayList<>();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard studyDashboard = new StudyDashboard(15);
        studyDashboard.print();
    }

    private void print() throws IOException, InterruptedException {
        checkGithubIssues(getGhRepository());
        // 다형성을 활용해서 조건문을 없앴음
        // 만약 동적으로 변환하길 원하면 팩토리 메서드를 만들어서 삭제했던 PrintMode에 따라 스위치로 분기해서 인스턴스를 반환해줄 수도 있겠지
        // (백기선님) 근데 동적이어도 변경하려면 어차피 넘겨주는 변수값을 수정해야하니까, 그럴바에는 직접 사용할 프린터를 지정하는것도 괜찮을듯
        new ConsolePrinter(this.totalNumberOfEvents, this.participants).execute();
    }

    private GHRepository getGhRepository() throws IOException {
        GitHub gitHub = GitHub.connect();
        return gitHub.getRepository("whiteship/live-study");
    }

    private void checkGithubIssues(GHRepository repository) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalNumberOfEvents);

        for (int index = 1 ; index <= totalNumberOfEvents ; index++) {
            int eventId = index;
            service.execute(() -> {
                try {
                    GHIssue issue = repository.getIssue(eventId);
                    checkHomework(issue.getComments(), participants, eventId);
                    latch.countDown();
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            });
        }

        latch.await();
        service.shutdown();
    }

    private void checkHomework(List<GHIssueComment> comments, List<Participant> participants, int eventId) {
        for (GHIssueComment comment : comments) {
            Participant participant = findParticipant(comment.getUserName(), participants);
            participant.setHomeworkDone(eventId);
        }
    }

    private Participant findParticipant(String username, List<Participant> participants) {
        return isNewParticipant(username, participants) ?
                createNewParticipant(username, participants) :
                findExistingParticipant(username, participants);
    }

    private Participant findExistingParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        return participant;
    }

    private Participant createNewParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = new Participant(username);
        participants.add(participant);
        return participant;
    }

    private boolean isNewParticipant(String username, List<Participant> participants) {
        return participants.stream().noneMatch(p -> p.username().equals(username));
    }

}
