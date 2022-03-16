package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class ReadingClientTest {
    @Test
    void client1() {
        Client1 client = new Client1(acquireReading());
        assertEquals(100d, client.getBaseCharge());
    }

    @Test
    void client2() {
        Client2 client = new Client2(acquireReading());
        assertEquals(100d, client.getBase());
        assertEquals(95d, client.getTaxableCharge());
    }

    @Test
    void client3() {
        Client3 client = new Client3(acquireReading());
        assertEquals(100d, client.getBasicChargeAmount());
    }

    private Reading acquireReading() {
        return new Reading("customer", 10, Month.MARCH, Year.of(2022));
    }
}