package me.hsgamer.bettergui.alternativecommandlistener;

import me.hsgamer.hscore.config.annotation.ConfigPath;

import java.util.Collections;
import java.util.List;

public interface CommandConfig {
    @ConfigPath(value = "ignored-commands", converter = StringListConverter.class)
    default List<String> getIgnoredCommands() {
        return Collections.singletonList("warp test");
    }

    @ConfigPath("case-insensitive")
    default boolean isCaseInsensitive() {
        return true;
    }

    @ConfigPath("should-ignore")
    default boolean isShouldIgnore() {
        return true;
    }

    void reloadConfig();
}
