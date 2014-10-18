// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.forgerock.openam.logreader.psi.OpenAMLogLogtitle;
import org.forgerock.openam.logreader.psi.OpenAMLogPsiImplUtil;
import org.forgerock.openam.logreader.psi.OpenAMLogVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class OpenAMLogLogtitleImpl extends ASTWrapperPsiElement implements OpenAMLogLogtitle {

    public OpenAMLogLogtitleImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof OpenAMLogVisitor) ((OpenAMLogVisitor) visitor).visitLogtitle(this);
        else super.accept(visitor);
    }

    public String getDebugName() {
        return OpenAMLogPsiImplUtil.getDebugName(this);
    }

    public Date getDate() {
        return OpenAMLogPsiImplUtil.getDate(this);
    }

    public String getThreadName() {
        return OpenAMLogPsiImplUtil.getThreadName(this);
    }

}
