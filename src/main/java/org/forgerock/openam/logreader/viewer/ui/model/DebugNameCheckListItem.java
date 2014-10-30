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

/**
 * CheckList item of a debug name
 *
 * @author qcastel
 * Date: 27/10/2014
 * Project: OpenAMLogPlugin
 */
public class DebugNameCheckListItem {

    private String debugName;
    private boolean isSelected = false;

    /**
     * Constructor
     * @param debugName debug name
     */
    public DebugNameCheckListItem(String debugName) {
        this.debugName = debugName;
    }

    /**
     * is the checkbox selected ?
     * @return true if check, false if uncheck
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Check/uncheck the checkbox
     * @param isSelected check the checkbox if true, uncheck if false
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * Get debug name
     * @return debug name
     */
    public String getDebugName() {
        return debugName;
    }

    @Override
    public String toString() {
        return getDebugName();
    }
}
