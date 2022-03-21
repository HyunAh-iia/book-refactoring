package me.whiteship.refactoring._07_divergent_change._25_move_function;

public class AccountType {
    private boolean premium;

    public AccountType(boolean premium) {
        this.premium = premium;
    }

    public boolean isPremium() {
        return this.premium;
    }

    // 책에서는 Account를 통째로 넘겨주는 예제도 존재함
    // 그렇게 되면 Account에 있는 데이터를 많이 참조하게 되는 것
    // 그러면 Account에 위치시키는게 타당하지 않을까 싶어서 백기선님은 매개변수로 필요한 것만 넘겨주는 것을 추천한다고 함
    protected double overdraftCharge(int daysOverdrawn) {
        if (isPremium()) {
            final int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge + (daysOverdrawn - 7) * 0.85;
            }
        } else {
            return daysOverdrawn * 1.75;
        }
    }
}
