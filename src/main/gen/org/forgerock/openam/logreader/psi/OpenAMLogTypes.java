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

// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.forgerock.openam.logreader.psi.impl.OpenAMLogLogImpl;
import org.forgerock.openam.logreader.psi.impl.OpenAMLogLogcontentImpl;
import org.forgerock.openam.logreader.psi.impl.OpenAMLogLogtitleImpl;

public interface OpenAMLogTypes {

    IElementType LOG = new OpenAMLogElementType("LOG");
    IElementType LOGCONTENT = new OpenAMLogElementType("LOGCONTENT");
    IElementType LOGTITLE = new OpenAMLogElementType("LOGTITLE");

    IElementType COMMENT = new OpenAMLogTokenType("COMMENT");
    IElementType CRLF = new OpenAMLogTokenType("CRLF");
    IElementType DATE = new OpenAMLogTokenType("DATE");
    IElementType DEBUG_NAME = new OpenAMLogTokenType("DEBUG_NAME");
    IElementType END_OF_LOG_CONTENT = new OpenAMLogTokenType("END_OF_LOG_CONTENT");
    IElementType LOG_LINE = new OpenAMLogTokenType("LOG_LINE");
    IElementType SEPARATOR = new OpenAMLogTokenType("SEPARATOR");
    IElementType THREAD_NAME = new OpenAMLogTokenType("THREAD_NAME");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == LOG) {
                return new OpenAMLogLogImpl(node);
            } else if (type == LOGCONTENT) {
                return new OpenAMLogLogcontentImpl(node);
            } else if (type == LOGTITLE) {
                return new OpenAMLogLogtitleImpl(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
