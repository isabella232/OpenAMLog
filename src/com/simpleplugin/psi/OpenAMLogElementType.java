package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.OpenAMLogLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OpenAMLogElementType extends IElementType {
    public OpenAMLogElementType(@NotNull @NonNls String debugName) {
        super(debugName, OpenAMLogLanguage.INSTANCE);
    }
}