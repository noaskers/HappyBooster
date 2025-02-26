package nl.noaskers.happyBoosters.commands;

import nl.noaskers.happyBoosters.HappyBoosters;
import nl.noaskers.happyBoosters.gui.BoosterGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoosterCommand implements CommandExecutor, TabCompleter {

    private final HappyBoosters plugin;
    private final SubCommand subCommand;

    public BoosterCommand(HappyBoosters plugin) {
        this.plugin = plugin;
        this.subCommand = new SubCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            return subCommand.onCommand(sender, command, label, args);
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            new BoosterGUI(plugin, player).open();
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> subCommands = new ArrayList<>();
            subCommands.add("reload");
            return subCommands;
        }
        return Collections.emptyList();
    }
}