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

package org.forgerock.openam.logreader.highlight;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.forgerock.openam.logreader.icons.OpenAMLogIcons;
import org.forgerock.openam.logreader.util.OpenAMLogConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

/**
 * Color settings page for preferences
 *
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogColorSettingsPage implements ColorSettingsPage {

    private static final Logger LOG = Logger.getInstance(OpenAMLogColorSettingsPage.class.getName());

    private static final String DEMO_TEXT_AMLOG_PATH = "DemoText.amlog";

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Comment", OpenAMLogSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Debug name", OpenAMLogSyntaxHighlighter.DEBUG_NAME),
            new AttributesDescriptor("Date", OpenAMLogSyntaxHighlighter.DATE),
            new AttributesDescriptor("Thread name", OpenAMLogSyntaxHighlighter.THREAD_NAME),
            new AttributesDescriptor("log content", OpenAMLogSyntaxHighlighter.LOG_LINE),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return OpenAMLogIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new OpenAMLogSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        String demoText = null;
        URL demoTextResource = getClass().getClassLoader().getResource(DEMO_TEXT_AMLOG_PATH);
        if(demoTextResource != null) {
            try {
                demoText = new Scanner(new File(demoTextResource.getFile())).useDelimiter("\\Z").next();
            } catch (FileNotFoundException e) {
                LOG.warn("Can't read '" + DEMO_TEXT_AMLOG_PATH + "' resource.", e);
            }
        } else {
            LOG.warn("Can't find '" + DEMO_TEXT_AMLOG_PATH + "' resource.");
        }

        if(demoText == null) {
            demoText = "# You are reading the OpenAM Log file.\n" +
                    "amAuth:09 / 17 / 2014 07:15:06:802 AM CEST:Thread[ajp - bio - 8009 - exec - 95, 5, main]\n " +
                    "postProcessOnSuccess\n" +
                    "amAuth:09/17/2014 07:15:06:802 AM CEST: Thread[ajp-bio-8009-exec-95,5,main]\n" +
                    "postLoginClassSet = [org.forgerock.postauthentication.RegistrationPAP]\n";
        }
        return demoText;

    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return OpenAMLogConstant.PLUGIN_NAME;
    }
}