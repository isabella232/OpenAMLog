package org.forgerock.openam.logreader.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.forgerock.openam.logreader.OpenAMLogFileType;
import org.forgerock.openam.logreader.OpenAMLogLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class OpenAMLogFile extends PsiFileBase {
    public OpenAMLogFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, OpenAMLogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return OpenAMLogFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "OpenAM Log File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}