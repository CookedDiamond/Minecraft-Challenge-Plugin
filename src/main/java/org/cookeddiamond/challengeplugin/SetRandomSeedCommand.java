package org.cookeddiamond.challengeplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetRandomSeedCommand implements CommandExecutor {

    private final DropEventListeners listeners;

    public SetRandomSeedCommand(DropEventListeners listener) {
        this.listeners = listener;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            int seed = Integer.parseInt(strings[0]);
            listeners.setSeed(seed);
            commandSender.sendMessage("set seed to " + strings[0]);
            return true;
        }
        catch (NumberFormatException e) {
            commandSender.sendMessage(strings[0] + " is not a number.");
            return false;
        }
    }
}
