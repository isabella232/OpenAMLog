// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.forgerock.openam.logreader.psi.OpenAMLogTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.forgerock.openam.logreader.psi.*;

public class OpenAMLogLogImpl extends ASTWrapperPsiElement implements OpenAMLogLog {

  public OpenAMLogLogImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OpenAMLogVisitor) ((OpenAMLogVisitor)visitor).visitLog(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public OpenAMLogLogcontent getLogcontent() {
    return findNotNullChildByClass(OpenAMLogLogcontent.class);
  }

  @Override
  @NotNull
  public OpenAMLogLogtitle getLogtitle() {
    return findNotNullChildByClass(OpenAMLogLogtitle.class);
  }


}
