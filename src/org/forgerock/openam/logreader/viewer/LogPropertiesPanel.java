package org.forgerock.openam.logreader.viewer;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import org.forgerock.openam.logreader.util.OpenAMLogUtil;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.psi.OpenAMLogPsiImplUtil;
import org.forgerock.openam.logreader.viewer.model.DebugNameListModel;
import org.forgerock.openam.logreader.viewer.project.OpenAMLogProjectComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by qcastel on 12/10/2014.
 */
public class LogPropertiesPanel extends JPanel implements Runnable{


    private DebugNameJList debugNameJList;
    private DebugNameListModel debugNameListModel = new DebugNameListModel();
    private JTable table;
    private JSplitPane splitPane;
    public int SPLIT_DIVIDER_POSITION = 300;

    private final OpenAMLogProjectComponent _projectComponent;
    private final Project _project;
    private ToolWindow toolWindow;

    public LogPropertiesPanel(OpenAMLogProjectComponent projectComponent)
    {
        _projectComponent = projectComponent;
        _project = projectComponent.getProject();

        buildGUI();
    }

    private void buildGUI()
    {
        setLayout(new BorderLayout());

        debugNameJList = new DebugNameJList(debugNameListModel);
        String columnNames[] = { "Property", "Value"};

        DefaultTableModel model = new DefaultTableModel(columnNames,2);
        table=new JTable(model){@Override public boolean isCellEditable(int arg0, int arg1) { return false; }};

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JBScrollPane(debugNameJList), table);
        splitPane.setDividerLocation(SPLIT_DIVIDER_POSITION);

        add(splitPane);

    }

    private ToolWindow getToolWindow()
    {
        return toolWindow;
    }

    public void setToolWindow(ToolWindow toolWindow)
    {
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
        DefaultTableModel model = (DefaultTableModel)table.getModel();

        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        String columnNames[] = { "Property", "Value"};
        model.addRow(columnNames);

        model.addRow(new Object[]{"Log Name", logFile.getName()});

        int nbDebugNames = OpenAMLogUtil.findAllDebugNames(logFile).size();
        model.addRow(new Object[]{"Number of debuggers", nbDebugNames});

        Date startDate = OpenAMLogUtil.getMinDate(logFile);
        if(startDate != null) {
            model.addRow(new Object[]{"Start Date", OpenAMLogPsiImplUtil.dateFormat.format(startDate)});
        }
        Date endDate = OpenAMLogUtil.getMaxDate(logFile);
        if(startDate != null) {
            model.addRow(new Object[]{"End Date", OpenAMLogPsiImplUtil.dateFormat.format(endDate)});
        }


    }
}
