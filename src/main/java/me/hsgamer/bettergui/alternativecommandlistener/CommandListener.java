package me.hsgamer.bettergui.alternativecommandlistener;

import me.hsgamer.bettergui.BetterGUI;
import me.hsgamer.bettergui.manager.MenuCommandManager;
import me.hsgamer.hscore.collections.map.CaseInsensitiveStringMap;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandListener implements Listener {
    private static final Pattern SPACE_PATTERN = Pattern.compile("\\s");
    private final AlternativeCommandListener addon;

    public CommandListener(AlternativeCommandListener addon) {
        this.addon = addon;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }

        String rawCommand = event.getMessage().substring(1);
        if (addon.getConfig().getIgnoredCommands().stream().anyMatch(s -> addon.getConfig().isCaseInsensitive() ? s.equalsIgnoreCase(rawCommand) : s.equals(rawCommand)) == addon.getConfig().isShouldIgnore()) {
            return;
        }

        String[] split = SPACE_PATTERN.split(rawCommand);
        String command = split[0];
        String[] args = new String[0];
        if (split.length > 1) {
            args = Arrays.copyOfRange(split, 1, split.length);
        }

        Map<String, Command> menuCommand = BetterGUI.getInstance().get(MenuCommandManager.class).getRegisteredMenuCommand();
        if (addon.getConfig().isCaseInsensitive()) {
            menuCommand = new CaseInsensitiveStringMap<>(menuCommand);
        }

        if (menuCommand.containsKey(command)) {
            event.setCancelled(true);
            menuCommand.get(command).execute(event.getPlayer(), command, args);
        }
    }
}
