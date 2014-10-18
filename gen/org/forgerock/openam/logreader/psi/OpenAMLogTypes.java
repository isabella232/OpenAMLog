// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.forgerock.openam.logreader.psi.impl.OpenAMLogLogImpl;
import org.forgerock.openam.logreader.psi.impl.OpenAMLogLogcontentImpl;
import org.forgerock.openam.logreader.psi.impl.OpenAMLogLogtitleImpl;

public interface OpenAMLogTypes {

    IElementType LOG = new OpenAMLogElementType("LOG");
    IElementType LOGCONTENT = new OpenAMLogElementType("LOGCONTENT");
    IElementType LOGTITLE = new OpenAMLogElementType("LOGTITLE");

    IElementType COMMENT = new OpenAMLogTokenType("COMMENT");
    IElementType CRLF = new OpenAMLogTokenType("CRLF");
    IElementType DATE = new OpenAMLogTokenType("DATE");
    IElementType DEBUG_NAME = new OpenAMLogTokenType("DEBUG_NAME");
    IElementType END_OF_LOG_CONTENT = new OpenAMLogTokenType("END_OF_LOG_CONTENT");
    IElementType LOG_LINE = new OpenAMLogTokenType("LOG_LINE");
    IElementType SEPARATOR = new OpenAMLogTokenType("SEPARATOR");
    IElementType THREAD_NAME = new OpenAMLogTokenType("THREAD_NAME");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == LOG) {
                return new OpenAMLogLogImpl(node);
            } else if (type == LOGCONTENT) {
                return new OpenAMLogLogcontentImpl(node);
            } else if (type == LOGTITLE) {
                return new OpenAMLogLogtitleImpl(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
