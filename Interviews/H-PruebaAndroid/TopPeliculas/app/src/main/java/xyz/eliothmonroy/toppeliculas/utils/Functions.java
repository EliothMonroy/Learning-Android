package xyz.eliothmonroy.toppeliculas.utils;

import android.content.res.Resources;

/**
 * Funciones de utilería
 */
public class Functions {
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
    
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    
    public static int calculateHeight(int screenSize) {
        return Functions.dpToPx((int) (((float) screenSize) / Constants.SCREEN_FACTOR_HEIGHT));
    }
    
    public static int calculateHeightDetail(int screenSize) {
        return Functions.dpToPx((int) (((float) screenSize) / Constants.SCREEN_FACTOR_HEIGHT_DETAIL));
    }
    
    public static int calculateWidth(int screenSize) {
        return Functions.dpToPx((int) (((float) screenSize) / Constants.SCREEN_FACTOR_WIDTH));
    }
    
    public static int calculateWidthDetail(int screenSize) {
        return Functions.dpToPx((int) (((float) screenSize) / Constants.SCREEN_FACTOR_WIDTH_DETAIL));
    }
    
}
