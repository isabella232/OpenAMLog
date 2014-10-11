// This is a generated file. Not intended for manual editing.
package org.forgerock.openam.logreader.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static org.forgerock.openam.logreader.psi.OpenAMLogTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class OpenAMLogParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("org.forgerock.openam.logreader.parser.OpenAMLogParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == LOG) {
      result_ = log(builder_, 0);
    }
    else if (root_ == LOGCONTENT) {
      result_ = logcontent(builder_, 0);
    }
    else if (root_ == LOGTITLE) {
      result_ = logtitle(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return openAMLogFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // COMMENT|log|CRLF
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = log(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // logtitle CRLF logcontent
  public static boolean log(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "log")) return false;
    if (!nextTokenIs(builder_, DEBUG_NAME)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = logtitle(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CRLF);
    result_ = result_ && logcontent(builder_, level_ + 1);
    exit_section_(builder_, marker_, LOG, result_);
    return result_;
  }

  /* ********************************************************** */
  // LOG_LINE CRLF  (LOG_LINE? CRLF)* END_OF_LOG_CONTENT | LOG_LINE CRLF  (LOG_LINE? CRLF)* LOG_LINE END_OF_LOG_CONTENT | LOG_LINE END_OF_LOG_CONTENT
  public static boolean logcontent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent")) return false;
    if (!nextTokenIs(builder_, LOG_LINE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = logcontent_0(builder_, level_ + 1);
    if (!result_) result_ = logcontent_1(builder_, level_ + 1);
    if (!result_) result_ = parseTokens(builder_, 0, LOG_LINE, END_OF_LOG_CONTENT);
    exit_section_(builder_, marker_, LOGCONTENT, result_);
    return result_;
  }

  // LOG_LINE CRLF  (LOG_LINE? CRLF)* END_OF_LOG_CONTENT
  private static boolean logcontent_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LOG_LINE, CRLF);
    result_ = result_ && logcontent_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END_OF_LOG_CONTENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (LOG_LINE? CRLF)*
  private static boolean logcontent_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!logcontent_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "logcontent_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // LOG_LINE? CRLF
  private static boolean logcontent_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = logcontent_0_2_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CRLF);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LOG_LINE?
  private static boolean logcontent_0_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_0_2_0_0")) return false;
    consumeToken(builder_, LOG_LINE);
    return true;
  }

  // LOG_LINE CRLF  (LOG_LINE? CRLF)* LOG_LINE END_OF_LOG_CONTENT
  private static boolean logcontent_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LOG_LINE, CRLF);
    result_ = result_ && logcontent_1_2(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, LOG_LINE, END_OF_LOG_CONTENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (LOG_LINE? CRLF)*
  private static boolean logcontent_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_1_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!logcontent_1_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "logcontent_1_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // LOG_LINE? CRLF
  private static boolean logcontent_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_1_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = logcontent_1_2_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CRLF);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LOG_LINE?
  private static boolean logcontent_1_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logcontent_1_2_0_0")) return false;
    consumeToken(builder_, LOG_LINE);
    return true;
  }

  /* ********************************************************** */
  // DEBUG_NAME SEPARATOR DATE SEPARATOR THREAD_NAME
  public static boolean logtitle(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "logtitle")) return false;
    if (!nextTokenIs(builder_, DEBUG_NAME)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, DEBUG_NAME, SEPARATOR, DATE, SEPARATOR, THREAD_NAME);
    exit_section_(builder_, marker_, LOGTITLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // item_*
  static boolean openAMLogFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "openAMLogFile")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "openAMLogFile", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

}
