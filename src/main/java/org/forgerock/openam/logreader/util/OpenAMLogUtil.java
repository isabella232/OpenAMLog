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

package org.forgerock.openam.logreader.util;

import com.intellij.psi.util.PsiTreeUtil;
import org.forgerock.openam.logreader.psi.OpenAMLogFile;
import org.forgerock.openam.logreader.psi.OpenAMLogLog;

import java.util.*;

/**
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogUtil {


    /**
     * Filter logs by a list of debug names
     * @param openAMLogFile log file
     * @param debugNames list of debug names that you want to keep
     * @return every logs that have their debug names in debugNames
     */
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

    /**
     * Get the set of debug names in this file
     * @param openAMLogFile log file
     * @return every debug Names in log file
     */
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

    /**
     * Get the start Date
     * @param openAMLogFile
     * @return
     */
    public static Date getMinDate(OpenAMLogFile openAMLogFile) {
        Date minDate = null;
        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {
                    Date currentDate = log.getLogtitle().getDate();
                    if (currentDate != null) {
                        if (minDate == null || !currentDate.after(minDate)) {
                            minDate = currentDate;
                        }
                    }
                }
            }
        }
        return minDate;
    }

    /**
     * Get the end date
     * @param openAMLogFile
     * @return
     */
    public static Date getMaxDate(OpenAMLogFile openAMLogFile) {
        Date maxDate = null;
        if (openAMLogFile != null) {
            OpenAMLogLog[] logs = PsiTreeUtil.getChildrenOfType(openAMLogFile, OpenAMLogLog.class);
            if (logs != null) {
                for (OpenAMLogLog log : logs) {
                    Date currentDate = log.getLogtitle().getDate();
                    if (currentDate != null) {
                        if (maxDate == null || currentDate.after(maxDate)) {
                            maxDate = currentDate;
                        }
                    }
                }
            }
        }
        return maxDate;
    }
}