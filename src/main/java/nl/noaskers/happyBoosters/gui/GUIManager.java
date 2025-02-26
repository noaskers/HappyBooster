package nl.noaskers.happyBoosters.gui;

import nl.noaskers.happyBoosters.HappyBoosters;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GUIManager {

    private final HappyBoosters plugin;
    private FileConfiguration guiConfig;
    private File guiConfigFile;

    public GUIManager(HappyBoosters plugin) {
        this.plugin = plugin;
        loadGuiConfig();
    }

    public void loadGuiConfig() {
        guiConfigFile = new File(plugin.getDataFolder(), "gui.yml");
        if (!guiConfigFile.exists()) {
            plugin.saveResource("gui.yml", false);
        }
        guiConfig = YamlConfiguration.loadConfiguration(guiConfigFile);
    }

    public void reloadGuiConfig() {
        if (guiConfigFile == null) {
            guiConfigFile = new File(plugin.getDataFolder(), "gui.yml");
        }
        guiConfig = YamlConfiguration.loadConfiguration(guiConfigFile);
    }

    public FileConfiguration getGuiConfig() {
        return guiConfig;
    }
}