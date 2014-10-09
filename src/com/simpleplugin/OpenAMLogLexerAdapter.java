package com.simpleplugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by qcastel on 09/10/2014.
 */
public class OpenAMLogLexerAdapter extends FlexAdapter {
    public OpenAMLogLexerAdapter() {
        super(new OpenAMLogLexer((Reader) null));
    }
}
