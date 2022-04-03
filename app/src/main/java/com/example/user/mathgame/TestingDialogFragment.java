package com.example.user.mathgame;


import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class TestingDialogFragment extends DialogFragment {

    protected Bitmap qrcode;
    protected TextView tv_tip;
    protected ImageView iv_code;
    protected Button btn_dialog;
    //二维码的尺寸
    private static final int QRCODE_SIZE = 700;
    private SetOnClickDialogListener mSetOnClickListener;

    public void onSetClickDialogListener(SetOnClickDialogListener listener) {
        this.mSetOnClickListener = listener;
    }


    public interface SetOnClickDialogListener {
        void onClickDialogListener(int type, boolean boolClick, Bitmap content);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(true);
        }
        View mView = inflater.inflate(R.layout.dialog_test, container, false);
        tv_tip = mView.findViewById(R.id.tv_tip);
        btn_dialog = mView.findViewById(R.id.btn_dialog);
        iv_code = mView.findViewById(R.id.iv_code);


        //  String text = tvContent.getText().toString();
        Gson gson = new Gson();
        CodeGetBean mScanBean = new CodeGetBean();
        mScanBean.setWebsit("https://mg.mindordz.com/mindor/download_apply?id=96");
        mScanBean.setDevKey("2022年3月30日14点9375分");
        mScanBean.setDevSecret("9a9b16d688174a23a0326949b23e51cb");
        mScanBean.setUserId("minApp109911");
        mScanBean.setDevices("zcz001101,zcz001102");
        mScanBean.setPlatform("android");
        mScanBean.setModel("RedMi7");
        mScanBean.setType("share");
        String text = gson.toJson(mScanBean);


        if (!TextUtils.isEmpty(text)) {
            Bitmap dCode = create2DCode(text);
            iv_code.setImageBitmap(dCode);
            //得到一个大小为二维码1/5的bitmap
            //  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.red, null);
            //生成缩略图
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bitmap2, QRCODE_SIZE / 5, QRCODE_SIZE / 5, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            createQRCodeBitmapWithPortrait(dCode, thumbnail);
            //   saveQRCode();
        }

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = iv_code.getDrawable();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap1 = bitmapDrawable.getBitmap();

                getDialog().cancel();
                if (mSetOnClickListener != null) {
                    mSetOnClickListener.onClickDialogListener(0, true, bitmap1);
                }
            }
        });

        return mView;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    /**
     * 在二维码上绘制头像
     */
    private void createQRCodeBitmapWithPortrait(Bitmap qr, Bitmap portrait) {
        // 头像图片的大小
        int portrait_W = portrait.getWidth();
        int portrait_H = portrait.getHeight();

        // 设置头像要显示的位置，即居中显示
        int left = (QRCODE_SIZE - portrait_W) / 2;
        int top = (QRCODE_SIZE - portrait_H) / 2;
        int right = left + portrait_W;
        int bottom = top + portrait_H;
        Rect rect1 = new Rect(left, top, right, bottom);

        // 取得qr二维码图片上的画笔，即要在二维码图片上绘制我们的头像
        Canvas canvas = new Canvas(qr);

        // 设置我们要绘制的范围大小，也就是头像的大小范围
        Rect rect2 = new Rect(0, 0, portrait_W, portrait_H);
        // 开始绘制
        canvas.drawBitmap(portrait, rect2, rect1, null);
    }


    public static Bitmap create2DCode(String string) {
        return Create2DCode(string, 0, 0);
    }

    /**
     * @param str 用字符串生成二维码
     */
    public static Bitmap Create2DCode(String str, int codeWidth, int codeHeight) {
        // 用于设置QR二维码参数
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别——这里选择最高H级别
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //判断用户指定的二维码大小
        if (codeWidth < 100 || codeHeight < 100 || codeWidth > 600 || codeHeight > 600) {
            codeWidth = QRCODE_SIZE;
            codeHeight = QRCODE_SIZE;
        }
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, codeWidth, codeHeight, qrParam);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                } else {//这个else要加上去，否者保存的二维码全黑
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
