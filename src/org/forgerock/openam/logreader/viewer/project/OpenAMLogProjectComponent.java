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

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.messages.MessageBusConnection;
import org.forgerock.openam.logreader.icons.OpenAMLogIcons;
import org.forgerock.openam.logreader.viewer.ui.LogPropertiesPanel;
import org.forgerock.openam.logreader.viewer.util.OpenAMLogViewerConstants;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

/**
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogProjectComponent implements ProjectComponent, JDOMExternalizable {

    private static final Logger LOG = Logger.getInstance("idea.plugin.psiviewer.controller.project.PsiViewerProjectComponent");

    private final Project project;
    //private EditorListener _editorListener;
    private LogPropertiesPanel viewerPanel;
    private OpenAMLogProjectListener projectListener;

    public OpenAMLogProjectComponent(Project project) {
        this.project = project;
    }

    public void projectOpened() {
        initToolWindow();
    }

    public void projectClosed() {
        unregisterToolWindow();
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    @NotNull
    public String getComponentName() {
        return OpenAMLogViewerConstants.PLUGIN_NAME + '.' + OpenAMLogViewerConstants.PROJECT_COMPONENT_NAME;
    }

    public void initToolWindow() {
        viewerPanel = new LogPropertiesPanel(this);

        ActionManager actionManager = ActionManager.getInstance();

        ToolWindow toolWindow = getToolWindow();
        toolWindow.setIcon(OpenAMLogIcons.FILE);
        viewerPanel.setToolWindow(toolWindow);
        projectListener = new OpenAMLogProjectListener(viewerPanel, project);
        MessageBusConnection msgbus = project.getMessageBus().connect();
        msgbus.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, projectListener);
        //_editorListener = new EditorListener(viewerPanel, project);
    }


    public void unregisterToolWindow() {
        if (viewerPanel != null) {
            viewerPanel = null;
        }

        /*
        if (_editorListener != null) {
            _editorListener.stop();
            _editorListener = null;
        }*/
        if (isToolWindowRegistered())
            ToolWindowManager.getInstance(project).unregisterToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
    }

    private ToolWindow getToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        if (isToolWindowRegistered())
            return toolWindowManager.getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
        else
            return toolWindowManager.registerToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW,
                    viewerPanel,
                    ToolWindowAnchor.RIGHT);
    }

    private boolean isToolWindowRegistered() {
        return ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW) != null;
    }

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }

    public LogPropertiesPanel getViewerPanel() {
        return viewerPanel;
    }


    public Project getProject() {
        return project;
    }

    public static OpenAMLogProjectComponent getInstance(Project project) {
        return project.getComponent(OpenAMLogProjectComponent.class);
    }

    private static void debug(String message) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(message);
        }
    }

}