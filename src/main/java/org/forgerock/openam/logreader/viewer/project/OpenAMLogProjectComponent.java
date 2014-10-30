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

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.util.messages.MessageBusConnection;
import org.forgerock.openam.logreader.icons.OpenAMLogIcons;
import org.forgerock.openam.logreader.util.OpenAMLogConstant;
import org.forgerock.openam.logreader.viewer.ui.LogPropertiesPanel;
import org.jetbrains.annotations.NotNull;

/**
 * @author qcastel
 *         Date: 18/10/2014
 *         Project: OpenAMLogPlugin
 */
public class OpenAMLogProjectComponent implements ProjectComponent {

    private final Project project;

    private LogPropertiesPanel viewerPanel;

    /**
     * Constructor
     *
     * @param project current project opened in intellij
     */
    public OpenAMLogProjectComponent(Project project) {
        this.project = project;
    }

    /**
     * Called when a project is opened
     */
    public void projectOpened() {
        initToolWindow();
    }

    /**
     * Called when a project is closed
     */
    public void projectClosed() {
        unregisterToolWindow();
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    @NotNull
    /**
     * Component Name
     */
    public String getComponentName() {
        return OpenAMLogConstant.PLUGIN_NAME + '.' + OpenAMLogConstant.PROJECT_COMPONENT_NAME;
    }

    /**
     * Get project
     *
     * @return project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Initialize the tool window
     */
    public void initToolWindow() {
        viewerPanel = new LogPropertiesPanel();

        ToolWindow toolWindow = getToolWindow();
        toolWindow.setIcon(OpenAMLogIcons.FILE);
        OpenAMLogProjectListener projectListener = new OpenAMLogProjectListener(viewerPanel, project);
        MessageBusConnection messageBusConnection = project.getMessageBus().connect();
        messageBusConnection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, projectListener);
    }

    /**
     * unregister the tool window
     */
    public void unregisterToolWindow() {
        if (viewerPanel != null) {
            viewerPanel = null;
        }

        if (isToolWindowRegistered())
            ToolWindowManager.getInstance(project).unregisterToolWindow(OpenAMLogConstant.ID_TOOL_WINDOW);
    }

    /**
     * Get tool window
     * if it's not created yet, initialize it
     *
     * @return the tool window
     */
    private ToolWindow getToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        if (isToolWindowRegistered())
            return toolWindowManager.getToolWindow(OpenAMLogConstant.ID_TOOL_WINDOW);
        else {
            ToolWindow toolWindow = toolWindowManager.registerToolWindow(OpenAMLogConstant.ID_TOOL_WINDOW,
                    false,
                    ToolWindowAnchor.RIGHT);
            final Content toolContent = toolWindow.getContentManager().getFactory().createContent(viewerPanel,
                    OpenAMLogConstant.LOG_FILE_DESCRIPTION,
                    false);
            toolWindow.getContentManager().addContent(toolContent);
            toolWindow.setTitle(OpenAMLogConstant.LOG_FILE_DESCRIPTION);
            toolWindow.setIcon(OpenAMLogIcons.FILE);
            return toolWindow;
        }
    }

    /**
     * is the tool window registered ?
     *
     * @return true if it's register
     */
    private boolean isToolWindowRegistered() {
        return ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogConstant.ID_TOOL_WINDOW) != null;
    }

}