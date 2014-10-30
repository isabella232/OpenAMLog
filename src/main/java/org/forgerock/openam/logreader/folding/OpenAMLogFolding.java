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

package org.forgerock.openam.logreader.folding;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.util.PsiTreeUtil;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.psi.OpenAMLogLog;
import org.forgerock.openam.logreader.psi.OpenAMLogPsiImplUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manage the folding of an OpenAM log file
 *
 * Folding is the action on a IDE to minimize a block
 *
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogFolding {

    private static final Logger LOG = Logger.getInstance(OpenAMLogFolding.class.getName());

    //Map for sorting our elements
    private Map<OpenAMLogLog, FoldRegion> logFoldingRegion = new HashMap<OpenAMLogLog, FoldRegion>();
    private Map<String, List<FoldRegion>> logFoldingRegionByDebugNames = new HashMap<String, List<FoldRegion>>();
    private Map<String, Boolean> isDebugNameUnExpanded = new HashMap<String, Boolean>();

    private OpenAMLogFile openAMLogFile;

    /**
     * Constructor for managing a OpenAM log file
     * @param openAMLogFile OpenAM log file
     */
    public OpenAMLogFolding(OpenAMLogFile openAMLogFile) {
        this.openAMLogFile = openAMLogFile;
    }

    public void generateFoldingRegions(Editor editor) {

        //Reset previous map
        for (FoldRegion foldRegion : logFoldingRegion.values()) {
            editor.getFoldingModel().removeFoldRegion(foldRegion);
        }
        logFoldingRegionByDebugNames.clear();
        logFoldingRegion.clear();

        //Generate FoldRegion sorted by debug names
        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {

                    //Create folding region
                    TextRange textRange = log.getTextRange();
                    FoldRegion foldingRegion = editor.getFoldingModel().addFoldRegion(textRange.getStartOffset(), textRange.getEndOffset() - 1, log.getLogtitle().getDebugName() + ":"
                            + OpenAMLogPsiImplUtil.dateFormat.format(log.getLogtitle().getDate()) + "\n");

                    if(foldingRegion == null) {
                        LOG.warn("A Fold region creation failed. textRange : '" + textRange + "'");
                        break;
                    }

                    logFoldingRegion.put(log, foldingRegion);

                    //Sort folding by its debug name
                    String debugName = log.getLogtitle().getDebugName();
                    if (!logFoldingRegionByDebugNames.containsKey(debugName)) {
                        logFoldingRegionByDebugNames.put(debugName, new ArrayList<FoldRegion>());
                    }
                    logFoldingRegionByDebugNames.get(debugName).add(foldingRegion);

                    //Check if this fold should be expand
                    if (!isDebugNameUnExpanded.containsKey(debugName)) {
                        isDebugNameUnExpanded.put(debugName, Boolean.TRUE);
                    }
                    if (!isDebugNameUnExpanded(debugName)) {
                        foldingRegion.setExpanded(false);
                    }
                }
            }
        }
    }

    /**
     * Expand every folds associated with a debug name
     * @param debugName debug name
     */
    public void expandFold(String debugName) {
        expandFold(debugName, false);
    }

    /**
     * Un-expand every folds associated with a debug name
     * @param debugName debug name
     */
    public void unexpandFold(String debugName) {
        expandFold(debugName, true);
    }

    /**
     * Expand or un-expand every folds associated with a debug name
     * @param debugName debug name
     * @param action true if you want to expand all of them, false in the contrary
     */
    private void expandFold(String debugName, boolean action) {
        isDebugNameUnExpanded.put(debugName, action);
        if (logFoldingRegionByDebugNames.containsKey(debugName)) {
            for (FoldRegion foldRegion : logFoldingRegionByDebugNames.get(debugName)) {
                foldRegion.setExpanded(action);
            }
        }
    }

    /**
     * Check if every folds associated with this debug name are expanded
     * @param debugName debug name
     * @return true if they are expanded
     */
    public boolean isDebugNameUnExpanded(String debugName) {
        return isDebugNameUnExpanded.get(debugName);
    }

}
