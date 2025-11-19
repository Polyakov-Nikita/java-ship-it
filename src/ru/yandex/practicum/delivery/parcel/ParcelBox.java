package ru.yandex.practicum.delivery.parcel;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final String name;
    private final String letter;
    private final int maxWeight;
    private final ArrayList<T> parcels;
    private int currentWeight;

    public ParcelBox(String name, String letter, int maxWeight) {
        this.name = name;
        this.letter = letter;
        this.maxWeight = maxWeight;
        parcels = new ArrayList<>();
        currentWeight = 0;
    }

    public String getName() {
        return name;
    }

    public String getLetter() {
        return letter;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void add(T parcel) {
        int newWeightKg = currentWeight + parcel.getWeight();
        if(newWeightKg > maxWeight) {
            System.out.println("Максимальный вес коробки \"" + name + "\" превышен, посылка <<"
                    + parcel.getDescription() + ">> не добавлена");
            return;
        }
        parcels.add(parcel);
        currentWeight = newWeightKg;
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }
}
