// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class OpenAMLogVisitor extends PsiElementVisitor {

    public void visitLog(@NotNull OpenAMLogLog o) {
        visitPsiElement(o);
    }

    public void visitLogcontent(@NotNull OpenAMLogLogcontent o) {
        visitPsiElement(o);
    }

    public void visitLogtitle(@NotNull OpenAMLogLogtitle o) {
        visitPsiElement(o);
    }

    public void visitPsiElement(@NotNull PsiElement o) {
        visitElement(o);
    }

}
