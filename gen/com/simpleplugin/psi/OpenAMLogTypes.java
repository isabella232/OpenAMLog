// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface OpenAMLogTypes {

  IElementType PROPERTY = new OpenAMLogElementType("PROPERTY");

  IElementType COMMENT = new OpenAMLogTokenType("COMMENT");
  IElementType CRLF = new OpenAMLogTokenType("CRLF");
  IElementType KEY = new OpenAMLogTokenType("KEY");
  IElementType SEPARATOR = new OpenAMLogTokenType("SEPARATOR");
  IElementType VALUE = new OpenAMLogTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == PROPERTY) {
        return new OpenAMLogPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
