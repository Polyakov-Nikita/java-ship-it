package ru.yandex.practicum.delivery.commands;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandExecutor {
    private final HashMap<String, Command> commands;

    public CommandExecutor() {
        commands = new HashMap<>();
    }

    public void add(Command command) {
        commands.put(Integer.toString(commands.size() + 1), command);
    }

    public ArrayList<String> getCommandDescriptions() {
        ArrayList<String> commandDescriptions = new ArrayList<>();

        for (String key : commands.keySet()) {
            commandDescriptions.add(key + " - " + commands.get(key).getDescription());
        }

        return commandDescriptions;
    }

    public boolean containsCommand(String key) {
        return commands.containsKey(key);
    }

    public void execute(String key) {
        commands.get(key).execute();
    }
}
