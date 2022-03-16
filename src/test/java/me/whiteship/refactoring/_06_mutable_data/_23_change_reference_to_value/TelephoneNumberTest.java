package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneNumberTest {
    @Test
    void testEquals() {
        TelephoneNumber number1 = new TelephoneNumber("123", "010-1234-5678");
        TelephoneNumber number2 = new TelephoneNumber("123", "010-1234-5678");
        assertEquals(number1, number2);
    }
}