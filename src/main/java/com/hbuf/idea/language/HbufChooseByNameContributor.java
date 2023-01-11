package com.hbuf.idea.language;

import com.hbuf.idea.language.psi.HbufUtil;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiNameIdentifierOwner;

import java.util.ArrayList;
import java.util.List;

public class HbufChooseByNameContributor implements ChooseByNameContributor {
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<PsiNameIdentifierOwner> properties = HbufUtil.findProperties(project);
        List<String> names = new ArrayList<>(properties.size());
        for (PsiNameIdentifierOwner property : properties) {
            if (property.getName() != null && property.getName().length() > 0) {
                names.add(property.getName());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        List<PsiNameIdentifierOwner> properties = HbufUtil.findProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}
