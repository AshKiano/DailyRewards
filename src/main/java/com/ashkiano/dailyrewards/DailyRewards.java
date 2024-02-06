package com.ashkiano.dailyrewards;

import org.bukkit.plugin.java.JavaPlugin;

public class DailyRewards extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("daily").setExecutor(new DailyCommandExecutor(this));
        this.getLogger().info("Thank you for using the DailyRewards plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @Override
    public void onDisable() {
    }
}

