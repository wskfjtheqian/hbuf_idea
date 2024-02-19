// This is a generated file. Not intended for manual editing.
package com.hbuf.idea.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.hbuf.idea.language.psi.impl.*;

public interface HbufTypes {

  IElementType ANNOTATION = new HbufElementType("ANNOTATION");
  IElementType ANNOTATION_FIELD = new HbufElementType("ANNOTATION_FIELD");
  IElementType ANNOTATION_GROUP = new HbufElementType("ANNOTATION_GROUP");
  IElementType ANNOTATION_LIST = new HbufElementType("ANNOTATION_LIST");
  IElementType ANNOTATION_VALUES = new HbufElementType("ANNOTATION_VALUES");
  IElementType DATA_BODY = new HbufElementType("DATA_BODY");
  IElementType DATA_FIELD_LIST = new HbufElementType("DATA_FIELD_LIST");
  IElementType DATA_FIELD_STATEMENT = new HbufElementType("DATA_FIELD_STATEMENT");
  IElementType DATA_STATEMENT = new HbufElementType("DATA_STATEMENT");
  IElementType ENUM_BODY = new HbufElementType("ENUM_BODY");
  IElementType ENUM_FIELD_LIST = new HbufElementType("ENUM_FIELD_LIST");
  IElementType ENUM_FIELD_STATEMENT = new HbufElementType("ENUM_FIELD_STATEMENT");
  IElementType ENUM_STATEMENT = new HbufElementType("ENUM_STATEMENT");
  IElementType EXTENDS = new HbufElementType("EXTENDS");
  IElementType FUNC_LIST = new HbufElementType("FUNC_LIST");
  IElementType FUNC_PARAM = new HbufElementType("FUNC_PARAM");
  IElementType FUNC_STATEMENT = new HbufElementType("FUNC_STATEMENT");
  IElementType FUNC_TYPE = new HbufElementType("FUNC_TYPE");
  IElementType ID = new HbufElementType("ID");
  IElementType IDENT_NAME = new HbufElementType("IDENT_NAME");
  IElementType IMPORT_STATEMENT = new HbufElementType("IMPORT_STATEMENT");
  IElementType PACKAGE_STATEMENT = new HbufElementType("PACKAGE_STATEMENT");
  IElementType SERVER_BODY = new HbufElementType("SERVER_BODY");
  IElementType SERVER_STATEMENT = new HbufElementType("SERVER_STATEMENT");
  IElementType TYPE_ARRAY = new HbufElementType("TYPE_ARRAY");
  IElementType TYPE_BASE = new HbufElementType("TYPE_BASE");
  IElementType TYPE_MAP = new HbufElementType("TYPE_MAP");
  IElementType TYPE_STATEMENT = new HbufElementType("TYPE_STATEMENT");

  IElementType ASSIGN = new HbufTokenType("=");
  IElementType COLON = new HbufTokenType(":");
  IElementType COMMA = new HbufTokenType(",");
  IElementType COMMENT = new HbufTokenType("COMMENT");
  IElementType CRLF = new HbufTokenType("CRLF");
  IElementType DATA = new HbufTokenType("data");
  IElementType ENUM = new HbufTokenType("enum");
  IElementType GTR = new HbufTokenType(">");
  IElementType IDENT = new HbufTokenType("IDENT");
  IElementType IMPORT = new HbufTokenType("import");
  IElementType LBRACE = new HbufTokenType("{");
  IElementType LBRACK = new HbufTokenType("[");
  IElementType LPAREN = new HbufTokenType("(");
  IElementType LSS = new HbufTokenType("<");
  IElementType NUMBER = new HbufTokenType("NUMBER");
  IElementType PACKAGE = new HbufTokenType("package");
  IElementType QUESTION = new HbufTokenType("?");
  IElementType RBRACE = new HbufTokenType("}");
  IElementType RBRACK = new HbufTokenType("]");
  IElementType RPAREN = new HbufTokenType(")");
  IElementType SEMICOLON = new HbufTokenType(";");
  IElementType SERVER = new HbufTokenType("server");
  IElementType SPACE = new HbufTokenType(" ");
  IElementType STRING = new HbufTokenType("STRING");
  IElementType TYPES = new HbufTokenType("TYPES");
  IElementType VOID = new HbufTokenType("void");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ANNOTATION) {
        return new HbufAnnotationImpl(node);
      }
      else if (type == ANNOTATION_FIELD) {
        return new HbufAnnotationFieldImpl(node);
      }
      else if (type == ANNOTATION_GROUP) {
        return new HbufAnnotationGroupImpl(node);
      }
      else if (type == ANNOTATION_LIST) {
        return new HbufAnnotationListImpl(node);
      }
      else if (type == ANNOTATION_VALUES) {
        return new HbufAnnotationValuesImpl(node);
      }
      else if (type == DATA_BODY) {
        return new HbufDataBodyImpl(node);
      }
      else if (type == DATA_FIELD_LIST) {
        return new HbufDataFieldListImpl(node);
      }
      else if (type == DATA_FIELD_STATEMENT) {
        return new HbufDataFieldStatementImpl(node);
      }
      else if (type == DATA_STATEMENT) {
        return new HbufDataStatementImpl(node);
      }
      else if (type == ENUM_BODY) {
        return new HbufEnumBodyImpl(node);
      }
      else if (type == ENUM_FIELD_LIST) {
        return new HbufEnumFieldListImpl(node);
      }
      else if (type == ENUM_FIELD_STATEMENT) {
        return new HbufEnumFieldStatementImpl(node);
      }
      else if (type == ENUM_STATEMENT) {
        return new HbufEnumStatementImpl(node);
      }
      else if (type == EXTENDS) {
        return new HbufExtendsImpl(node);
      }
      else if (type == FUNC_LIST) {
        return new HbufFuncListImpl(node);
      }
      else if (type == FUNC_PARAM) {
        return new HbufFuncParamImpl(node);
      }
      else if (type == FUNC_STATEMENT) {
        return new HbufFuncStatementImpl(node);
      }
      else if (type == FUNC_TYPE) {
        return new HbufFuncTypeImpl(node);
      }
      else if (type == ID) {
        return new HbufIdImpl(node);
      }
      else if (type == IDENT_NAME) {
        return new HbufIdentNameImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new HbufImportStatementImpl(node);
      }
      else if (type == PACKAGE_STATEMENT) {
        return new HbufPackageStatementImpl(node);
      }
      else if (type == SERVER_BODY) {
        return new HbufServerBodyImpl(node);
      }
      else if (type == SERVER_STATEMENT) {
        return new HbufServerStatementImpl(node);
      }
      else if (type == TYPE_ARRAY) {
        return new HbufTypeArrayImpl(node);
      }
      else if (type == TYPE_BASE) {
        return new HbufTypeBaseImpl(node);
      }
      else if (type == TYPE_MAP) {
        return new HbufTypeMapImpl(node);
      }
      else if (type == TYPE_STATEMENT) {
        return new HbufTypeStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
