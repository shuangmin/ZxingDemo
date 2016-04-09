package com.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends FragmentActivity {
    private static final int REQUEST_CODE = 0x345;
    private TextView mTvScanResult = null;
    private ImageView mIvQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvScanResult = (TextView) findViewById(R.id.tv_scan_result);
        mIvQr = (ImageView) findViewById(R.id.iv_QR);
    }

    /**
     * 扫描二维码
     * @param v
     */
    public void toScan(View v)
    {
        startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE);
    }

    /**
     * 生成二维码
     * @param v
     */
    public void generateSQCode(View v)
    {
        Bitmap bitmap = EncodingUtils.createQRCode("大家好", 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        mIvQr.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTvScanResult.setText(result);
        }
    }
}
