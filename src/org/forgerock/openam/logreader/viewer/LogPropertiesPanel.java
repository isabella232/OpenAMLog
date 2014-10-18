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

package org.forgerock.openam.logreader.viewer;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.psi.OpenAMLogPsiImplUtil;
import org.forgerock.openam.logreader.util.OpenAMLogUtil;
import org.forgerock.openam.logreader.viewer.model.DebugNameListModel;
import org.forgerock.openam.logreader.viewer.project.OpenAMLogProjectComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.Set;

/**
 * @author qcastel<br/>
 * Date: 18/10/2014<br/>
 * Project: OpenAMLogPlugin</br>
 */
public class LogPropertiesPanel extends JPanel implements Runnable {


    private DebugNameJList debugNameJList;
    private DebugNameListModel debugNameListModel = new DebugNameListModel();
    private JTable table;
    private JSplitPane splitPane;
    public int SPLIT_DIVIDER_POSITION = 300;

    private final OpenAMLogProjectComponent _projectComponent;
    private final Project _project;
    private ToolWindow toolWindow;

    public LogPropertiesPanel(OpenAMLogProjectComponent projectComponent) {
        _projectComponent = projectComponent;
        _project = projectComponent.getProject();

        buildGUI();
    }

    private void buildGUI() {
        setLayout(new BorderLayout());

        debugNameJList = new DebugNameJList(debugNameListModel);
        String columnNames[] = {"Property", "Value"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 2);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JBScrollPane(debugNameJList), table);
        splitPane.setDividerLocation(SPLIT_DIVIDER_POSITION);

        add(splitPane);

    }

    private ToolWindow getToolWindow() {
        return toolWindow;
    }

    public void setToolWindow(ToolWindow toolWindow) {
        toolWindow = toolWindow;
    }

    @Override
    public void run() {

    }

    public void setDebugNames(Set<String> allDebugNames) {
        debugNameListModel.setDebugNames(allDebugNames);
    }

    public void refreshOpenAMLogProperties(OpenAMLogFile logFile) {

        setDebugNames(OpenAMLogUtil.findAllDebugNames(logFile));
        initTableFromLog(logFile);
        revalidate();
        repaint();
        debugNameJList.repaint();
        table.repaint();
    }

    public void initTableFromLog(OpenAMLogFile logFile) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        String columnNames[] = {"Property", "Value"};
        model.addRow(columnNames);

        model.addRow(new Object[]{"Log Name", logFile.getName()});

        int nbDebugNames = OpenAMLogUtil.findAllDebugNames(logFile).size();
        model.addRow(new Object[]{"Number of debuggers", nbDebugNames});

        Date startDate = OpenAMLogUtil.getMinDate(logFile);
        if (startDate != null) {
            model.addRow(new Object[]{"Start Date", OpenAMLogPsiImplUtil.dateFormat.format(startDate)});
        }
        Date endDate = OpenAMLogUtil.getMaxDate(logFile);
        if (startDate != null) {
            model.addRow(new Object[]{"End Date", OpenAMLogPsiImplUtil.dateFormat.format(endDate)});
        }


    }
}
