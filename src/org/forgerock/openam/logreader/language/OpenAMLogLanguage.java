package org.forgerock.openam.logreader.language;

import com.intellij.lang.Language;

public class OpenAMLogLanguage extends Language {
    public static final OpenAMLogLanguage INSTANCE = new OpenAMLogLanguage();

    private OpenAMLogLanguage() {
        super("OpenAMLog");
    }
}