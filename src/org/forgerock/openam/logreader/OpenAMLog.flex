package org.forgerock.openam.logreader;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.forgerock.openam.logreader.psi.OpenAMLogTypes;
import com.intellij.psi.TokenType;

%%

%class OpenAMLogLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%{
  StringBuffer string = new StringBuffer();
%}
CRLF= \n|\r|\r\n
END_OF_LINE= \n

WHITE_SPACE=[\ \t\f]
FIRST_VALUE_CHARACTER=[^ \n\r\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*

SEPARATOR=[:]
KEY_CHARACTER=[^:=\ \n\r\t\f\\] | "\\"{CRLF} | "\\".

DEBUG_NAME=[^:\n]+

DAY=[0-9]{2}
MONTH=[0-9]{2}
YEAR=[0-9]{4}
HOUR=[0-9]{2}
MIN=[0-9]{2}
SEC=[0-9]{2}
MS=[0-9]{3}
AM_PM=("AM"|"PM")
TIME_ZONE=[^:]+
DATE={DAY} "/" {MONTH} "/" {YEAR} {WHITE_SPACE} {HOUR} ":" {MIN} ":" {SEC} ":" {MS} {WHITE_SPACE} {AM_PM} {WHITE_SPACE} {TIME_ZONE}

THREAD_NAME=[^:\n]+

LOG_HEADER= {DEBUG_NAME} {SEPARATOR} {DATE} {SEPARATOR} {THREAD_NAME}

LOG_LINE=[^\n]+

%state CONTENT, WAIT_DEBUG_NAME, WAIT_SEPARATOR_BETWEEN_DEBUG_NAME_AND_DATE, WAIT_DATE, WAIT_SEPARATOR_BETWEEN_DATE_AND_THREAD_NAME, WAIT_THREAD_NAME, WAIT_END_LINE, WAIT_LOG_VALUE

%%
/*  yypushback(yytext().length()); */
<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return OpenAMLogTypes.COMMENT; }

<YYINITIAL> {CRLF}                                          { return OpenAMLogTypes.CRLF; }

<YYINITIAL> {LOG_HEADER}                                    { yypushback(yytext().length()); yybegin(WAIT_DEBUG_NAME); }

<YYINITIAL> {LOG_LINE}                                      { yypushback(yytext().length()); yybegin(CONTENT); }

<WAIT_DEBUG_NAME> {DEBUG_NAME}                              { yybegin(WAIT_SEPARATOR_BETWEEN_DEBUG_NAME_AND_DATE); return OpenAMLogTypes.DEBUG_NAME; }

<WAIT_SEPARATOR_BETWEEN_DEBUG_NAME_AND_DATE> {SEPARATOR}    { yybegin(WAIT_DATE); return OpenAMLogTypes.SEPARATOR; }

<WAIT_DATE> {DATE}                                          { yybegin(WAIT_SEPARATOR_BETWEEN_DATE_AND_THREAD_NAME); return OpenAMLogTypes.DATE; }

<WAIT_SEPARATOR_BETWEEN_DATE_AND_THREAD_NAME> {SEPARATOR}   { yybegin(WAIT_THREAD_NAME); return OpenAMLogTypes.SEPARATOR; }

<WAIT_THREAD_NAME> {THREAD_NAME}                            { yybegin(WAIT_END_LINE); return OpenAMLogTypes.THREAD_NAME; }

<WAIT_END_LINE> {END_OF_LINE}                               { yybegin(YYINITIAL); return OpenAMLogTypes.CRLF; }

<CONTENT> {LOG_HEADER}                                      { yypushback(yytext().length()); yybegin(WAIT_DEBUG_NAME); return OpenAMLogTypes.END_OF_LOG_CONTENT;}

<CONTENT> {LOG_LINE}                                        { return OpenAMLogTypes.LOG_LINE;}

<CONTENT> {CRLF}                                            { return OpenAMLogTypes.CRLF; }

<CONTENT> <<EOF>>                                            { yypushback(yytext().length()); yybegin(YYINITIAL); return OpenAMLogTypes.END_OF_LOG_CONTENT; }

.                                                           { return TokenType.BAD_CHARACTER; }