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
import com.intellij.psi.PsiFile;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.viewer.LogPropertiesPanel;
import org.forgerock.openam.logreader.viewer.OpenAMLogViewerConstants;
import org.jetbrains.annotations.NotNull;

/**
 * Created by qcastel on 12/10/2014.
 */
public class ProjectListener implements FileEditorManagerListener {

    private LogPropertiesPanel viewer;
    private final Project project;

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
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        try {
            OpenAMLogFile logFile = (OpenAMLogFile) PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
            if (toolWindow != null)
            {
                toolWindow.activate(viewer);
            }
            viewer.setVisible(true);
            viewer.refreshOpenAMLogProperties(logFile);
        } catch(ClassCastException e) {
            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
            if (toolWindow != null)
            {
                toolWindow.hide(viewer);
            }
            //not an OpenAM log file
            viewer.setVisible(false);
        }
    }
}
