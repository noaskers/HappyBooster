package nl.noaskers.happyBoosters.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import nl.noaskers.happyBoosters.HappyBoosters;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoosterGUI {

    private final HappyBoosters plugin;
    private final Player player;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public BoosterGUI(HappyBoosters plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void open() {
        String title = plugin.getGuiManager().getGuiConfig().getString("gui.title", "Booster GUI");
        String type = plugin.getGuiManager().getGuiConfig().getString("gui.type", "CHEST");
        int rows = plugin.getGuiManager().getGuiConfig().getInt("gui.rows", 6);

        GuiType guiType = GuiType.valueOf(type.toUpperCase());
        Gui gui = Gui.gui(guiType)
                .title(miniMessage.deserialize(title))
                .rows(rows)
                .disableAllInteractions()
                .create();

        ConfigurationSection fillerSection = plugin.getGuiManager().getGuiConfig().getConfigurationSection("gui.filler");
        if (fillerSection != null) {
            Material fillerMaterial = Material.valueOf(fillerSection.getString("material", "GRAY_STAINED_GLASS_PANE"));
            String fillerType = fillerSection.getString("type", "BORDER");
            // Add filler logic here based on the type and other configurations
            // Example: gui.getFiller().fill(fillerMaterial);
        }

        ConfigurationSection itemsSection = plugin.getGuiManager().getGuiConfig().getConfigurationSection("gui.items");
        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
                if (itemSection != null) {
                    Material material = Material.valueOf(itemSection.getString("material", "STONE"));
                    String name = itemSection.getString("name", "");
                    List<Component> lore = itemSection.getStringList("lore").stream()
                            .map(line -> miniMessage.deserialize("<reset>" + line)
                                    .decoration(TextDecoration.ITALIC, false))
                            .collect(Collectors.toList());
                    List<String> slots = itemSection.getStringList("slots");
                    ConfigurationSection clickActions = itemSection.getConfigurationSection("click-actions");

                    GuiItem guiItem = ItemBuilder.from(material)
                            .name(miniMessage.deserialize("<reset>" + name).decoration(TextDecoration.ITALIC, false))
                            .lore(lore)
                            .asGuiItem(event -> {
                                event.setCancelled(true);
                                if (clickActions != null) {
                                    handleItemClick(event, clickActions);
                                }
                            });

                    for (String slot : slots) {
                        if (slot.contains("-")) {
                            String[] range = slot.split("-");
                            int start = Integer.parseInt(range[0].trim());
                            int end = Integer.parseInt(range[1].trim());
                            for (int i = start; i <= end; i++) {
                                gui.setItem(i, guiItem);
                            }
                        } else {
                            int slotIndex = Integer.parseInt(slot.trim());
                            gui.setItem(slotIndex, guiItem);
                        }
                    }
                }
            }
        }

        gui.open(player);
    }

    private void handleItemClick(InventoryClickEvent event, ConfigurationSection clickActions) {
        ClickType clickType = event.getClick();
        if (clickType.isLeftClick() && clickActions.contains("left")) {
            performAction(event, clickActions.getString("left"));
        } else if (clickType.isRightClick() && clickActions.contains("right")) {
            performAction(event, clickActions.getString("right"));
        } else if (clickType.isShiftClick() && clickActions.contains("shift")) {
            performAction(event, clickActions.getString("shift"));
        } else if (clickType == ClickType.DROP && clickActions.contains("drop")) {
            performAction(event, clickActions.getString("drop"));
        }
    }

    private void performAction(InventoryClickEvent event, String action) {
        if ("close".equalsIgnoreCase(action)) {
            event.getWhoClicked().closeInventory();
        } else if ("next-page".equalsIgnoreCase(action)) {
            // Handle next page action
        } else if ("previous-page".equalsIgnoreCase(action)) {
            // Handle previous page action
        }
        // Add more actions if needed
    }
}