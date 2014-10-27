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

package org.forgerock.openam.logreader.viewer.ui;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ui.components.JBScrollPane;
import org.forgerock.openam.logreader.psi.OpenAMLogPsiImplUtil;
import org.forgerock.openam.logreader.util.OpenAMLogConstant;
import org.forgerock.openam.logreader.util.OpenAMLogUtil;
import org.forgerock.openam.logreader.viewer.project.LogPropertiesStatus;
import org.forgerock.openam.logreader.viewer.ui.icons.ToolBarIcons;
import org.forgerock.openam.logreader.viewer.ui.model.DebugNameCheckListItem;
import org.forgerock.openam.logreader.viewer.ui.model.DebugNameListModel;
import org.forgerock.openam.logreader.viewer.ui.toolbar.ButtonActionListener;
import org.forgerock.openam.logreader.viewer.ui.toolbar.OpenAMLogToggleAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class LogPropertiesPanel extends JPanel implements Runnable {

    public static final int SPLIT_DIVIDER_POSITION = 300;

    private static final String PROPERTY_COLUMN_TITLE = "Property" ;
    private static final String VALUE_COLUMN_TITLE = "Value";
    private static final String LOG_NAME_TITLE = "Log Name";
    private static final String NB_DEBUGGERS_TITLE = "Number of debuggers";
    private static final String START_DATE_TITLE = "Start Date";
    private static final String END_DATE_TITLE = "End Date";
    private static final int MARGIN = 5;

    private DebugNameJList debugNameJList;
    private DebugNameListModel debugNameListModel = new DebugNameListModel();
    private JTable table;
    private List<DebugNameCheckListItem> checkListItems;
    private LogPropertiesStatus currentLogPropertiesStatus;

    /**
     * Constructor
     */
    public LogPropertiesPanel() {
        buildGUI();
    }

    /**
     * Build GUI : build and connect every UI elements with the main layout
     */
    private void buildGUI() {
        setLayout(new BorderLayout());

        //Construct sub panel elements
        {
            // Debug names panel
            debugNameJList = new DebugNameJList(debugNameListModel);

            // Properties panel
            String columnNames[] = {PROPERTY_COLUMN_TITLE, VALUE_COLUMN_TITLE};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);

            table = new JTable(model) {
                @Override
                public boolean isCellEditable(int arg0, int arg1) {
                    return false;
                }
            };
        }

        //link sub panel elements in a split panel
        JSplitPane splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JBScrollPane(debugNameJList), new JBScrollPane(table));
        splitPanel.setDividerLocation(SPLIT_DIVIDER_POSITION);

        // Split panel to the main panel
        add(splitPanel);

        //Set toolBar
        DefaultActionGroup actionGroup = new DefaultActionGroup(OpenAMLogConstant.ID_TOOL_BAR_WINDOW, false);

        ButtonActionListener checkAction = new ButtonActionListener() {
            @Override
            public void onClickAction(AnActionEvent anActionEvent, boolean b) {
                for(DebugNameCheckListItem checkItem : checkListItems) {
                    checkItem.setSelected(true);
                    currentLogPropertiesStatus.selectedEvent(checkItem.getDebugName());
                }
                refreshPanel();
            }
        };
        actionGroup.add(new OpenAMLogToggleAction(OpenAMLogConstant.CHECKBOX_TEXT,
                OpenAMLogConstant.CHECKBOX_TEXT,
                ToolBarIcons.CHECKED_BOX, checkAction));

        ButtonActionListener uncheckAction = new ButtonActionListener() {
            @Override
            public void onClickAction(AnActionEvent anActionEvent, boolean b) {
                for(DebugNameCheckListItem checkItem : checkListItems) {
                    checkItem.setSelected(false);
                    currentLogPropertiesStatus.unselectedEvent(checkItem.getDebugName());
                }
                refreshPanel();
            }
        };
        actionGroup.add(new OpenAMLogToggleAction(OpenAMLogConstant.UNCHECKBOX_TEXT,
                OpenAMLogConstant.UNCHECKBOX_TEXT,
                ToolBarIcons.UNCHECKED_BOX, uncheckAction));



        ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar toolBar = actionManager.createActionToolbar(OpenAMLogConstant.ID_TOOL_BAR_WINDOW, actionGroup, true);

        add(toolBar.getComponent(), BorderLayout.NORTH);
    }


    @Override
    /**
     * Useful for activate the panel in the tool viewer
     */
    public void run() {
    }

    /**
     * Refresh the OpenAm Log viewer with the current file
     * @param logPropertiesStatus current log properties status
     */
    public void refreshOpenAMLogProperties(LogPropertiesStatus logPropertiesStatus) {

        this.currentLogPropertiesStatus = logPropertiesStatus;

        // Set Debug names listener
        debugNameJList.setDebugNamesStatusListener(currentLogPropertiesStatus);

        //Debug names
        initCheckboxListFromLog();

        //Properties table
        initTableFromLog();

        //Refresh panel
        refreshPanel();
    }

    /**
     * Initialize the checkbox list
     */
    private void initCheckboxListFromLog() {
        checkListItems = debugNameListModel.setDebugNames(currentLogPropertiesStatus,
                OpenAMLogUtil.findAllDebugNames(currentLogPropertiesStatus
                .getLogFile()));

    }

    /**
     * Initialize the properties table panel
     */
    private void initTableFromLog() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        //Remove current properties
        {
            int rowCount = model.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }

        //Set properties
        {
            model.addRow(new Object[]{LOG_NAME_TITLE, currentLogPropertiesStatus.getLogFile().getName()});

            int nbDebugNames = OpenAMLogUtil.findAllDebugNames(currentLogPropertiesStatus.getLogFile()).size();
            model.addRow(new Object[]{NB_DEBUGGERS_TITLE, nbDebugNames});

            Date startDate = OpenAMLogUtil.getMinDate(currentLogPropertiesStatus.getLogFile());
            if (startDate != null) {
                model.addRow(new Object[]{START_DATE_TITLE, OpenAMLogPsiImplUtil.dateFormat.format(startDate)});
            }
            Date endDate = OpenAMLogUtil.getMaxDate(currentLogPropertiesStatus.getLogFile());
            if (startDate != null) {
                model.addRow(new Object[]{END_DATE_TITLE, OpenAMLogPsiImplUtil.dateFormat.format(endDate)});
            }
        }

        //Set properties column size
        {
            TableColumn propertyColumn = table.getColumn(PROPERTY_COLUMN_TITLE);
            int width = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, propertyColumn.getModelIndex());
                Component comp = table.prepareRenderer(renderer, row, propertyColumn.getModelIndex());
                width = Math.max (comp.getPreferredSize().width, width);
            }
            propertyColumn.setMaxWidth(width + MARGIN);
            propertyColumn.setPreferredWidth(width + MARGIN);
        }

    }

    private void refreshPanel() {
        revalidate();
        repaint();
        debugNameJList.repaint();
        table.repaint();
    }

}
