// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.forgerock.openam.logreader.psi.impl.*;

public interface OpenAMLogTypes {

  IElementType LOGTITLE = new OpenAMLogElementType("LOGTITLE");
  IElementType PROPERTY = new OpenAMLogElementType("PROPERTY");

  IElementType COMMENT = new OpenAMLogTokenType("COMMENT");
  IElementType CRLF = new OpenAMLogTokenType("CRLF");
  IElementType DATE = new OpenAMLogTokenType("DATE");
  IElementType DEBUG_NAME = new OpenAMLogTokenType("DEBUG_NAME");
  IElementType KEY = new OpenAMLogTokenType("KEY");
  IElementType LOG_HEADER = new OpenAMLogTokenType("LOG_HEADER");
  IElementType SEPARATOR = new OpenAMLogTokenType("SEPARATOR");
  IElementType THREAD_NAME = new OpenAMLogTokenType("THREAD_NAME");
  IElementType VALUE = new OpenAMLogTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == LOGTITLE) {
        return new OpenAMLogLogtitleImpl(node);
      }
      else if (type == PROPERTY) {
        return new OpenAMLogPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
