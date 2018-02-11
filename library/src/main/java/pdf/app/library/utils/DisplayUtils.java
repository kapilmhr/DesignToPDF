package pdf.app.library.utils;

import android.content.res.Resources;

/**
 * Created by frantic on 2/8/18.
 */

public class DisplayUtils {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
