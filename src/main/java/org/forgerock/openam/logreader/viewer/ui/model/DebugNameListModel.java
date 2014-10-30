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

package org.forgerock.openam.logreader.viewer.ui.model;

import org.forgerock.openam.logreader.viewer.project.LogPropertiesStatus;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Debug names model for panel
 *
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class DebugNameListModel extends DefaultListModel {

    /**
     * Set debug names set
     * @param debugNames debug names list
     * @return all the checkbox for every debug names given
     */
    public List<DebugNameCheckListItem> setDebugNames(LogPropertiesStatus logPropertiesStatus, Set<String> debugNames) {
        this.removeAllElements();
        List<DebugNameCheckListItem> checkListItemList = new ArrayList<DebugNameCheckListItem>();
        for (String debugName : debugNames) {
            DebugNameCheckListItem checkListItem = new DebugNameCheckListItem(debugName);
            checkListItem.setSelected(logPropertiesStatus.isDebugNameSelected(debugName));
            this.addElement(checkListItem);
            checkListItemList.add(checkListItem);
        }
        return checkListItemList;
    }


}
