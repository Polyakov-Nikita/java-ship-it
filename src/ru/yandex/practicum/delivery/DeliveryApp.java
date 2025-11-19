package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.commands.Command;
import ru.yandex.practicum.delivery.commands.CommandExecutor;
import ru.yandex.practicum.delivery.parcel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {
    private static boolean running = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static final CommandExecutor commandExecutor = new CommandExecutor() {{
        add(new Command("Добавить посылку", DeliveryApp::addParcel));
        add(new Command("Отправить все посылки", DeliveryApp::sendParcels));
        add(new Command("Посчитать стоимость доставки", DeliveryApp::calculateCosts));
        add(new Command("Сменить место отслеживаемых посылок", DeliveryApp::changeLocation));
        add(new Command("Показать содержимое коробки", DeliveryApp::showParcelsInBox));
        add(new Command("Завершить", DeliveryApp::exit));
    }};
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final ArrayList<Trackable> trackableParcels = new ArrayList<>();
    private static final HashMap<String, ParcelCreator> creators = new HashMap<>() {{
        put("1", DeliveryApp::createStandardParcel);
        put("2", DeliveryApp::createFragileParcel);
        put("3", DeliveryApp::createPerishablParcel);
    }};
    private static final ParcelBox<StandardParcel> standardParcelsBox = new ParcelBox<>("обычные", "s", 50);
    private static final ParcelBox<FragileParcel> fragileParcelsBox = new ParcelBox<>("хрупкие", "f", 30);
    private static final ParcelBox<PerishableParcel> perishableParcelsBox = new ParcelBox<>("скоропортящиеся", "p", 10);

    public static void main(String[] args) {
        while (running) {
            showMenu();
            String command = takeString("Введите команду");
            if (commandExecutor.containsCommand(command)) {
                commandExecutor.execute(command);
            } else {
                printError("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        for (String commandDescription : commandExecutor.getCommandDescriptions()) {
            System.out.println(commandDescription);
        }
    }

    private static String takeString(String message) {
        while (true) {
            printMessage(message);
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            printError("Строка не должна быть пустой.");
        }
    }

    private static void printMessage(String message) {
        System.out.print(message + ": ");
    }

    private static void printError(String message) {
        System.out.println("ОШИБКА: " + message);
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        String type = takeParcelType();
        Parcel parcel = creators.get(type).create();
        allParcels.add(parcel);
    }

    private static String takeParcelType() {
        String input;
        while (true) {
            printMessage("Выберите тип посылки(1 - обычная, 2 - хрупкая, 3 - скоропортящаяся)");
            input = scanner.nextLine();
            if (creators.containsKey(input)) {
                return input;
            }
            printError("Такого типа нет.");
        }
    }

    private static Parcel createStandardParcel() {
        String description = takeString("Введите описание");
        int weight = takeInt("Введите вес посылки");
        String deliveryAddress = takeString("Введите адрес");
        int sendDay = takeInt("Введите день отправки");
        StandardParcel parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
        standardParcelsBox.add(parcel);

        return parcel;
    }

    private static int takeInt(String message) {
        while (true) {
            int input;

            printMessage(message);
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printError("Некорректный ввод");
                continue;
            }
            if (input > 0) {
                return input;
            }
            printError("Число должно быть положительным.");
        }
    }

    private static Parcel createFragileParcel() {
        String description = takeString("Введите описание");
        int weight = takeInt("Введите вес посылки");
        String deliveryAddress = takeString("Введите адрес");
        int sendDay = takeInt("Введите день отправки");
        FragileParcel parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
        fragileParcelsBox.add(parcel);
        trackableParcels.add(parcel);

        return parcel;
    }

    private static Parcel createPerishablParcel() {
        String description = takeString("Введите описание");
        int weight = takeInt("Введите вес посылки");
        String deliveryAddress = takeString("Введите адрес");
        int sendDay = takeInt("Введите день отправки");
        int daysToLive = takeInt("Введите срок хранения (дн.)");
        PerishableParcel parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, daysToLive);
        perishableParcelsBox.add(parcel);

        return parcel;
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sum = 0;

        for (Parcel parcel : allParcels) {
            int currentCost = parcel.calculateDeliveryCost();
            sum += currentCost;
            System.out.println(parcel.getDescription() + ": " + currentCost + " руб.");
        }
        System.out.println("Общая стоимость доставок: " + sum);
    }

    private static void changeLocation() {
        String newLocation = takeString("Введите новое место для отслеживаемых посылок");
        for (Trackable trackableParcel : trackableParcels) {
            trackableParcel.reportStatus(newLocation);
        }
    }

    private static void showParcelsInBox() {
        while (true) {
            String letter = takeString("Какую коробку смотреть("
                    + standardParcelsBox.getLetter() + " - " + standardParcelsBox.getName() + ", "
                    + fragileParcelsBox.getLetter() + " - " + fragileParcelsBox.getName() + ", "
                    + perishableParcelsBox.getLetter() + " - " + perishableParcelsBox.getName() + ")");
            if (letter.equals(standardParcelsBox.getLetter())) {
                printBoxContents(standardParcelsBox);
                return;
            } else if (letter.equals(fragileParcelsBox.getLetter())) {
                printBoxContents(fragileParcelsBox);
                return;
            } else if (letter.equals(perishableParcelsBox.getLetter())) {
                printBoxContents(perishableParcelsBox);
                return;
            } else {
                printError("Такой коробки нет.");
            }
        }
    }

    private static void printBoxContents(ParcelBox<? extends Parcel> parcelBox) {
        System.out.println("Коробка с надписью \"" + parcelBox.getName() + "\"(" + parcelBox.getCurrentWeight() + "/" + parcelBox.getMaxWeight() + " кг.):");
        for (Parcel parcel : parcelBox.getAllParcels()) {
            System.out.println(parcel.getDescription() + ", вес: " + parcel.getWeight() + " кг.");
        }
    }

    private static void exit() {
        running = false;
    }
}
