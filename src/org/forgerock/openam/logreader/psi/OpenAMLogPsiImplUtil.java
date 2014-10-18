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