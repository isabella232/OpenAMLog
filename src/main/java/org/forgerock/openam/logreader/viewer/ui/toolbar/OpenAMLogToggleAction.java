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

package org.forgerock.openam.logreader.viewer.ui.toolbar;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * OpenAM log viewer Toggle Action for Tool bar
 *
 * @author qcastel
 * Date: 27/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogToggleAction extends ToggleAction {

    private ButtonActionListener buttonActionListener;


    /**
     * Constructor
     * @param text text display when the mousse is over the icon
     * @param description description of the action
     * @param icon icon display : recommend 18x18
     * @param buttonActionListener button action listener
     */
    public OpenAMLogToggleAction(@Nullable String text, @Nullable String description, @Nullable Icon icon,
                                 ButtonActionListener buttonActionListener) {
        super(text, description, icon);
        this.buttonActionListener = buttonActionListener;
    }

    @Override
    public boolean isSelected(AnActionEvent anActionEvent) {
        return false;
    }

    @Override
    public void setSelected(AnActionEvent anActionEvent, boolean b) {
        buttonActionListener.onClickAction(anActionEvent, b);
    }
}
