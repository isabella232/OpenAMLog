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

import org.forgerock.openam.logreader.viewer.project.DebugNamesStatusListener;
import org.forgerock.openam.logreader.viewer.ui.model.DebugNameCheckListItem;
import org.forgerock.openam.logreader.viewer.ui.model.CheckListRenderer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Debug names panel
 *
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class DebugNameJList extends JList {

    private DebugNamesStatusListener debugNamesStatusListener;

    /**
     * Constructor
     * @param dataModel list model
     */
    public DebugNameJList(ListModel dataModel) {
        super(dataModel);

        this.setCellRenderer(new CheckListRenderer());
        this.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        // Add a mouse listener to handle changing selection
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();

                // Get index of item clicked

                int index = list.locationToIndex(event.getPoint());
                DebugNameCheckListItem item = (DebugNameCheckListItem)
                        list.getModel().getElementAt(index);

                // Toggle selected state

                if(item.isSelected()) {
                    debugNamesStatusListener.unselectedEvent(item.getDebugName());
                } else {
                    debugNamesStatusListener.selectedEvent(item.getDebugName());

                }
                item.setSelected(!item.isSelected());

                // Repaint cell

                list.repaint(list.getCellBounds(index, index));
            }
        });

    }

    /**
     * Set debug names status listener
     * @param debugNamesStatusListener debug names status listener
     */
    public void setDebugNamesStatusListener(DebugNamesStatusListener debugNamesStatusListener) {
        this.debugNamesStatusListener = debugNamesStatusListener;
    }

}
