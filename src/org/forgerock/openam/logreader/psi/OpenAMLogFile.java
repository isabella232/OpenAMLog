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

package org.forgerock.openam.logreader.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.forgerock.openam.logreader.file.OpenAMLogFileType;
import org.forgerock.openam.logreader.language.OpenAMLogLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author qcastel<br/>
 * Date: 18/10/2014<br/>
 * Project: OpenAMLogPlugin</br>
 */
public class OpenAMLogFile extends PsiFileBase {
    public OpenAMLogFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, OpenAMLogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return OpenAMLogFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "OpenAM Log File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}