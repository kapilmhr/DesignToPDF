package pdf.app.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by frantic on 2/8/18.
 */

public class BitmapMaker {
    public static File saveBitMap(View drawView,boolean visiblity) {
        String filename = "/storage/emulated/0" + File.separator + System.currentTimeMillis() + ".png";
        File pictureFile = new File(filename);
        Bitmap bitmap1 = null;
        if (visiblity){
            bitmap1 = getBitmapFromLayout(drawView);
        }else {
            bitmap1 = getBitmapFromView(drawView);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(bitmap1, bitmap1.getWidth() * 3, bitmap1.getHeight() * 3, true);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        return pictureFile;
    }

    public static File saveBitMap(View drawView,boolean visiblity, Context context) {
        String destinationFileName = System.currentTimeMillis() + ".png";
        File pictureFile = new File(context.getCacheDir(), destinationFileName);
        Bitmap bitmap1 = null;
        if (visiblity){
            bitmap1 = getBitmapFromLayout(drawView);
        }else {
            bitmap1 = getBitmapFromView(drawView);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(bitmap1, bitmap1.getWidth() * 1, bitmap1.getHeight() * 1, true);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        return pictureFile;
    }

    public static File saveBitMap(View drawView,boolean visiblity, Context context, int height, int width) {
        String destinationFileName = System.currentTimeMillis() + ".png";
        File pictureFile = new File(context.getCacheDir(), destinationFileName);
        Bitmap bitmap1 = null;
        if (visiblity){
            bitmap1 = getBitmapFromLayout(drawView,height,width);
        }else {
            bitmap1 = getBitmapFromView(drawView,height,width);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(bitmap1, bitmap1.getWidth() * 1, bitmap1.getHeight() * 1, true);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        return pictureFile;
    }

/*    public static Bitmap getBitmapFromView(View v) {
        if (v.getMeasuredHeight() <= 0) {
            v.measure(DisplayUtils.getScreenWidth(), DisplayUtils.getScreenHeight());
        }

        Bitmap b = Bitmap.createBitmap(DisplayUtils.getScreenWidth(), DisplayUtils.getScreenWidth(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.layout(0,0,DisplayUtils.getScreenWidth(),DisplayUtils.getScreenHeight());
        v.draw(c);
        return b;
    }
    */

    public static Bitmap getBitmapFromView(View v) {
        v.setMinimumWidth(DisplayUtils.getScreenWidth());
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, DisplayUtils.getScreenWidth(),DisplayUtils.getScreenHeight());

        final Bitmap clusterBitmap = Bitmap.createBitmap(DisplayUtils.getScreenWidth(), DisplayUtils.getScreenHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(clusterBitmap);
        Drawable bgDrawable = v.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        v.draw(canvas);

        return clusterBitmap;
    }

    private static Bitmap getBitmapFromLayout(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap( DisplayUtils.getScreenWidth(), DisplayUtils.getScreenHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public static Bitmap getBitmapFromView(View v, int height, int width) {
        v.setMinimumWidth(DisplayUtils.getScreenWidth());
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, DisplayUtils.getScreenWidth(),DisplayUtils.getScreenHeight());
        Bitmap clusterBitmap;
        if (height>3 || width>3){
            clusterBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }else {
            clusterBitmap = Bitmap.createBitmap(DisplayUtils.getScreenWidth()*width, DisplayUtils.getScreenHeight()*height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(clusterBitmap);
        Drawable bgDrawable = v.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        v.draw(canvas);

        return clusterBitmap;
    }

    private static Bitmap getBitmapFromLayout(View view, int height, int width) {
        Bitmap returnedBitmap;
        if (height>3 || width>3){
            returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }else {
            returnedBitmap = Bitmap.createBitmap(DisplayUtils.getScreenWidth()*width, DisplayUtils.getScreenHeight()*height, Bitmap.Config.ARGB_8888);
        }        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}
