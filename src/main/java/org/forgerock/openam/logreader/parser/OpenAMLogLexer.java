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

/* The following code was generated by JFlex 1.4.3 on 10/11/14 8:12 PM */

package org.forgerock.openam.logreader.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.forgerock.openam.logreader.psi.OpenAMLogTypes;


/**
 * Lexer Adapter
 *
 * Auto-generated from flex
 *
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
class OpenAMLogLexer implements FlexLexer {
    /**
     * initial size of the lookahead buffer
     */
    private static final int ZZ_BUFFERSIZE = 16384;

    /**
     * lexical states
     */
    public static final int WAIT_DATE = 8;
    public static final int WAIT_DEBUG_NAME = 4;
    public static final int WAIT_THREAD_NAME = 12;
    public static final int YYINITIAL = 0;
    public static final int WAIT_SEPARATOR_BETWEEN_DEBUG_NAME_AND_DATE = 6;
    public static final int WAIT_LOG_VALUE = 16;
    public static final int WAIT_END_LINE = 14;
    public static final int WAIT_SEPARATOR_BETWEEN_DATE_AND_THREAD_NAME = 10;
    public static final int CONTENT = 2;

    /**
     * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
     * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
     * at the beginning of a line
     * l is of the form l = 2*k, k a non negative integer
     */
    private static final int ZZ_LEXSTATE[] = {
            0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7,
            8, 8
    };

    /**
     * Translates characters to character classes
     */
    private static final String ZZ_CMAP_PACKED =
            "\11\0\1\3\1\1\1\0\1\3\1\2\22\0\1\3\1\4\1\0" +
                    "\1\4\13\0\1\11\12\6\1\5\2\0\1\0\3\0\1\7\13\0" +
                    "\1\10\2\0\1\7\13\0\1\0\uffa3\0";

    /**
     * Translates characters to character classes
     */
    private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

    /**
     * Translates DFA states to action switch labels.
     */
    private static final int[] ZZ_ACTION = zzUnpackAction();

    private static final String ZZ_ACTION_PACKED_0 =
            "\11\0\1\1\2\2\1\3\1\1\3\4\1\5\1\6" +
                    "\1\7\1\6\1\10\1\11\1\12\1\1\1\3\1\4" +
                    "\1\0\1\1\2\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\0\1\1\1\3\1\4\1\0\1\1\1\3\1\4" +
                    "\1\13\1\1\1\3\1\4\1\1\1\0\1\3\1\4" +
                    "\1\0\1\1\1\0\1\3\1\4\1\0\2\14\2\15";

    private static int[] zzUnpackAction() {
        int[] result = new int[150];
        int offset = 0;
        offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }


    /**
     * Translates a state to a row index in the transition table
     */
    private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

    private static final String ZZ_ROWMAP_PACKED_0 =
            "\0\0\0\12\0\24\0\36\0\50\0\62\0\74\0\106" +
                    "\0\120\0\132\0\144\0\156\0\170\0\202\0\214\0\226" +
                    "\0\240\0\252\0\144\0\144\0\264\0\144\0\276\0\144" +
                    "\0\310\0\322\0\334\0\346\0\360\0\372\0\u0104\0\u010e" +
                    "\0\u0118\0\u0122\0\u012c\0\u0136\0\u0140\0\u014a\0\u0154\0\u015e" +
                    "\0\u0168\0\u0172\0\u017c\0\u0186\0\u0190\0\u019a\0\u01a4\0\u01ae" +
                    "\0\u01b8\0\u01c2\0\u01cc\0\u01d6\0\u01e0\0\u01ea\0\u01f4\0\u01fe" +
                    "\0\u0208\0\u0212\0\u021c\0\u0226\0\u0230\0\u023a\0\u0244\0\u024e" +
                    "\0\u0258\0\u0262\0\u026c\0\u0276\0\u0280\0\u028a\0\u0294\0\u029e" +
                    "\0\u02a8\0\u02b2\0\u02bc\0\u02c6\0\u02d0\0\u02da\0\u02e4\0\u02ee" +
                    "\0\u02f8\0\u0302\0\u030c\0\u0316\0\u0320\0\u032a\0\u0334\0\u033e" +
                    "\0\u0348\0\u0352\0\u035c\0\u0366\0\u0370\0\u037a\0\u0384\0\u038e" +
                    "\0\u0398\0\u03a2\0\u03ac\0\u03b6\0\u03c0\0\u03ca\0\u03d4\0\u03de" +
                    "\0\u03e8\0\u03f2\0\u03fc\0\u0406\0\u0410\0\u041a\0\u0424\0\u042e" +
                    "\0\u0438\0\u0442\0\u044c\0\u0456\0\u0460\0\u046a\0\u0474\0\u047e" +
                    "\0\u0488\0\u0492\0\u049c\0\u04a6\0\u04b0\0\u04ba\0\u04c4\0\u04ce" +
                    "\0\u04d8\0\u04e2\0\u04ec\0\u04f6\0\u04d8\0\u0500\0\u050a\0\u0514" +
                    "\0\u051e\0\u0528\0\u0532\0\u053c\0\u0546\0\u0550\0\u055a\0\u0564" +
                    "\0\u056e\0\u0578\0\u0550\0\u055a\0\u056e\0\u0578";

    private static int[] zzUnpackRowMap() {
        int[] result = new int[150];
        int offset = 0;
        offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;  /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    /**
     * The transition table of the DFA
     */
    private static final int[] ZZ_TRANS = zzUnpackTrans();

    private static final String ZZ_TRANS_PACKED_0 =
            "\1\12\1\13\1\14\1\12\1\15\1\16\4\12\1\17" +
                    "\1\13\1\20\2\17\1\21\4\17\1\22\1\0\3\22" +
                    "\1\23\4\22\1\23\1\0\3\23\1\24\5\23\1\0" +
                    "\4\23\1\25\4\23\1\0\3\23\1\26\4\23\1\27" +
                    "\1\0\3\27\1\23\4\27\1\23\1\30\11\23\1\0" +
                    "\10\23\1\12\1\0\3\12\1\31\4\12\12\0\1\12" +
                    "\1\13\3\12\1\31\4\12\1\15\1\0\1\12\2\15" +
                    "\1\32\4\15\1\16\1\0\10\16\1\17\1\0\3\17" +
                    "\1\33\5\17\1\13\3\17\1\33\4\17\1\21\1\0" +
                    "\10\21\1\22\1\0\3\22\1\0\4\22\6\0\1\34" +
                    "\3\0\1\27\1\0\3\27\1\0\4\27\1\16\1\0" +
                    "\4\16\1\35\3\16\1\36\1\0\1\16\3\36\1\37" +
                    "\3\36\1\21\1\0\4\21\1\40\3\21\11\0\1\41" +
                    "\1\16\1\0\4\16\1\42\3\16\1\36\1\0\1\16" +
                    "\10\36\1\0\1\16\3\36\1\43\3\36\1\21\1\0" +
                    "\4\21\1\44\3\21\6\0\1\45\3\0\1\16\1\0" +
                    "\7\16\1\46\1\36\1\0\1\16\6\36\1\47\1\21" +
                    "\1\0\7\21\1\50\6\0\1\51\3\0\1\16\1\0" +
                    "\4\16\1\52\3\16\1\36\1\0\1\16\3\36\1\53" +
                    "\3\36\1\21\1\0\4\21\1\54\3\21\11\0\1\55" +
                    "\1\16\1\0\4\16\1\56\3\16\1\36\1\0\1\16" +
                    "\3\36\1\57\3\36\1\21\1\0\4\21\1\60\3\21" +
                    "\6\0\1\61\3\0\1\16\1\0\7\16\1\62\1\36" +
                    "\1\0\1\16\6\36\1\63\1\21\1\0\7\21\1\64" +
                    "\6\0\1\65\3\0\1\16\1\0\4\16\1\66\3\16" +
                    "\1\36\1\0\1\16\3\36\1\67\3\36\1\21\1\0" +
                    "\4\21\1\70\3\21\6\0\1\71\3\0\1\16\1\0" +
                    "\4\16\1\72\3\16\1\36\1\0\1\16\3\36\1\73" +
                    "\3\36\1\21\1\0\4\21\1\74\3\21\6\0\1\75" +
                    "\3\0\1\16\1\0\4\16\1\76\3\16\1\36\1\0" +
                    "\1\16\3\36\1\77\3\36\1\21\1\0\4\21\1\100" +
                    "\3\21\3\0\1\101\6\0\1\16\1\0\4\16\1\102" +
                    "\3\16\1\36\1\0\1\16\3\36\1\103\3\36\1\21" +
                    "\1\0\4\21\1\104\3\21\6\0\1\105\3\0\1\16" +
                    "\1\0\1\16\1\106\6\16\1\36\1\0\1\16\1\107" +
                    "\6\36\1\21\1\0\1\21\1\110\6\21\6\0\1\111" +
                    "\3\0\1\16\1\0\4\16\1\112\3\16\1\36\1\0" +
                    "\1\16\3\36\1\113\3\36\1\21\1\0\4\21\1\114" +
                    "\3\21\5\0\1\115\4\0\1\16\1\0\4\16\1\116" +
                    "\3\16\1\36\1\0\1\16\3\36\1\117\3\36\1\21" +
                    "\1\0\4\21\1\120\3\21\6\0\1\121\3\0\1\16" +
                    "\1\0\3\16\1\122\4\16\1\36\1\0\1\16\2\36" +
                    "\1\123\4\36\1\21\1\0\3\21\1\124\4\21\6\0" +
                    "\1\125\3\0\1\16\1\0\4\16\1\126\3\16\1\36" +
                    "\1\0\1\16\3\36\1\127\3\36\1\21\1\0\4\21" +
                    "\1\130\3\21\5\0\1\131\4\0\1\16\1\0\4\16" +
                    "\1\132\3\16\1\36\1\0\1\16\3\36\1\133\3\36" +
                    "\1\21\1\0\4\21\1\134\3\21\6\0\1\135\3\0" +
                    "\1\16\1\0\3\16\1\136\4\16\1\36\1\0\1\16" +
                    "\2\36\1\137\4\36\1\21\1\0\3\21\1\140\4\21" +
                    "\6\0\1\141\3\0\1\16\1\0\4\16\1\142\3\16" +
                    "\1\36\1\0\1\16\3\36\1\143\3\36\1\21\1\0" +
                    "\4\21\1\144\3\21\5\0\1\145\4\0\1\16\1\0" +
                    "\4\16\1\146\3\16\1\36\1\0\1\16\3\36\1\147" +
                    "\3\36\1\21\1\0\4\21\1\150\3\21\6\0\1\151" +
                    "\3\0\1\16\1\0\3\16\1\152\4\16\1\36\1\0" +
                    "\1\16\2\36\1\153\4\36\1\21\1\0\3\21\1\154" +
                    "\4\21\6\0\1\155\3\0\1\16\1\0\4\16\1\156" +
                    "\3\16\1\36\1\0\1\16\3\36\1\157\3\36\1\21" +
                    "\1\0\4\21\1\160\3\21\6\0\1\161\3\0\1\16" +
                    "\1\0\4\16\1\162\3\16\1\36\1\0\1\16\3\36" +
                    "\1\163\3\36\1\21\1\0\4\21\1\164\3\21\3\0" +
                    "\1\165\6\0\1\16\1\0\4\16\1\166\3\16\1\36" +
                    "\1\0\1\16\3\36\1\167\3\36\1\21\1\0\4\21" +
                    "\1\170\3\21\7\0\1\171\2\0\1\16\1\0\1\16" +
                    "\1\172\6\16\1\36\1\0\1\16\1\173\6\36\1\21" +
                    "\1\0\1\21\1\174\6\21\10\0\1\175\1\0\1\16" +
                    "\1\0\5\16\1\176\2\16\1\36\1\0\1\16\4\36" +
                    "\1\177\2\36\1\21\1\0\5\21\1\200\2\21\3\0" +
                    "\1\201\6\0\1\16\1\0\6\16\1\202\1\16\1\36" +
                    "\1\0\1\16\5\36\1\203\1\36\1\21\1\0\6\21" +
                    "\1\204\1\21\5\205\1\0\4\205\1\16\1\0\1\16" +
                    "\1\206\6\16\1\36\1\0\1\16\1\207\6\36\1\21" +
                    "\1\0\1\21\1\210\6\21\1\211\1\212\3\211\1\16" +
                    "\4\211\1\213\1\212\1\211\2\213\1\36\4\213\1\214" +
                    "\1\215\3\214\1\21\4\214\1\211\1\212\3\211\1\216" +
                    "\4\211\5\212\1\217\4\212\1\213\1\212\1\211\2\213" +
                    "\1\220\4\213\1\214\1\215\3\214\1\221\4\214\5\215" +
                    "\1\222\4\215\1\223\1\0\3\223\1\16\4\223\1\224" +
                    "\1\0\3\224\1\0\4\224\1\220\1\0\1\223\2\220" +
                    "\1\36\4\220\1\225\1\0\3\225\1\21\4\225\1\226" +
                    "\1\0\3\226\1\0\4\226";

    private static int[] zzUnpackTrans() {
        int[] result = new int[1410];
        int offset = 0;
        offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            value--;
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }


    /* error codes */
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final char[] EMPTY_BUFFER = new char[0];
    private static final int YYEOF = -1;
    private static java.io.Reader zzReader = null; // Fake

    /* error messages for the codes above */
    private static final String ZZ_ERROR_MSG[] = {
            "Unkown internal scanner error",
            "Error: could not match input",
            "Error: pushback value was too large"
    };

    /**
     * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
     */
    private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

    private static final String ZZ_ATTRIBUTE_PACKED_0 =
            "\11\0\1\1\1\11\7\1\2\11\1\1\1\11\1\1" +
                    "\1\11\3\1\1\0\4\1\1\0\3\1\1\0\3\1" +
                    "\1\0\3\1\1\0\3\1\1\0\3\1\1\0\3\1" +
                    "\1\0\3\1\1\0\3\1\1\0\3\1\1\0\3\1" +
                    "\1\0\3\1\1\0\3\1\1\0\3\1\1\0\3\1" +
                    "\1\0\3\1\1\0\3\1\1\0\3\1\1\0\3\1" +
                    "\1\0\3\1\1\0\3\1\1\0\3\1\1\0\3\1" +
                    "\1\0\3\1\1\0\3\1\1\0\10\1\1\0\2\1" +
                    "\1\0\1\1\1\0\2\1\1\0\4\1";

    private static int[] zzUnpackAttribute() {
        int[] result = new int[150];
        int offset = 0;
        offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }

    /**
     * the current state of the DFA
     */
    private int zzState;

    /**
     * the current lexical state
     */
    private int zzLexicalState = YYINITIAL;

    /**
     * this buffer contains the current text to be matched and is
     * the source of the yytext() string
     */
    private CharSequence zzBuffer = "";

    /**
     * this buffer may contains the current text array to be matched when it is cheap to acquire it
     */
    private char[] zzBufferArray;

    /**
     * the textposition at the last accepting state
     */
    private int zzMarkedPos;

    /**
     * the textposition at the last state to be included in yytext
     */
    private int zzPushbackPos;

    /**
     * the current text position in the buffer
     */
    private int zzCurrentPos;

    /**
     * startRead marks the beginning of the yytext() string in the buffer
     */
    private int zzStartRead;

    /**
     * endRead marks the last character in the buffer, that has been read
     * from input
     */
    private int zzEndRead;

    /**
     * zzAtBOL == true <=> the scanner is currently at the beginning of a line
     */
    private boolean zzAtBOL = true;

    /**
     * zzAtEOF == true <=> the scanner is at the EOF
     */
    private boolean zzAtEOF;

    /**
     * denotes if the user-EOF-code has already been executed
     */
    private boolean zzEOFDone;

    /* user code: */
    StringBuffer string = new StringBuffer();


    OpenAMLogLexer(java.io.Reader in) {
        this.zzReader = in;
    }

    /**
     * Creates a new scanner.
     * There is also java.io.Reader version of this constructor.
     *
     * @param in the java.io.Inputstream to read input from.
     */
    OpenAMLogLexer(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
    }

    /**
     * Unpacks the compressed character translation table.
     *
     * @param packed the packed character translation table
     * @return the unpacked character translation table
     */
    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[0x10000];
        int i = 0;  /* index in packed string  */
        int j = 0;  /* index in unpacked array */
        while (i < 52) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do map[j++] = value; while (--count > 0);
        }
        return map;
    }

    public final int getTokenStart() {
        return zzStartRead;
    }

    public final int getTokenEnd() {
        return getTokenStart() + yylength();
    }

    public void reset(CharSequence buffer, int start, int end, int initialState) {
        zzBuffer = buffer;
        zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
        zzCurrentPos = zzMarkedPos = zzStartRead = start;
        zzPushbackPos = 0;
        zzAtEOF = false;
        zzAtBOL = true;
        zzEndRead = end;
        yybegin(initialState);
    }

    /**
     * Refills the input buffer.
     *
     * @return <code>false</code>, iff there was new input.
     * @throws java.io.IOException if any I/O-Error occurs
     */
    private boolean zzRefill() throws java.io.IOException {
        return true;
    }


    /**
     * Returns the current lexical state.
     */
    public final int yystate() {
        return zzLexicalState;
    }


    /**
     * Enters a new lexical state
     *
     * @param newState the new lexical state
     */
    public final void yybegin(int newState) {
        zzLexicalState = newState;
    }


    /**
     * Returns the text matched by the current regular expression.
     */
    public final CharSequence yytext() {
        return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
    }


    /**
     * Returns the character at position <tt>pos</tt> from the
     * matched text.
     * <p/>
     * It is equivalent to yytext().charAt(pos), but faster
     *
     * @param pos the position of the character to fetch.
     *            A value from 0 to yylength()-1.
     * @return the character at position pos
     */
    public final char yycharat(int pos) {
        return zzBufferArray != null ? zzBufferArray[zzStartRead + pos] : zzBuffer.charAt(zzStartRead + pos);
    }


    /**
     * Returns the length of the matched text region.
     */
    public final int yylength() {
        return zzMarkedPos - zzStartRead;
    }


    /**
     * Reports an error that occured while scanning.
     * <p/>
     * In a wellformed scanner (no or only correct usage of
     * yypushback(int) and a match-all fallback rule) this method
     * will only be called with things that "Can't Possibly Happen".
     * If this method is called, something is seriously wrong
     * (e.g. a JFlex bug producing a faulty scanner etc.).
     * <p/>
     * Usual syntax/scanner level error handling should be done
     * in error fallback rules.
     *
     * @param errorCode the code of the errormessage to display
     */
    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
        }

        throw new Error(message);
    }


    /**
     * Pushes the specified amount of characters back into the input stream.
     * <p/>
     * They will be read again by then next call of the scanning method
     *
     * @param number the number of characters to be read again.
     *               This number must not be greater than yylength()!
     */
    public void yypushback(int number) {
        if (number > yylength())
            zzScanError(ZZ_PUSHBACK_2BIG);

        zzMarkedPos -= number;
    }


    /**
     * Contains user EOF-code, which will be executed exactly once,
     * when the end of file is reached
     */
    private void zzDoEOF() {
        if (!zzEOFDone) {
            zzEOFDone = true;

        }
    }


    /**
     * Resumes scanning until the next regular expression is matched,
     * the end of input is encountered or an I/O-Error occurs.
     *
     * @return the next token
     * @throws java.io.IOException if any I/O-Error occurs
     */
    public IElementType advance() throws java.io.IOException {
        int zzInput;
        int zzAction;

        // cached fields:
        int zzCurrentPosL;
        int zzMarkedPosL;
        int zzEndReadL = zzEndRead;
        CharSequence zzBufferL = zzBuffer;
        char[] zzBufferArrayL = zzBufferArray;
        char[] zzCMapL = ZZ_CMAP;

        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;

        while (true) {
            zzMarkedPosL = zzMarkedPos;

            zzAction = -1;

            zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

            zzState = ZZ_LEXSTATE[zzLexicalState];


            zzForAction:
            {
                while (true) {

                    if (zzCurrentPosL < zzEndReadL)
                        zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
                    else if (zzAtEOF) {
                        zzInput = YYEOF;
                        break zzForAction;
                    } else {
                        // store back cached positions
                        zzCurrentPos = zzCurrentPosL;
                        zzMarkedPos = zzMarkedPosL;
                        boolean eof = zzRefill();
                        // get translated positions and possibly new buffer
                        zzCurrentPosL = zzCurrentPos;
                        zzMarkedPosL = zzMarkedPos;
                        zzBufferL = zzBuffer;
                        zzEndReadL = zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                            break zzForAction;
                        } else {
                            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
                        }
                    }
                    int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
                    if (zzNext == -1) break zzForAction;
                    zzState = zzNext;

                    int zzAttributes = zzAttrL[zzState];
                    if ((zzAttributes & 1) == 1) {
                        zzAction = zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) == 8) break zzForAction;
                    }

                }
            }

            // store back cached position
            zzMarkedPos = zzMarkedPosL;

            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 13: {
                    yypushback(yytext().length());
                    yybegin(WAIT_DEBUG_NAME);
                    return OpenAMLogTypes.END_OF_LOG_CONTENT;
                }
                case 14:
                    break;
                case 11: {
                    yybegin(WAIT_SEPARATOR_BETWEEN_DATE_AND_THREAD_NAME);
                    return OpenAMLogTypes.DATE;
                }
                case 15:
                    break;
                case 7: {
                    yybegin(WAIT_DATE);
                    return OpenAMLogTypes.SEPARATOR;
                }
                case 16:
                    break;
                case 2: {
                    return OpenAMLogTypes.CRLF;
                }
                case 17:
                    break;
                case 12: {
                    yypushback(yytext().length());
                    yybegin(WAIT_DEBUG_NAME);
                }
                case 18:
                    break;
                case 9: {
                    yybegin(WAIT_END_LINE);
                    return OpenAMLogTypes.THREAD_NAME;
                }
                case 19:
                    break;
                case 5: {
                    yybegin(WAIT_SEPARATOR_BETWEEN_DEBUG_NAME_AND_DATE);
                    return OpenAMLogTypes.DEBUG_NAME;
                }
                case 20:
                    break;
                case 1: {
                    yypushback(yytext().length());
                    yybegin(CONTENT);
                }
                case 21:
                    break;
                case 8: {
                    yybegin(WAIT_THREAD_NAME);
                    return OpenAMLogTypes.SEPARATOR;
                }
                case 22:
                    break;
                case 10: {
                    yybegin(YYINITIAL);
                    return OpenAMLogTypes.CRLF;
                }
                case 23:
                    break;
                case 3: {
                    yybegin(YYINITIAL);
                    return OpenAMLogTypes.COMMENT;
                }
                case 24:
                    break;
                case 6: {
                    return TokenType.BAD_CHARACTER;
                }
                case 25:
                    break;
                case 4: {
                    return OpenAMLogTypes.LOG_LINE;
                }
                case 26:
                    break;
                default:
                    if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
                        zzAtEOF = true;
                        zzDoEOF();
                        switch (zzLexicalState) {
                            case CONTENT: {
                                yypushback(yytext().length());
                                yybegin(YYINITIAL);
                                return OpenAMLogTypes.END_OF_LOG_CONTENT;
                            }
                            case 151:
                                break;
                            default:
                                return null;
                        }
                    } else {
                        zzScanError(ZZ_NO_MATCH);
                    }
            }
        }
    }


}
