package org.forgerock.openam.logreader.viewer.project;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.forgerock.openam.logreader.folding.OpenAMLogFolding;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.viewer.LogPropertiesPanel;
import org.forgerock.openam.logreader.viewer.OpenAMLogViewerConstants;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qcastel on 12/10/2014.
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
        if(editor == null) {
            closeOpenAMLogView();
            return;
        }
        try {
            PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
            if(psiDocumentManager == null) {
                closeOpenAMLogView();
                return;
            }
            OpenAMLogFile logFile = (OpenAMLogFile) psiDocumentManager.getPsiFile(editor.getDocument());

            if(!foldingsByLogFile.containsKey(logFile)) {
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
            if (toolWindow != null)
            {
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
        } catch(ClassCastException e) {
            closeOpenAMLogView();
        }

    }

    private void closeOpenAMLogView() {
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
        if (toolWindow != null)
        {
            toolWindow.hide(viewer);
        }
        //not an OpenAM log file
        viewer.setVisible(false);
    }
}
