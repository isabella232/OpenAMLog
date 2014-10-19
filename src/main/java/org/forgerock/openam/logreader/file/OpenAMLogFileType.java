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

package org.forgerock.openam.logreader.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.forgerock.openam.logreader.icons.OpenAMLogIcons;
import org.forgerock.openam.logreader.language.OpenAMLogLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Intellij file type definition.
 *
 * It's used by intellij to associate a language support to a file type.
 * File types are editable on the preference menu.
 *
 * @author qcastel
 * Date: 18/10/2014
 * Project: OpenAMLogPlugin
 */
public class OpenAMLogFileType extends LanguageFileType {

    public static final OpenAMLogFileType INSTANCE = new OpenAMLogFileType();

    public static final String OPENAM_LOG_FILE_EXTENSION = "amlog";

    private OpenAMLogFileType() {
        super(OpenAMLogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "OpenAM Log file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "OpenAM Log language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return OPENAM_LOG_FILE_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return OpenAMLogIcons.FILE;
    }
}