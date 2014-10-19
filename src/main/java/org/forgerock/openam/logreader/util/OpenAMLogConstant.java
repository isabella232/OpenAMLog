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

import com.intellij.openapi.diagnostic.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Constants useful on this project
 *
 * @author qcastel
 * Date: 19/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogConstant {
    public static final String PLUGIN_NAME;
    public static final String PROJECT_COMPONENT_NAME;

    public static final String OPENAM_LOG_FILE_EXTENSION;

    public static final String LANGUAGE_NAME;

    public static final String ID_TOOL_WINDOW;

    private static final String AMLOG_PROPERTIES_PATH = "OpenAMLog.properties";

    private static final Logger LOG = Logger.getInstance(OpenAMLogConstant.class.getName());

    static {
        Properties prop = new Properties();
        InputStream input = null;

        String pluginName = "OpenAMLog";
        String projectComponentNanme = "ProjectComponent";
        String openAMLogFileExtension = "amlog";
        String languageName = "OpenAMLog";
        String idToolWindow = "OpenAMLogViewer";
        try {
            URL demoTextResource = OpenAMLogConstant.class.getClassLoader().getResource(AMLOG_PROPERTIES_PATH);
            if(demoTextResource == null) {
                LOG.error("OpenAM log properties '" + AMLOG_PROPERTIES_PATH + "'can't be find");
            } else {

                input = new FileInputStream(demoTextResource.getFile());

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                pluginName = prop.getProperty("pluginName");
                projectComponentNanme = prop.getProperty("projectComponentNanme");
                openAMLogFileExtension = prop.getProperty("openAMLogFileExtension");
                languageName = prop.getProperty("languageName");
                idToolWindow = prop.getProperty("idToolWindow");
            }

        } catch (IOException ex) {
            LOG.error("OpenAM log properties can't be read", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("OpenAM log properties stream can't be closed", e);
                }
            }
            PLUGIN_NAME = pluginName;
            PROJECT_COMPONENT_NAME = projectComponentNanme;
            OPENAM_LOG_FILE_EXTENSION = openAMLogFileExtension;
            LANGUAGE_NAME = languageName;
            ID_TOOL_WINDOW = idToolWindow;
        }
    }

}
