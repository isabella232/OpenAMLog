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

import com.intellij.ui.components.JBScrollPane;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.psi.OpenAMLogPsiImplUtil;
import org.forgerock.openam.logreader.util.OpenAMLogUtil;
import org.forgerock.openam.logreader.viewer.ui.model.DebugNameListModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

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


    private DebugNameJList debugNameJList;
    private DebugNameListModel debugNameListModel = new DebugNameListModel();
    private JTable table;

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
            String columnNames[] = {"Property", "Value"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 2);
            table = new JTable(model) {
                @Override
                public boolean isCellEditable(int arg0, int arg1) {
                    return false;
                }
            };
        }

        //link sub panel elements in a split panel
        JSplitPane splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JBScrollPane(debugNameJList), table);
        splitPanel.setDividerLocation(SPLIT_DIVIDER_POSITION);

        // Split panel to the main panel
        add(splitPanel);

    }


    @Override
    /**
     * Useful for activate the panel in the tool viewer
     */
    public void run() {
    }

    /**
     * Refresh the OpenAm Log viewer with the current file
     * @param logFile current log file
     */
    public void refreshOpenAMLogProperties(OpenAMLogFile logFile) {

        //Debug names
        debugNameListModel.setDebugNames(OpenAMLogUtil.findAllDebugNames(logFile));

        //Properties table
        initTableFromLog(logFile);

        //Refresh panel
        revalidate();
        repaint();
        debugNameJList.repaint();
        table.repaint();
    }

    /**
     * Initialize the properties table panel
     * @param logFile current log file
     */
    public void initTableFromLog(OpenAMLogFile logFile) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        //Remove current properties
        {
            int rowCount = model.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }

        //Columns
        String columnNames[] = {PROPERTY_COLUMN_TITLE, VALUE_COLUMN_TITLE};
        model.addRow(columnNames);


        //Set properties
        {
            model.addRow(new Object[]{LOG_NAME_TITLE, logFile.getName()});

            int nbDebugNames = OpenAMLogUtil.findAllDebugNames(logFile).size();
            model.addRow(new Object[]{NB_DEBUGGERS_TITLE, nbDebugNames});

            Date startDate = OpenAMLogUtil.getMinDate(logFile);
            if (startDate != null) {
                model.addRow(new Object[]{START_DATE_TITLE, OpenAMLogPsiImplUtil.dateFormat.format(startDate)});
            }
            Date endDate = OpenAMLogUtil.getMaxDate(logFile);
            if (startDate != null) {
                model.addRow(new Object[]{END_DATE_TITLE, OpenAMLogPsiImplUtil.dateFormat.format(endDate)});
            }
        }

    }
}
