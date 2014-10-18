package org.forgerock.openam.logreader;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.forgerock.openam.logreader.psi.OpenAMLogTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class OpenAMLogSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey DEBUG_NAME = createTextAttributesKey("OPENAMLOG_DEBUG_NAME", new TextAttributes(new Color(125, 125, 125), null, null, null, Font.PLAIN));
    public static final TextAttributesKey DATE = createTextAttributesKey("OPENAMLOG_DATE_NAME", new TextAttributes(new Color(91, 91, 91), null, null, null, Font.PLAIN));
    public static final TextAttributesKey THREAD_NAME = createTextAttributesKey("OPENAMLOG_THREAD_NAME", new TextAttributes(new Color(144, 144, 144), null, null, null, Font.PLAIN));
    public static final TextAttributesKey LOG_LINE = createTextAttributesKey("OPENAMLOG_LOG_LINE", new TextAttributes(new Color(36, 36, 128), null, null, null, Font.BOLD));

    public static final TextAttributesKey COMMENT = createTextAttributesKey("OPENAMLOG_COMMENT", SyntaxHighlighterColors.LINE_COMMENT);

    static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("OPENAMLOG_BAD_CHARACTER",
            new TextAttributes(Color.RED, null, null, null, Font.BOLD));

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