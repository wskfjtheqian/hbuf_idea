package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufDataElement;
import com.hbuf.idea.language.psi.HbufNameElement;
import com.hbuf.idea.language.psi.HbufServerElement;
import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class HbufLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (element instanceof HbufDataElement) {
            Collection<HbufDataElement> list = HbufUtil.getDataSub((HbufDataElement) element);
            if (!list.isEmpty()) {
                Collection<HbufNameElement> name = new ArrayList();
                for (HbufDataElement item : list) {
                    name.add(item.getIdentName());
                }
                NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder.create(HbufIcons.FILE)
                        .setTargets(name)
                        .setTooltipText("Navigate to Data Inheritor");
                result.add(builder.createLineMarkerInfo(((HbufDataElement) element).getIdentName()));
                return;
            }
        } else if (element instanceof HbufServerElement) {
            Collection<HbufServerElement> list = HbufUtil.getServerSub((HbufServerElement) element);
            if (!list.isEmpty()) {
                Collection<HbufNameElement> name = new ArrayList();
                for (HbufServerElement item : list) {
                    name.add(item.getIdentName());
                }
                NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder.create(HbufIcons.FILE)
                        .setTargets(name)
                        .setTooltipText("Navigate to Server Inheritor");
                result.add(builder.createLineMarkerInfo(((HbufServerElement) element).getIdentName()));
                return;
            }
        }
    }

}