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
 * @author qcastel<br/>
 * Date: 18/10/2014<br/>
 * Project: OpenAMLogPlugin</br>
 */
public class OpenAMLogFileType extends LanguageFileType {
    public static final OpenAMLogFileType INSTANCE = new OpenAMLogFileType();

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
        return "amlog";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return OpenAMLogIcons.FILE;
    }
}