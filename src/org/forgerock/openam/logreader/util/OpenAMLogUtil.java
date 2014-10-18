package org.forgerock.openam.logreader.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.psi.OpenAMLogLog;

import java.util.*;

public class OpenAMLogUtil {


    public static List<OpenAMLogLog> filterByDebugNames(OpenAMLogFile openAMLogFile, List<String> debugNames) {
        List<OpenAMLogLog> result = null;

        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {

                    if (debugNames.contains(log.getLogtitle().getDebugName())) {
                        if (result == null) {
                            result = new ArrayList<OpenAMLogLog>();
                        }
                        result.add(log);
                    }
                }
            }
        }
        return result != null ? result : Collections.<OpenAMLogLog>emptyList();
    }

    public static Set<String> findAllDebugNames(OpenAMLogFile openAMLogFile) {
        Set<String> result = new TreeSet<String>();

        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {
                    result.add(log.getLogtitle().getDebugName());
                }
            }
        }
        return result;
    }

    public static Date getMinDate(OpenAMLogFile openAMLogFile) {
        Date minDate = null;
        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {
                    Date currentDate = log.getLogtitle().getDate();
                    if(currentDate != null) {
                        if(minDate == null || !currentDate.after(minDate)) {
                            minDate = currentDate;
                        }
                    }
                }
            }
        }
        return minDate;
    }

    public static Date getMaxDate(OpenAMLogFile openAMLogFile) {
        Date maxDate = null;
        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {
                    Date currentDate = log.getLogtitle().getDate();
                    if(currentDate != null) {
                        if(maxDate == null || currentDate.after(maxDate)) {
                            maxDate = currentDate;
                        }
                    }
                }
            }
        }
        return maxDate;
    }
}