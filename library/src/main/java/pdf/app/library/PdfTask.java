package pdf.app.library;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import pdf.app.library.utils.BitmapMaker;
import pdf.app.library.utils.Constants;

/**
 * Created by frantic on 2/8/18.
 */

public class PdfTask extends AsyncTask<Void,Void,Void> {
    private String pdfPath;
    private String bitmapPath;
    private View view;
    private Context context;
    private String filePath = Constants.FILEPATH;
    private String imagePath ;

    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;
    private int height = 1;
    private int width = 1;
    private boolean visibility = true;

    public PdfTask(Context context, View view) {
        this.context = context;
        this.view = view;
        this.pdfPath = filePath;
        this.bitmapPath = imagePath;
    }

    public PdfTask(Context context, View view,String pdfPath) {
        this.context = context;
        this.view = view;
        this.pdfPath = filePath;
    }
    public void setHeightnWidth(int height, int width) {
        this.height = height;
        this.width = width;
    }


    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public interface PdfTaskListener {
        void onSuccess(File file);
        void onError(Exception e);
    }

    private PdfTaskListener mListener;

    final public void setListener(PdfTaskListener listener) {
        mListener = listener;
    }


    public PdfTask(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void createPdf(){
        Document document=new Document();
        document.setMargins(left,right,top,bottom);
        try {
            PdfWriter.getInstance(document,new FileOutputStream(filePath)); //  Change pdf's name.
            document.open();
            Image img =Image.getInstance(imagePath);
            img.setAlignment(Element.ALIGN_CENTER);
//            img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP|Image.TEXTWRAP);
            //img.setAlignment(Image.LEFT| Image.TEXTWRAP);

            float width = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            float height = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
            img.scaleToFit(width, height);

            document.add(img);
            document.close();
        } catch (DocumentException e) {
            mListener.onError(e);
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mListener.onError(e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mListener.onError(e);
        } catch (IOException e) {
            e.printStackTrace();
            mListener.onError(e);
        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
        File file;
        if (height>1 || width > 1){
            file = BitmapMaker.saveBitMap(view,visibility,context,height,width);
        }else {
            file = BitmapMaker.saveBitMap(view,visibility,context);
        }
        imagePath = file.getAbsolutePath();
        File folder = new File(Constants.PDF_FILE_PATH);
        if (!folder.exists()){
            folder.mkdir();
        }
        createPdf();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mListener.onSuccess(new File(filePath));
    }
}
