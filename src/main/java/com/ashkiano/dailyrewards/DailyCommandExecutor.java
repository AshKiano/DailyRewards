package com.ashkiano.dailyrewards;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DailyCommandExecutor implements CommandExecutor {
    private final DailyRewards plugin;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DailyCommandExecutor(DailyRewards plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getName();
        String today = dateFormat.format(new Date());
        String lastClaimed = plugin.getConfig().getString("rewards." + playerName, "");

        if (today.equals(lastClaimed)) {
            player.sendMessage("You have already claimed your daily reward today.");
            return true;
        }

        List<String> rewardCommands = plugin.getConfig().getStringList("reward-commands");
        for (String rewardCommand : rewardCommands) {
            rewardCommand = rewardCommand.replace("%player%", playerName);
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), rewardCommand);
        }
        player.sendMessage("You have claimed your daily rewards.");

        plugin.getConfig().set("rewards." + playerName, today);
        plugin.saveConfig();

        return true;
    }
}