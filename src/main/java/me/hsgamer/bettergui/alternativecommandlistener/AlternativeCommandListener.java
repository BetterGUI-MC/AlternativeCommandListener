package me.hsgamer.bettergui.alternativecommandlistener;

import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public final class AlternativeCommandListener extends PluginAddon {
    private final CommandConfig config = new CommandConfig(this);
    private final CommandListener commandListener = new CommandListener(this);

    @Override
    public void onEnable() {
        config.setup();
        Bukkit.getPluginManager().registerEvents(commandListener, getPlugin());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(commandListener);
    }

    @Override
    public void onReload() {
        config.reload();
    }

    public CommandConfig getConfig() {
        return config;
    }
}
