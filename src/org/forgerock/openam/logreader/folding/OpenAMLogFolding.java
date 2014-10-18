package org.forgerock.openam.logreader.folding;

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
 * Created by qcastel on 13/10/2014.
 */
public class OpenAMLogFolding {

    private Map<OpenAMLogLog, FoldRegion> logFoldingRegion = new HashMap<OpenAMLogLog, FoldRegion>();
    private Map<String, List<FoldRegion>> logFoldingRegionByDebugNames = new HashMap<String, List<FoldRegion>>();
    private Map<String, Boolean> isDebugNameOpen = new HashMap<String, Boolean>();

    private OpenAMLogFile openAMLogFile;

    public OpenAMLogFolding(OpenAMLogFile openAMLogFile) {
        this.openAMLogFile = openAMLogFile;
    }

    public void generateFoldingRegions(Editor editor) {
        for (FoldRegion foldRegion : logFoldingRegion.values()) {
            editor.getFoldingModel().removeFoldRegion(foldRegion);
        }
        logFoldingRegionByDebugNames.clear();
        logFoldingRegion.clear();

        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {
                    TextRange textRange = log.getTextRange();
                    FoldRegion foldingRegion = editor.getFoldingModel().addFoldRegion(textRange.getStartOffset(), textRange.getEndOffset() - 1, log.getLogtitle().getDebugName() + ":"
                            + OpenAMLogPsiImplUtil.dateFormat.format(log.getLogtitle().getDate()) + "\n");
                    logFoldingRegion.put(log, foldingRegion);

                    String debugName = log.getLogtitle().getDebugName();
                    if (!logFoldingRegionByDebugNames.containsKey(debugName)) {
                        logFoldingRegionByDebugNames.put(debugName, new ArrayList<FoldRegion>());
                    }
                    logFoldingRegionByDebugNames.get(debugName).add(foldingRegion);

                    if (!isDebugNameOpen.containsKey(debugName)) {
                        isDebugNameOpen.put(debugName, Boolean.TRUE);
                    }
                    if (!isDebugNameOpen.get(debugName)) {
                        foldingRegion.setExpanded(false);
                    }
                }
            }
        }
    }

    public void close(String debugName) {
        isDebugNameOpen.put(debugName, Boolean.FALSE);
        if (logFoldingRegionByDebugNames.containsKey(debugName)) {
            for (FoldRegion foldRegion : logFoldingRegionByDebugNames.get(debugName)) {
                foldRegion.setExpanded(false);
            }
        }
    }

    public void open(String debugName) {
        isDebugNameOpen.put(debugName, Boolean.TRUE);
        if (logFoldingRegionByDebugNames.containsKey(debugName)) {
            for (FoldRegion foldRegion : logFoldingRegionByDebugNames.get(debugName)) {
                foldRegion.setExpanded(true);
            }
        }
    }

}
