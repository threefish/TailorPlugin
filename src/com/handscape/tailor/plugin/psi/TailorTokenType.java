package com.handscape.tailor.plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.handscape.tailor.plugin.TailorLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/2215:06
 */

public class TailorTokenType extends IElementType {
    public TailorTokenType(@NotNull @NonNls String debugName) {
        super(debugName, TailorLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "TailorTokenType." + super.toString();
    }
}