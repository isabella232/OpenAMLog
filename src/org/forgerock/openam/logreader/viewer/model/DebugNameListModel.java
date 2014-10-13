package org.forgerock.openam.logreader.viewer.model;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by qcastel on 12/10/2014.
 */
public class DebugNameListModel extends DefaultListModel {

    private Set<String> debugNames = new TreeSet<String>();


    public void setDebugNames( Set<String> debugNames) {
        this.debugNames = debugNames;
        this.removeAllElements();
        for(String debugName : debugNames) {
            this.addElement(debugName);
        }
    }


}
