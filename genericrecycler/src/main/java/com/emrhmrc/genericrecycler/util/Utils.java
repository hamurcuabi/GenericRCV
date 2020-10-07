package com.emrhmrc.genericrecycler.util;

import android.content.Context;

/**
 * Created by hamurcuabi on 08,October,2020
 **/
public class Utils {
    public static int dpFromPx(final Context context, final float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
