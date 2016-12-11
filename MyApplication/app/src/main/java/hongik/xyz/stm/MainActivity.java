package hongik.xyz.stm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class MainActivity extends AppCompatActivity {

    private Button pointButton;
    private Button eventButton;
    private Button mapButton;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointButton = (Button) findViewById(R.id.point_button);
        mapButton = (Button) findViewById(R.id.map_button);
        final String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("Andorid ID value:", androidId);

        img = (ImageView) findViewById(R.id.imageView1);
//        final String strBarcode = "987654321";
        Bitmap barcode = createBarcode(androidId);
        img.setImageBitmap(barcode);
        img.invalidate();
    


        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toBarcode = new Intent(getApplicationContext(), BarcodeActivity.class);
                toBarcode.putExtra("strBarcode", androidId);
                setResult(RESULT_OK, toBarcode);
                startActivity(toBarcode);
                finish();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMap = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(toMap);
                finish();
            }
        });
    }

    public Bitmap createBarcode(String code){


        Bitmap bitmap =null;
        MultiFormatWriter gen = new MultiFormatWriter();
        try {
            final int WIDTH = 840;
            final int HEIGHT = 160;
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
