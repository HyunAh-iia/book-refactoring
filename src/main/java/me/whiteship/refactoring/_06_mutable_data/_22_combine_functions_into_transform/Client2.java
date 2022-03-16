package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform;

import java.time.Month;
import java.time.Year;

public class Client2 extends ReadingClient {

    private double base;
    private double taxableCharge;

    public Client2(Reading reading) {
        this.base = enrichReading(reading).baseCharge();
        // 만약 taxableCharge 가 여러군 데서 사용된다면, 이것도 변환함수에 옮겨도 좋다.
        this.taxableCharge = Math.max(0, this.base - taxThreshold(reading.year()));
    }

    public double getBase() {
        return base;
    }

    public double getTaxableCharge() {
        return taxableCharge;
    }
}
