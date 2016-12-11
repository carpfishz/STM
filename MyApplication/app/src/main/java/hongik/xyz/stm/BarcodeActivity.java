package hongik.xyz.stm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class BarcodeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        ImageView img2 = (ImageView) findViewById(R.id.imageView2);

        Intent intent = getIntent();
        String strBarcode2 = intent.getExtras().getString("strBarcode");

        Bitmap barcode = createBarcode(strBarcode2);
        img2.setImageBitmap(barcode);
        img2.invalidate();
    }

    public Bitmap createBarcode(String code){


        Bitmap bitmap =null;
        MultiFormatWriter gen = new MultiFormatWriter();
        try {
            final int WIDTH = 1500;
            final int HEIGHT = 800;
            BitMatrix bytemap = gen.encode(code, BarcodeFormat.CODE_128, WIDTH, HEIGHT);
            bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
            for (int i = 0 ; i < WIDTH ; ++i)
                for (int j = 0 ; j < HEIGHT ; ++j) {
                    bitmap.setPixel(i, j, bytemap.get(i,j) ? Color.BLACK : Color.WHITE);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
