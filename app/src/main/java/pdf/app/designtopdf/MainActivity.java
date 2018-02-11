package pdf.app.designtopdf;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import pdf.app.library.Callback;
import pdf.app.library.DesignPdf;

import static pdf.app.library.utils.Constants.SPLASH_DISPLAY_LENGTH;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout,linearLayout;
    View views;
    private String filePath = Environment.getExternalStorageDirectory().toString() + "/PDF/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.layout);
        views = new View(this);
        /**
         * make sure that visibility is false when the new view is passed which is not rendered
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("a","a");
                views = LayoutInflater.from(MainActivity.this).inflate(R.layout.test_layout,null);
                layout = views.findViewById(R.id.relLayout);
//                views.layout(linearLayout.getLeft(),linearLayout.getTop(),linearLayout.getRight(),linearLayout.getBottom());
                DesignPdf.with(MainActivity.this).addView(R.layout.test_layout).setFilePath(filePath,"easy")
                        .setMargins(0,0,10,10)
                        .setViewVisibilty(false)
                        .create(new Callback() {
                            @Override
                            public void onSuccess(File file) {
                                Toast.makeText(MainActivity.this, "path: "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                                Log.d("path",file.getAbsolutePath());
                            }

                            @Override
                            public void onError(Exception e) {
                                e.printStackTrace();
                            }
                        });

            }
        }, SPLASH_DISPLAY_LENGTH);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * by default visibility is on
                 * give the file path and filename
                 * multiplier is setting the size of your screen by default its 1
                 * if you have long lengthy design then use heright multiplier to 2
                 */
                DesignPdf.with(MainActivity.this).addView(linearLayout)
                        .setFilePath(filePath,"stupid")
                        .setHeightnWidthMultiplier(1,1)
                        .create(new Callback() {
                    @Override
                    public void onSuccess(File file) {
                        Toast.makeText(MainActivity.this, "path: "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        Log.d("path",file.getAbsolutePath());
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });


            }
        });
    }

}
