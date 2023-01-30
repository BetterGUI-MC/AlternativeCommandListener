package me.hsgamer.bettergui.alternativecommandlistener;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.annotated.AnnotatedConfig;
import me.hsgamer.hscore.config.annotation.ConfigPath;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class CommandConfig extends AnnotatedConfig {
    @ConfigPath(value = "ignored-commands", converter = StringListConverter.class)
    public final List<String> ignoredCommands;
    @ConfigPath("case-insensitive")
    public final boolean caseInsensitive;
    @ConfigPath("should-ignore")
    public final boolean shouldIgnore;

    public CommandConfig(AlternativeCommandListener addon) {
        super(new BukkitConfig(new File(addon.getDataFolder(), "config.yml")));
        ignoredCommands = Collections.singletonList("warp test");
        caseInsensitive = true;
        shouldIgnore = true;
    }
}
