package pdf.app.library;

import java.io.File;

/**
 * Created by frantic on 2/8/18.
 */

public interface Callback {
    void onSuccess(File file);

    void onError(Exception e);

}

