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
