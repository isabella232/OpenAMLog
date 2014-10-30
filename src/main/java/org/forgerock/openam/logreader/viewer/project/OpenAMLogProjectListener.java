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
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.util.OpenAMLogConstant;
import org.forgerock.openam.logreader.viewer.ui.LogPropertiesPanel;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Listener
 *
 * Will launch automatic action when an OpenAM log is involved.
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogProjectListener implements FileEditorManagerListener {

    private LogPropertiesPanel viewer;
    private final Project project;
    private Map<OpenAMLogFile, LogPropertiesStatus> logPropertiesStatusByLogFile = new HashMap<OpenAMLogFile, LogPropertiesStatus>();
    private ToolWindow toolWindow;
    /**
     * Constructor
     * @param viewer OpenAM viewer
     * @param project Current project
     */
    public OpenAMLogProjectListener(LogPropertiesPanel viewer, Project project) {
        this.viewer = viewer;
        this.project = project;
        this.toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogConstant.ID_TOOL_WINDOW);

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
        if (editor == null) { // When an error occurred in intellij, editor could be null
            closeOpenAMLogView();
            return;
        }
        try {
            PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
            if (psiDocumentManager == null) { //Could happen if intellij failed
                closeOpenAMLogView();
                return;
            }
            OpenAMLogFile logFile = (OpenAMLogFile) psiDocumentManager.getPsiFile(editor.getDocument());

            // Create OpenAM folding controller
            if (!logPropertiesStatusByLogFile.containsKey(logFile)) {
                logPropertiesStatusByLogFile.put(logFile, new LogPropertiesStatus(logFile, editor));
            }
            LogPropertiesStatus logPropertiesStatus = logPropertiesStatusByLogFile.get(logFile);
            logPropertiesStatus.setEditor(editor);


            // Activate viewer
            if (toolWindow != null) {
                toolWindow.activate(viewer);
            }

            // initialize viewer
            viewer.setVisible(true);
            viewer.refreshOpenAMLogProperties(logPropertiesStatus);

        } catch (ClassCastException e) {
            closeOpenAMLogView();
        }

    }

    /**
     * Close the OpenAM log viewer
     */
    private void closeOpenAMLogView() {
        if (toolWindow != null && ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogConstant
                .ID_TOOL_WINDOW) != null) {
            toolWindow.hide(viewer);
        }
        //not an OpenAM log file
        viewer.setVisible(false);
    }
}
