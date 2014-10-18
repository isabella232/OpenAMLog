// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.forgerock.openam.logreader.psi.OpenAMLogLogcontent;
import org.forgerock.openam.logreader.psi.OpenAMLogVisitor;
import org.jetbrains.annotations.NotNull;

public class OpenAMLogLogcontentImpl extends ASTWrapperPsiElement implements OpenAMLogLogcontent {

    public OpenAMLogLogcontentImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof OpenAMLogVisitor) ((OpenAMLogVisitor) visitor).visitLogcontent(this);
        else super.accept(visitor);
    }

}
