package me.whiteship.refactoring._03_long_function._01_before;

import java.util.HashMap;
import java.util.Map;

/*
냄새 3. 긴 함수
Long Function
• 짧은 함수 vs 긴 함수
  • 함수가 길 수록 더 이해하기 어렵다. vs 짧은 함수는 더 많은 문맥 전환을 필요로 한다.
  • “과거에는” 작은 함수를 사용하는 경우에 더 많은 서브루틴 호출로 인한 오버헤드가 있었다.
  • 작은 함수에 “좋은 이름”을 사용했다면 해당 함수의 코드를 보지 않고도 이해할 수 있다.
  • 어떤 코드에 “주석”을 남기고 싶다면, 주석 대신 함수를 만들고 함수의 이름으로 “의도”를 표현해보자.
• 사용할 수 있는 리팩토링 기술
  • 99%는, “함수 추출하기 (Extract Function)”로 해결할 수 있다.
  • 함수로 분리하면서 해당 함수로 전달해야 할 매개변수가 많아진다면 다음과 같은 리팩토링을 고려해볼 수 있다.
    • 임시 변수를 질의 함수로 바꾸기 (Replace Temp with Query)
    • 매개변수 객체 만들기 (Introduce Parameter Object)
    • 객체 통째로 넘기기 (Preserve Whole Object)
  • “조건문 분해하기 (Decompose Conditional)”를 사용해 조건문을 분리할 수 있다.
  • 같은 조건으로 여러개의 Swtich 문이 있다면, “조건문을 다형성으로 바꾸기 (Replace Conditional with Polymorphism)”을 사용할 수 있다.
  • 반복문 안에서 여러 작업을 하고 있어서 하나의 메소드로 추출하기 어렵다면, “반복문 쪼개기 (Split Loop)”를 적용할 수 있다.
*/
public record Participant(String username, Map<Integer, Boolean> homework) {
    public Participant(String username) {
        this(username, new HashMap<>());
    }

    public double getRate(double total) {
        long count = this.homework.values().stream()
                .filter(v -> v == true)
                .count();
        return count * 100 / total;
    }

    public void setHomeworkDone(int index) {
        this.homework.put(index, true);
    }

}
