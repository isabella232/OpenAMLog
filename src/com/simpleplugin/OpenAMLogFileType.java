package com.simpleplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class OpenAMLogFileType extends LanguageFileType {
    public static final OpenAMLogFileType INSTANCE = new OpenAMLogFileType();

    private OpenAMLogFileType() {
        super(OpenAMLogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Simple file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Simple language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "lfr";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return OpenAMLogIcons.FILE;
    }
}