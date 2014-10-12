/*
    IDEA PsiViewer Plugin
    Copyright (C) 2002 Andrew J. Armstrong

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	Author:
	Andrew J. Armstrong <andrew_armstrong@bigpond.com>
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
import org.forgerock.openam.logreader.OpenAMLogIcons;
import org.forgerock.openam.logreader.viewer.LogPropertiesPanel;
import org.forgerock.openam.logreader.viewer.OpenAMLogViewerConstants;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class OpenAMLogProjectComponent implements ProjectComponent, JDOMExternalizable
{

    private static final Logger LOG = Logger.getInstance("idea.plugin.psiviewer.controller.project.PsiViewerProjectComponent");

    private final Project project;
    //private EditorListener _editorListener;
    private LogPropertiesPanel viewerPanel;
    private ProjectListener projectListener;

    public OpenAMLogProjectComponent(Project project)
    {
        this.project = project;
    }

    public void projectOpened()
    {
        initToolWindow();
    }

    public void projectClosed()
    {
        unregisterToolWindow();
    }

    public void initComponent()
    {
    }

    public void disposeComponent()
    {
    }

    @NotNull
    public String getComponentName()
    {
        return OpenAMLogViewerConstants.PLUGIN_NAME + '.' + OpenAMLogViewerConstants.PROJECT_COMPONENT_NAME;
    }

    public void initToolWindow()
    {
        viewerPanel = new LogPropertiesPanel(this);

        ActionManager actionManager = ActionManager.getInstance();

        ToolWindow toolWindow = getToolWindow();
        toolWindow.setIcon(OpenAMLogIcons.FILE);
        viewerPanel.setToolWindow(toolWindow);
        projectListener = new ProjectListener(viewerPanel, project);
        MessageBusConnection msgbus = project.getMessageBus().connect();
        msgbus.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, projectListener);
        //_editorListener = new EditorListener(viewerPanel, project);
    }


    public void unregisterToolWindow()
    {
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

    private ToolWindow getToolWindow()
    {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        if (isToolWindowRegistered())
            return toolWindowManager.getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW);
        else
            return toolWindowManager.registerToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW,
                                                        viewerPanel,
                                                        ToolWindowAnchor.RIGHT);
    }

    private boolean isToolWindowRegistered()
    {
        return ToolWindowManager.getInstance(project).getToolWindow(OpenAMLogViewerConstants.ID_TOOL_WINDOW) != null;
    }

    public void readExternal(Element element) throws InvalidDataException
    {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public void writeExternal(Element element) throws WriteExternalException
    {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }

    public LogPropertiesPanel getViewerPanel()
    {
        return viewerPanel;
    }


    public Project getProject()
    {
        return project;
    }

    public static OpenAMLogProjectComponent getInstance(Project project)
    {
        return project.getComponent(OpenAMLogProjectComponent.class);
    }

    private static void debug(String message)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(message);
        }
    }

}
