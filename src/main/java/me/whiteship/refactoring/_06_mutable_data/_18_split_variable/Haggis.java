package me.whiteship.refactoring._06_mutable_data._18_split_variable;

public class Haggis {

    private double primaryForce;
    private double secondaryForce;
    private double mass;
    private int delay;

    public Haggis(double primaryForce, double secondaryForce, double mass, int delay) {
        this.primaryForce = primaryForce;
        this.secondaryForce = secondaryForce;
        this.mass = mass;
        this.delay = delay;
    }

    public double distanceTravelled(int time) {
        double result; // 값을 누적시키기에 사용하므로 변수를 나눌 필요 없음!
        final double primaryAcceleration = primaryForce / mass; // 가속도 = 추진력/질량
        final int primaryTime = Math.min(time, delay); // 첫번째 추진력으로 이동한 시간
        result = 0.5 * primaryAcceleration * primaryTime * primaryTime; // 첫번째 추진력으로 이동한 거리

        final int secondaryTime = time - delay; // 두번째 추진력으로 이동한 시간
        if (secondaryTime > 0) {
            final double primaryVelocity = primaryAcceleration * delay; // 속도
            final double secondaryAcceleration = (primaryForce + secondaryForce) / mass; // 가속도
            result += primaryVelocity * secondaryTime + 0.5 * secondaryAcceleration * secondaryTime + secondaryTime; // 두번째 추진력으로 이동한 거리
        }

        return result;
    }
}
