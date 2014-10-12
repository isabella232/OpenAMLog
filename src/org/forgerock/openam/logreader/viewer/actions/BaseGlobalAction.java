/*
 *  Copyright (c) 2002 Sabre, Inc. All rights reserved.
 */
package org.forgerock.openam.logreader.viewer.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.forgerock.openam.logreader.OpenAMLogUtil;
import org.forgerock.openam.logreader.viewer.OpenAMLogViewerConstants;
import org.forgerock.openam.logreader.viewer.util.ActionEventUtil;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.viewer.LogPropertiesPanel;
import org.forgerock.openam.logreader.viewer.project.OpenAMLogProjectComponent;

abstract class BaseGlobalAction extends AnAction
{

    public void update(AnActionEvent event)
    {
        Presentation presentation = event.getPresentation();
        Project project = ActionEventUtil.getProject(event);
        if (project == null)
        { // project isn't accessible from the context
            presentation.setEnabled(false);
            presentation.setVisible(false);
            return;
        }
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
        if (toolWindow == null)
        { // tool window isn't registered
            presentation.setEnabled(false);
            presentation.setVisible(false);
            return;
        }
        VirtualFile file = ActionEventUtil.getVirtualFile(event);

        if (file == null)
        {
            presentation.setEnabled(false);
            presentation.setVisible(false);
            return;
        }
        presentation.setEnabled(toolWindow.isAvailable());
        presentation.setVisible(true);

        OpenAMLogFile logFile = (OpenAMLogFile) PsiManager.getInstance(project).findFile(file);
        LogPropertiesPanel viewer = OpenAMLogProjectComponent.getInstance(project).getViewerPanel();

        viewer.refreshOpenAMLogProperties(logFile);
    }

    public void actionPerformed(AnActionEvent event)
    {
        Project project = ActionEventUtil.getProject(event);
        PsiDocumentManager.getInstance(project).commitAllDocuments();

        LogPropertiesPanel viewer = OpenAMLogProjectComponent.getInstance(project).getViewerPanel();

        if (getTargetElement(event) == null)
            return;


        VirtualFile file = ActionEventUtil.getVirtualFile(event);
        OpenAMLogFile logFile = (OpenAMLogFile) PsiManager.getInstance(project).findFile(file);

        viewer.refreshOpenAMLogProperties(logFile);

        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
        toolWindow.activate(viewer);
    }

    protected abstract String getToolWindowTitle();

    protected abstract PsiElement getTargetElement(AnActionEvent event);
}
