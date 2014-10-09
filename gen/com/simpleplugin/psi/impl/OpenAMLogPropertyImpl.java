// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.simpleplugin.psi.*;

public class OpenAMLogPropertyImpl extends ASTWrapperPsiElement implements OpenAMLogProperty {

  public OpenAMLogPropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OpenAMLogVisitor) ((OpenAMLogVisitor)visitor).visitProperty(this);
    else super.accept(visitor);
  }

}
