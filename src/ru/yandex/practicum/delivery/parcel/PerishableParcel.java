package ru.yandex.practicum.delivery.parcel;

public class PerishableParcel extends Parcel {
    public static final int BASE_COST = 3;
    private final int daysToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int daysToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.daysToLive = daysToLive;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) {
        return sendDay + daysToLive < currentDay;
    }
}
