package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.*;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiErrorElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class HbufCompletionContributor extends CompletionContributor {
    public HbufCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufTypes.STRING)
                        .withParent(HbufImportElement.class),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufTypes.IDENT)
                        .withParent(PsiErrorElement.class),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("package "));
                        resultSet.addElement(LookupElementBuilder.create("import "));
                        resultSet.addElement(LookupElementBuilder.create("data "));
                        resultSet.addElement(LookupElementBuilder.create("enum "));
                        resultSet.addElement(LookupElementBuilder.create("server "));
                    }
                }
        );

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufTypes.IDENT)
                        .withParent(HbufDataFieldsElement.class),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        for (String item : HbufUtil.DataBaseType) {
                            resultSet.addElement(LookupElementBuilder.create(item));
                        }
                        for (HbufEnumElement item : HbufUtil.findEnum(parameters.getEditor().getProject())) {
                            resultSet.addElement(LookupElementBuilder.create(item.getName()));
                        }
                        for (HbufDataElement item : HbufUtil.findData(parameters.getEditor().getProject())) {
                            resultSet.addElement(LookupElementBuilder.create(item.getName()));
                        }
                    }
                }
        );

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufTypes.IDENT)
                        .withParent(HbufServerFuncsElement.class),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        for (HbufDataElement item : HbufUtil.findData(parameters.getEditor().getProject())) {
                            resultSet.addElement(LookupElementBuilder.create(item.getName()));
                        }
                    }
                }
        );

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufTypes.IDENT)
                        .withParent(HbufServerFuncElement.class),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        for (HbufDataElement item : HbufUtil.findData(parameters.getEditor().getProject())) {
                            resultSet.addElement(LookupElementBuilder.create(item.getName()));
                        }
                    }
                }
        );


    }
}
