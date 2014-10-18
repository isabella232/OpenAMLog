package org.forgerock.openam.logreader.parser;

import com.intellij.testFramework.ParsingTestCase;

public class OpenAMLogParsingTest extends ParsingTestCase {
    public OpenAMLogParsingTest() {
        super("", "lfr", new OpenAMLogParserDefinition());
    }

    public void testParsingTestData() {
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "test/resources/testData";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
