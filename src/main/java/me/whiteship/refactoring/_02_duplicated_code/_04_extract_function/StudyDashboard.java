package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    // Extract Function을 활용함 :) 유용하니 실무에서도 단축키 사용하도록 연습하기
    // 여기에서는 중복제거를 위해 사용했지만 다른 냄새 제거에도 유용하게 사용되는 기능임
    // printParticipants 와 printReviewers도 인텔리제이의 refactor - inline 으로 한줄로 묶을 수 있는데, 다음에 알려주기로 함
    private void printParticipants(int eventId) throws IOException {
        GHIssue issue = getGhIssue(eventId);
        Set<String> participants = getUserNames(issue);
        print(participants);
    }

    private void printReviewers() throws IOException {
        GHIssue issue = getGhIssue(30);
        Set<String> reviewers = getUserNames(issue);
        print(reviewers);
    }

    private void print(Set<String> participants) {
        participants.forEach(System.out::println);
    }

    private Set<String> getUserNames(GHIssue issue) throws IOException {
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));
        return participants;
    }

    private GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
