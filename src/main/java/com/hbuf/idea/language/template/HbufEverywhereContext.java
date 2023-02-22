/*
 * Copyright 2013-2015 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hbuf.idea.language.template;


import com.hbuf.idea.language.psi.HbufTypes;
import com.intellij.codeInsight.template.EverywhereContextType;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.NotNull;

public class HbufEverywhereContext extends HbufLiveTemplatesContext {
    protected HbufEverywhereContext() {
        super("HBUF", "HBUF", EverywhereContextType.class);
    }


    @Override
    protected boolean isInContext(@NotNull PsiElement element) {
        return !(element instanceof PsiComment ||
                element instanceof LeafPsiElement && ((LeafPsiElement) element).getElementType() == HbufTypes.STRING);
    }
}
