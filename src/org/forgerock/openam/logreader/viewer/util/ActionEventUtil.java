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
package org.forgerock.openam.logreader.viewer.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class ActionEventUtil {
    public static Project getProject(AnActionEvent event) {
        return PlatformDataKeys.PROJECT.getData(event.getDataContext());
    }

    public static PsiElement getPsiElement(AnActionEvent event) {
        return LangDataKeys.PSI_ELEMENT.getData(event.getDataContext());
    }

    public static Editor getEditor(AnActionEvent event) {
        return PlatformDataKeys.EDITOR.getData(event.getDataContext());
    }

    public static PsiFile getPsiFile(AnActionEvent event) {
        return LangDataKeys.PSI_FILE.getData(event.getDataContext());
    }

    public static VirtualFile getVirtualFile(AnActionEvent event) {
        return PlatformDataKeys.VIRTUAL_FILE.getData(event.getDataContext());
    }
}
