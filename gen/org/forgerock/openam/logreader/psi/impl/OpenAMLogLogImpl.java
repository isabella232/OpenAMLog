// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.forgerock.openam.logreader.psi.OpenAMLogLog;
import org.forgerock.openam.logreader.psi.OpenAMLogLogcontent;
import org.forgerock.openam.logreader.psi.OpenAMLogLogtitle;
import org.forgerock.openam.logreader.psi.OpenAMLogVisitor;
import org.jetbrains.annotations.NotNull;

public class OpenAMLogLogImpl extends ASTWrapperPsiElement implements OpenAMLogLog {

    public OpenAMLogLogImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof OpenAMLogVisitor) ((OpenAMLogVisitor) visitor).visitLog(this);
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
