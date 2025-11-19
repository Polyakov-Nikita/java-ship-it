package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.FragileParcel;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;
import ru.yandex.practicum.delivery.parcel.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {
    private static final int MIN_WEIGHT = 1;
    private static final int SOME_WEIGHT = 100;

    @Test
    public void shouldCalculateStandardParcelCostWithMinWeight() {
        StandardParcel parcel = createStandardParcelWithWeight(MIN_WEIGHT);
        assertEquals(MIN_WEIGHT * StandardParcel.BASE_COST, parcel.calculateDeliveryCost());
    }

    private static StandardParcel createStandardParcelWithWeight(int weight) {
        return new StandardParcel("", weight, "", 1);
    }

    @Test
    public void shouldCalculateStandardParcelCostWithSomeWeight() {
        StandardParcel parcel = createStandardParcelWithWeight(SOME_WEIGHT);
        assertEquals(SOME_WEIGHT * StandardParcel.BASE_COST, parcel.calculateDeliveryCost());
    }

    @Test
    public void shouldCalculateFragileParcelCostWithMinWeight() {
        FragileParcel parcel = createFragileParcelWithWeight(MIN_WEIGHT);
        assertEquals(MIN_WEIGHT * FragileParcel.BASE_COST, parcel.calculateDeliveryCost());
    }

    private static FragileParcel createFragileParcelWithWeight(int weight) {
        return new FragileParcel("", weight, "", 1);
    }

    @Test
    public void shouldCalculateFragileParcelCostWithSomeWeight() {
        FragileParcel parcel = createFragileParcelWithWeight(SOME_WEIGHT);
        assertEquals(SOME_WEIGHT * FragileParcel.BASE_COST, parcel.calculateDeliveryCost());
    }

    @Test
    public void shouldCalculatePerishableParcelCostWithMinWeight() {
        PerishableParcel parcel = createPerishableParcelWithWeight(MIN_WEIGHT);
        assertEquals(MIN_WEIGHT * PerishableParcel.BASE_COST, parcel.calculateDeliveryCost());
    }

    private static PerishableParcel createPerishableParcelWithWeight(int weight) {
        return new PerishableParcel("", weight, "", 1, 1);
    }

    @Test
    public void shouldCalculatePerishableParcelCostWithSomeWeight() {
        PerishableParcel parcel = createPerishableParcelWithWeight(SOME_WEIGHT);
        assertEquals(SOME_WEIGHT * PerishableParcel.BASE_COST, parcel.calculateDeliveryCost());
    }
}
