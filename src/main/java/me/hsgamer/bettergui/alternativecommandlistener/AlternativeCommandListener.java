package me.hsgamer.bettergui.alternativecommandlistener;

import me.hsgamer.bettergui.api.addon.GetPlugin;
import me.hsgamer.bettergui.api.addon.Reloadable;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.proxy.ConfigGenerator;
import me.hsgamer.hscore.expansion.common.Expansion;
import me.hsgamer.hscore.expansion.extra.expansion.DataFolder;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.io.File;

public final class AlternativeCommandListener implements Expansion, GetPlugin, Reloadable, DataFolder {
    private final CommandConfig config = ConfigGenerator.newInstance(CommandConfig.class, new BukkitConfig(new File(getDataFolder(), "config.yml")));
    private final CommandListener commandListener = new CommandListener(this);

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(commandListener, getPlugin());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(commandListener);
    }

    @Override
    public void onReload() {
        config.reloadConfig();
    }

    public CommandConfig getConfig() {
        return config;
    }
}
