package pdf.app.library;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

import pdf.app.library.utils.Constants;

/**
 * Created by frantic on 2/8/18.
 */

public class DesignPdf {
    private Context context;
    private View view;
    private int quality = 1;
    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;
    private int height = 1;
    private int width = 1;
    private boolean isVisible = true;
    private String filePath = Constants.PDF_FILE_PATH;
    private String filename = "sample" + Constants.EXTENSIONADDER;
    /**
     *
     * @param context
     * @return
     */
    public static DesignPdf with(@NonNull Context context){
        return new DesignPdf(context);
    }

    /**
     *
     * @param context
     */
    private DesignPdf(@NonNull Context context){
        this.context = context;
    }

    /**
     *
     * @param viewGroup
     * @return
     */
    public DesignPdf addView(@NonNull View viewGroup){
        this.view = viewGroup;
        return this;
    }

    public DesignPdf addView(@NonNull final int layout){
        this.view = new View(context);
        this.view = LayoutInflater.from(context).inflate(layout,null);
        return this;
    }


    public DesignPdf setViewVisibilty(boolean isVisible){
        this.isVisible = isVisible;
        return this;
    }

    public DesignPdf setFilePath(String filePath, String filename){
        this.filePath = filePath;
        this.filename = filename + Constants.EXTENSIONADDER;
        return this;
    }

/*    public DesignPdf setHeightMultiplier(int height) {
        if (height<1 && height>2){
            height =1;
        }
        this.height = height;
        return this;
    }

    public DesignPdf setWidthMultiplier(int width) {
        if (width<1 && width>2){
            width =1;
        }
        this.width = width;
        return this;
    }*/

    public DesignPdf setHeightnWidth(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }
    public DesignPdf setHeightnWidthMultiplier(int height, int width) {
        if (height<1 && height>2){
            height =1;
        }
        if (width<1 && width>2){
            width =1;
        }
        this.height = height;
        this.width = width;
        return this;
    }



    public DesignPdf setMargins(int left, int right, int top, int bottom){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        return this;
    }

    /**
     * set the quality of the pixels from 1 - 3
     * @param i
     * @return
     */
    public DesignPdf setQuality(int i){
        this.quality = i;
        return this;
    }

    public void create(){
        PdfTask pdfTask = new PdfTask(context, view);
        pdfTask.setVisibility(isVisible);
        pdfTask.setFilePath(filePath+filename);
        pdfTask.setLeft(left);
        pdfTask.setRight(right);
        pdfTask.setTop(top);
        pdfTask.setBottom(bottom);
        pdfTask.setHeight(height);
        pdfTask.setWidth(width);
        pdfTask.setHeightnWidth(height,width);
        pdfTask.execute();
    }

    public void create(final Callback callback){
        PdfTask pdfTask = new PdfTask(context, view);
        pdfTask.setVisibility(isVisible);
        pdfTask.setFilePath(filePath+filename);
        pdfTask.setLeft(left);
        pdfTask.setRight(right);
        pdfTask.setTop(top);
        pdfTask.setHeight(height);
        pdfTask.setWidth(width);
        pdfTask.setHeightnWidth(height,width);
        pdfTask.setBottom(bottom);
        pdfTask.setListener(new PdfTask.PdfTaskListener() {
            @Override
            public void onSuccess(File file) {
                callback.onSuccess(file);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
        pdfTask.execute();
    }

}
