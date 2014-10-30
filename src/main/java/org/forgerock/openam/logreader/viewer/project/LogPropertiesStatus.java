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

package org.forgerock.openam.logreader.viewer.project;

import com.intellij.openapi.editor.Editor;
import org.forgerock.openam.logreader.folding.OpenAMLogFolding;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.util.OpenAMLogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qcastel
 *         Date: 27/10/2014
 *         Project: OpenAMLogPlugin
 */
public class LogPropertiesStatus implements DebugNamesStatusListener{

    private OpenAMLogFile logFile;
    private Map<String, Boolean> debugNamesStatus = new HashMap<String, Boolean>();
    private OpenAMLogFolding folding;
    private Editor editor;

    public LogPropertiesStatus(OpenAMLogFile logFile,final Editor editor) {

        this.logFile = logFile;
        this.editor = editor;
        this.folding = new OpenAMLogFolding(logFile);

        //Set every debug names open at beginning
        for(String debugName : OpenAMLogUtil.findAllDebugNames(logFile)) {
            debugNamesStatus.put(debugName, true);
        }

        //Generate folding
        editor.getFoldingModel().runBatchFoldingOperation(new Runnable() {
            @Override
            public void run() {
            folding.generateFoldingRegions(editor);
            }
        });

    }

    public void setEditor(final Editor editor) {
        this.editor = editor;
        editor.getFoldingModel().runBatchFoldingOperation(new Runnable() {
            @Override
            public void run() {
                folding.generateFoldingRegions(editor);
            }
        });
    }

    public OpenAMLogFile getLogFile() {
        return logFile;
    }

    public boolean isDebugNameSelected(String debugName) {
        if(!debugNamesStatus.containsKey(debugName)) {
            throw new IllegalArgumentException("Debug name '" + debugName + "' isn't declared in this log file " +
                    getLogFile().getName() + ".");
        }
        return debugNamesStatus.get(debugName);
    }

    @Override
    public void selectedEvent(final String debugName) {
        debugNamesStatus.put(debugName, true);
        editor.getFoldingModel().runBatchFoldingOperation(new Runnable() {
            @Override
            public void run() {
                folding.unexpandFold(debugName);
            }
        });
    }

    @Override
    public void unselectedEvent(final String debugName) {
        debugNamesStatus.put(debugName, false);
        editor.getFoldingModel().runBatchFoldingOperation(new Runnable() {
            @Override
            public void run() {
                folding.expandFold(debugName);
            }
        });
    }

}
