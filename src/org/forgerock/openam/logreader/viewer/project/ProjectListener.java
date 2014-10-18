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

package org.forgerock.openam.logreader.viewer.project;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
import org.forgerock.openam.logreader.folding.OpenAMLogFolding;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.viewer.LogPropertiesPanel;
import org.forgerock.openam.logreader.viewer.OpenAMLogViewerConstants;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qcastel<br/>
 * Date: 18/10/2014<br/>
 * Project: OpenAMLogPlugin</br>
 */
public class ProjectListener implements FileEditorManagerListener {

    private LogPropertiesPanel viewer;
    private final Project project;
    private Map<OpenAMLogFile, OpenAMLogFolding> foldingsByLogFile = new HashMap<OpenAMLogFile, OpenAMLogFolding>();

    public ProjectListener(LogPropertiesPanel viewer, Project project) {
        this.viewer = viewer;
        this.project = project;
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {

    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {

    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        final Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (editor == null) {
            closeOpenAMLogView();
            return;
        }
        try {
            PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
            if (psiDocumentManager == null) {
                closeOpenAMLogView();
                return;
            }
            OpenAMLogFile logFile = (OpenAMLogFile) psiDocumentManager.getPsiFile(editor.getDocument());

            if (!foldingsByLogFile.containsKey(logFile)) {
                foldingsByLogFile.put(logFile, new OpenAMLogFolding(logFile));
            }
            final OpenAMLogFolding folding = foldingsByLogFile.get(logFile);
            editor.getFoldingModel().runBatchFoldingOperation(new Runnable() {
                @Override
                public void run() {
                    folding.generateFoldingRegions(editor);
                }
            });

            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
            if (toolWindow != null) {
                toolWindow.activate(viewer);
            }
            viewer.setVisible(true);
            viewer.refreshOpenAMLogProperties(logFile);
            editor.getFoldingModel().runBatchFoldingOperation(new Runnable() {
                @Override
                public void run() {
                    //folding.close("amAuth");
                }
            });
        } catch (ClassCastException e) {
            closeOpenAMLogView();
        }

    }

    private void closeOpenAMLogView() {
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
        if (toolWindow != null) {
            toolWindow.hide(viewer);
        }
        //not an OpenAM log file
        viewer.setVisible(false);
    }
}
