package nl.noaskers.happyBoosters.commands;

import nl.noaskers.happyBoosters.HappyBoosters;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SubCommand implements CommandExecutor {

    private final HappyBoosters plugin;

    public SubCommand(HappyBoosters plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reloadConfig();
        plugin.getGuiManager().reloadGuiConfig();
        sender.sendMessage("Configuration reloaded.");
        return true;
    }
}