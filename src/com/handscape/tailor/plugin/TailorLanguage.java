package com.handscape.tailor.plugin;

import com.intellij.lang.Language;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/2214:42
 */

public class TailorLanguage extends Language {
    public static final TailorLanguage INSTANCE = new TailorLanguage();

    private TailorLanguage() {
        super("jst");
    }
}