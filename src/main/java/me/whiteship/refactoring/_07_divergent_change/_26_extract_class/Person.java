package me.whiteship.refactoring._07_divergent_change._26_extract_class;

public class Person {

    private final TelephoneNumber officeTelephoneNumber;
    private String name;

    public Person(TelephoneNumber telephonenumber, String name) {
        this.officeTelephoneNumber = telephonenumber;
        this.name = name;
    }

    public String telephoneNumber() {
        return this.officeTelephoneNumber.toString();
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TelephoneNumber getTelephoneNumber() {
        return officeTelephoneNumber;
    }
}
