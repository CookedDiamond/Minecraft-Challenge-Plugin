package org.cookeddiamond.challengeplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class ChallengePlugin extends JavaPlugin {

    public final DropEventListeners listener;
    public final SetRandomSeedCommand setRandomSeedCommand;

    public ChallengePlugin() {
        listener = new DropEventListeners();
        setRandomSeedCommand = new SetRandomSeedCommand(listener);
    }

    @Override
    public void onEnable() {
        getCommand("set_random").setExecutor(setRandomSeedCommand);
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
