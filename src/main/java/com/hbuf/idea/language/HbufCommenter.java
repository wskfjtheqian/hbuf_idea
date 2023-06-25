package com.hbuf.idea.language;

import com.intellij.lang.Commenter;
public class HbufCommenter implements Commenter {

    @Override
    public String getLineCommentPrefix() {
        return "//";
    }

    
    @Override
    public String getBlockCommentPrefix() {
        return "";
    }

    
    @Override
    public String getBlockCommentSuffix() {
        return null;
    }

    
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }

}