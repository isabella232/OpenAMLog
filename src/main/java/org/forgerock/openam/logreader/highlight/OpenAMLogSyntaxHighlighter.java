/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright 2014 ForgeRock AS".
 *
 * Copyright 2014 ForgeRock AS.
 */

package org.forgerock.openam.logreader.highlight;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.forgerock.openam.logreader.parser.OpenAMLogLexerAdapter;
import org.forgerock.openam.logreader.psi.OpenAMLogTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey DEBUG_NAME = createTextAttributesKey("OPENAMLOG_DEBUG_NAME", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DATE = createTextAttributesKey("OPENAMLOG_DATE_NAME", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey THREAD_NAME = createTextAttributesKey("OPENAMLOG_THREAD_NAME", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey LOG_LINE = createTextAttributesKey("OPENAMLOG_LOG_LINE",
            DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);

    public static final TextAttributesKey COMMENT = createTextAttributesKey("OPENAMLOG_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

    static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("OPENAMLOG_BAD_CHARACTER",
            DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] DEBUG_NAME_KEYS = new TextAttributesKey[]{DEBUG_NAME};
    private static final TextAttributesKey[] DATE_KEYS = new TextAttributesKey[]{DATE};
    private static final TextAttributesKey[] THREAD_NAME_KEYS = new TextAttributesKey[]{THREAD_NAME};
    private static final TextAttributesKey[] LOG_LINE_KEYS = new TextAttributesKey[]{LOG_LINE};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new OpenAMLogLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(OpenAMLogTypes.DEBUG_NAME)) {
            return DEBUG_NAME_KEYS;
        } else if (tokenType.equals(OpenAMLogTypes.DATE)) {
            return DATE_KEYS;
        } else if (tokenType.equals(OpenAMLogTypes.THREAD_NAME)) {
            return THREAD_NAME_KEYS;
        } else if (tokenType.equals(OpenAMLogTypes.LOG_LINE)) {
            return LOG_LINE_KEYS;
        } else if (tokenType.equals(OpenAMLogTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}