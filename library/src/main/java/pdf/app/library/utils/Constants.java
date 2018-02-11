package pdf.app.library.utils;

import android.os.Environment;

/**
 * Created by frantic on 2/8/18.
 */

public class Constants {

    public static final String EXTENSIONADDER = ".pdf";
    public static final String PDF_FILE_PATH = Environment.getExternalStorageDirectory().toString() + "/PDF/";
    public static final long SPLASH_DISPLAY_LENGTH = 2000;
    public static final String FILEPATH = PDF_FILE_PATH+ "sample.pdf";
}
