package com.handscape.tailor.plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/2214:48
 */

public class TailorFileType extends LanguageFileType{
    public static final TailorFileType INSTANCE = new TailorFileType();

    private TailorFileType() {
        super(TailorLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Tailor file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Tailor files";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "Tailor";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return TailorIcons.FILE_ICON;
    }
}
