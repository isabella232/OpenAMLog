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

package org.forgerock.openam.logreader.psi;

import com.intellij.lang.ASTNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenAMLogPsiImplUtil {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MM/dd/yyyy hh:mm:ss:SSS a zzz");

    public static String getDebugName(OpenAMLogLogtitle element) {
        ASTNode keyNode = element.getNode().findChildByType(OpenAMLogTypes.DEBUG_NAME);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static Date getDate(OpenAMLogLogtitle element) {
        ASTNode keyNode = element.getNode().findChildByType(OpenAMLogTypes.DATE);
        if (keyNode != null) {
            try {
                return dateFormat.parse(keyNode.getText());
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getThreadName(OpenAMLogLogtitle element) {
        ASTNode keyNode = element.getNode().findChildByType(OpenAMLogTypes.THREAD_NAME);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

}