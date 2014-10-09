package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.OpenAMLogLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OpenAMLogTokenType extends IElementType {
    public OpenAMLogTokenType(@NotNull @NonNls String debugName) {
        super(debugName, OpenAMLogLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SimpleTokenType." + super.toString();
    }
}