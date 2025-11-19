package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpiredTest {
    private static final int FIRST_DAY = 1;
    private static final int DAYS_TO_LIVE = 5;
    private static PerishableParcel parcel;

    @BeforeEach
    public void createParcel() {
        parcel = new PerishableParcel("", 1, "", FIRST_DAY, DAYS_TO_LIVE);
    }

    @Test
    public void shouldNotExpiredInFirstDay() {
        assertFalse(parcel.isExpired(FIRST_DAY));
    }

    @Test
    public void shouldNotExpiredInLastDay() {
        assertFalse(parcel.isExpired(FIRST_DAY + DAYS_TO_LIVE));
    }

    @Test
    public void shouldNotExpiredInBounds() {
        assertFalse(parcel.isExpired(FIRST_DAY + DAYS_TO_LIVE / 2));
    }

    @Test
    public void shouldExpiredAfterOneDay() {
        assertTrue(parcel.isExpired(FIRST_DAY + DAYS_TO_LIVE + 1));
    }

    @Test
    public void shouldExpiredAfterFewDays() {
        assertTrue(parcel.isExpired(FIRST_DAY + DAYS_TO_LIVE + 3));
    }
}
