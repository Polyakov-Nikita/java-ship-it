package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddParcelToBoxTest {
    private static final int MAX_WEIGHT = 10;
    private static final int SOME_WEIGHT_1 = 6;
    private static final int SOME_WEIGHT_2 = 3;

    @Test
    public void shouldAddStandardParcelToEmptyBoxWhenWeightIsEnough() {
        ParcelBox<StandardParcel> box = createBox();
        StandardParcel parcel = createStandardParcelWithWeight(MAX_WEIGHT / 2);
        assertBoxAddition(box, parcel);
    }

    private <T extends Parcel> ParcelBox<T> createBox() {
        return new ParcelBox<>("", "", MAX_WEIGHT);
    }

    private static StandardParcel createStandardParcelWithWeight(int weight) {
        return new StandardParcel("", weight, "", 1);
    }

    private static <T extends Parcel> void assertBoxAddition(ParcelBox<T> box, T parcel) {
        int previousSize = box.getAllParcels().size();
        box.add(parcel);
        assertEquals(previousSize + 1, box.getAllParcels().size());
    }

    @Test
    public void shouldAddFragileParcelToEmptyBoxWhenWeightIsEnough() {
        ParcelBox<FragileParcel> box = createBox();
        FragileParcel parcel = createFragileParcelWithWeight(MAX_WEIGHT / 2);
        assertBoxAddition(box, parcel);
    }

    private static FragileParcel createFragileParcelWithWeight(int weight) {
        return new FragileParcel("", weight, "", 1);
    }

    @Test
    public void shouldAddPerishableParcelToEmptyBoxWhenWeightIsEnough() {
        ParcelBox<PerishableParcel> box = createBox();
        PerishableParcel parcel = createPerishableParcelWithWeight(MAX_WEIGHT / 2);
        assertBoxAddition(box, parcel);
    }

    private static PerishableParcel createPerishableParcelWithWeight(int weight) {
        return new PerishableParcel("", weight, "", 1, 1);
    }

    @Test
    public void shouldAddStandardParcelToEmptyBoxWhenWeightIsMax() {
        ParcelBox<StandardParcel> box = createBox();
        StandardParcel parcel = createStandardParcelWithWeight(MAX_WEIGHT);
        assertBoxAddition(box, parcel);
    }

    @Test
    public void shouldAddFragileParcelToEmptyBoxWhenWeightIsMax() {
        ParcelBox<FragileParcel> box = createBox();
        FragileParcel parcel = createFragileParcelWithWeight(MAX_WEIGHT);
        assertBoxAddition(box, parcel);
    }

    @Test
    public void shouldAddPerishableParcelToEmptyBoxWhenWeightIsMax() {
        ParcelBox<PerishableParcel> box = createBox();
        PerishableParcel parcel = createPerishableParcelWithWeight(MAX_WEIGHT);
        assertBoxAddition(box, parcel);
    }

    @Test
    public void shouldAddStandardParcelToPartiallyFilledBoxWhenWeightIsEnough() {
        ParcelBox<StandardParcel> box = createBox();
        StandardParcel firstParcel = createStandardParcelWithWeight(SOME_WEIGHT_1);
        box.add(firstParcel);
        StandardParcel secondParcel = createStandardParcelWithWeight(SOME_WEIGHT_2);
        assertBoxAddition(box, secondParcel);
    }

    @Test
    public void shouldAddFragileParcelToPartiallyFilledBoxWhenWeightIsEnough() {
        ParcelBox<FragileParcel> box = createBox();
        FragileParcel firstParcel = createFragileParcelWithWeight(SOME_WEIGHT_1);
        box.add(firstParcel);
        FragileParcel secondParcel = createFragileParcelWithWeight(SOME_WEIGHT_2);
        assertBoxAddition(box, secondParcel);
    }

    @Test
    public void shouldAddPerishableParcelToPartiallyFilledBoxWhenWeightIsEnough() {
        ParcelBox<PerishableParcel> box = createBox();
        PerishableParcel firstParcel = createPerishableParcelWithWeight(SOME_WEIGHT_1);
        box.add(firstParcel);
        PerishableParcel secondParcel = createPerishableParcelWithWeight(SOME_WEIGHT_2);
        assertBoxAddition(box, secondParcel);
    }

    @Test
    public void shouldNotAddStandardParcelToPartiallyFilledBoxWhenWeightIsNotEnough() {
        ParcelBox<StandardParcel> box = createBox();
        StandardParcel firstParcel = createStandardParcelWithWeight(SOME_WEIGHT_1);
        box.add(firstParcel);
        StandardParcel secondParcel = createStandardParcelWithWeight(MAX_WEIGHT);
        assertBoxNoChanges(box, secondParcel);
    }

    private static <T extends Parcel> void assertBoxNoChanges(ParcelBox<T> box, T parcel) {
        int previousSize = box.getAllParcels().size();
        box.add(parcel);
        assertEquals(previousSize, box.getAllParcels().size());
    }

    @Test
    public void shouldNotAddFragileParcelToPartiallyFilledBoxWhenWeightIsNotEnough() {
        ParcelBox<FragileParcel> box = createBox();
        FragileParcel firstParcel = createFragileParcelWithWeight(SOME_WEIGHT_1);
        box.add(firstParcel);
        FragileParcel secondParcel = createFragileParcelWithWeight(MAX_WEIGHT);
        assertBoxNoChanges(box, secondParcel);
    }

    @Test
    public void shouldNotAddPerishableParcelToPartiallyFilledBoxWhenWeightIsNotEnough() {
        ParcelBox<PerishableParcel> box = createBox();
        PerishableParcel firstParcel = createPerishableParcelWithWeight(SOME_WEIGHT_1);
        box.add(firstParcel);
        PerishableParcel secondParcel = createPerishableParcelWithWeight(MAX_WEIGHT);
        assertBoxNoChanges(box, secondParcel);
    }

    @Test
    public void shouldNotAddStandardParcelToFilledBox() {
        ParcelBox<StandardParcel> box = createBox();
        StandardParcel firstParcel = createStandardParcelWithWeight(MAX_WEIGHT);
        box.add(firstParcel);
        StandardParcel secondParcel = createStandardParcelWithWeight(SOME_WEIGHT_2);
        assertBoxNoChanges(box, secondParcel);
    }

    @Test
    public void shouldNotAddFragileParcelToFilledBox() {
        ParcelBox<FragileParcel> box = createBox();
        FragileParcel firstParcel = createFragileParcelWithWeight(MAX_WEIGHT);
        box.add(firstParcel);
        FragileParcel secondParcel = createFragileParcelWithWeight(SOME_WEIGHT_2);
        assertBoxNoChanges(box, secondParcel);
    }

    @Test
    public void shouldNotAddPerishableParcelToFilledBox() {
        ParcelBox<PerishableParcel> box = createBox();
        PerishableParcel firstParcel = createPerishableParcelWithWeight(MAX_WEIGHT);
        box.add(firstParcel);
        PerishableParcel secondParcel = createPerishableParcelWithWeight(SOME_WEIGHT_2);
        assertBoxNoChanges(box, secondParcel);
    }
}
