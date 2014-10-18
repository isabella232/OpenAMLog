package org.forgerock.openam.logreader.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.forgerock.openam.logreader.icons.OpenAMLogIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class OpenAMLogColorSettingsPage implements ColorSettingsPage {
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
        return "# You are reading the \".properties\" entry.\n" +
                "amAuth:09 / 17 / 2014 07:15:06:802 AM CEST:Thread[ajp - bio - 8009 - exec - 95, 5, main]\n " +
                "postProcessOnSuccess\n" +
                "amAuth:09/17/2014 07:15:06:802 AM CEST: Thread[ajp-bio-8009-exec-95,5,main]\n" +
                "postLoginClassSet = [be.mediaid.postauthentication.RegistrationPAP]\n" +
                "amAuth:09/17/2014 07:15:06:802 AM CEST: Thread[ajp-bio-8009-exec-95,5,main]\n" +
                "setPostLoginInstances : be.mediaid.postauthentication.RegistrationPAP\n" +
                "amAuth:09/17/2014 07:15:06:802 AM CEST: Thread[ajp-bio-8009-exec-95,5,main]\n" +
                "setPostLoginInstances : 1\n" +
                "amAuth:09/17/2014 07:15:06:802 AM CEST: Thread[ajp-bio-8009-exec-95,5,main]\n" +
                "postLoginProcess Class Name is : be.mediaid.postauthentication.RegistrationPAP";
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
        return "OpenAMLog";
    }
}