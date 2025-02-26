package nl.noaskers.happyBoosters;

import nl.noaskers.happyBoosters.commands.BoosterCommand;
import nl.noaskers.happyBoosters.gui.GUIManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HappyBoosters extends JavaPlugin {

    private GUIManager guiManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        guiManager = new GUIManager(this);
        getCommand("booster").setExecutor(new BoosterCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }
}