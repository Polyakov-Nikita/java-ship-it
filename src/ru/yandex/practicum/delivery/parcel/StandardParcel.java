package ru.yandex.practicum.delivery.parcel;

public class StandardParcel extends Parcel {
    public static final int BASE_COST = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}
