package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufFuncTypeElement;
import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

//TODO 自动布全提示
public class HbufCompletionContributor extends CompletionContributor {
    public HbufCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufTypes.TYPE_STATEMENT),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("int8"));
                        resultSet.addElement(LookupElementBuilder.create("int16"));
                        resultSet.addElement(LookupElementBuilder.create("int32"));
                        resultSet.addElement(LookupElementBuilder.create("int64"));
                    }
                }
        );
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(HbufFuncTypeElement.class), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters completionParameters,
                                          @NotNull ProcessingContext processingContext,
                                          @NotNull CompletionResultSet completionResultSet) {
                completionResultSet.addElement(LookupElementBuilder.create("Heqian"));

            }
        });
    }

}