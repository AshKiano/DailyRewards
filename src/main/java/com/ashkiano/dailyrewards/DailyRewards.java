package com.ashkiano.dailyrewards;

import org.bukkit.plugin.java.JavaPlugin;

public class DailyRewards extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("daily").setExecutor(new DailyCommandExecutor(this));
    }

    @Override
    public void onDisable() {
    }
}

