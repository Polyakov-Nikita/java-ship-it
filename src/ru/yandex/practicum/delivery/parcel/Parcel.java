package ru.yandex.practicum.delivery.parcel;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы
    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    public final int calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    protected abstract int getBaseCost();

    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована");
    }
}