package me.hsgamer.bettergui.alternativecommandlistener;

import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.annotation.converter.impl.SimpleConverter;

public class StringListConverter extends SimpleConverter {
    public StringListConverter() {
        super(CollectionUtils::createStringListFromObject);
    }
}
