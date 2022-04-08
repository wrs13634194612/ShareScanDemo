package com.example.user.mathgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShareDeviceActivity extends Activity {
    //    private List<ShareBean> mDevices;
    private List<Device> mDevices;

    private List<String> dataList;
    private RecyclerView gv_devices;
    private OnlineShareDeviceListAdapterShare mAdapter;
    private CommonTitleBar commonTitleBar;
    private String url = "https://www.mindordz.com:8181/mindor/shc/createDeviceShare";


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 102) {
                //刷新页面  如果为null  返回上一页  {"code":200,"message":"解除授权成功"}
                String post = (String) message.obj;
                Gson gson = new Gson();
                DeleteShareBean mDeleteShareBean = gson.fromJson(post, DeleteShareBean.class);
                if (mDeleteShareBean.getCode() == 200) {
                    //延时 效果  // 表示成功
                    Timer timer = new Timer();
                    timer.schedule(task1, 500);
                } else {
                    Timer timer = new Timer();
                    timer.schedule(task2, 500);
                }
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_device);
        gv_devices = findViewById(R.id.rv_online_devices);
        Button btn_share = findViewById(R.id.btn_share);
        Button btn_scan = findViewById(R.id.btn_scan);
        commonTitleBar = findViewById(R.id.title_activity_qr);

        mDevices = new ArrayList<>();
        dataList = new ArrayList<>();
        mDevices.addAll((ArrayList<Device>) getIntent().getSerializableExtra("list"));
        Log.e("TAG", "" + mDevices);
        //sig 单火线；swt零火线；通断器；五孔插座；智能台灯
        HashMap<String, Integer> map = new HashMap<>();
        map.put("HPS001", R.mipmap.mindor_share_hps001_off);
        map.put("HPS002", R.mipmap.mindor_share_hps002_off);
        map.put("HPS003", R.mipmap.mindor_share_hps003_off);
        map.put("HPS004", R.mipmap.mindor_share_hps004_off);
        map.put("zcz001", R.mipmap.mindor_share_zcz001_off);
        map.put("zcz002", R.mipmap.mindor_share_zcz002_off);
        map.put("zcz003", R.mipmap.mindor_share_zcz003_off);
        map.put("zcz004", R.mipmap.mindor_share_zcz004_off);
        map.put("SIG001", R.mipmap.sigbglogo_share1);
        map.put("SIG002", R.mipmap.sigbglogo_share2);
        map.put("SIG003", R.mipmap.sigbglogo_share3);
        map.put("swt001", R.mipmap.swtbglogo_share1);
        map.put("swt002", R.mipmap.swtbglogo_share2);
        map.put("swt003", R.mipmap.swtbglogo_share3);
        map.put("tdq", R.mipmap.mindor_share_tdq001);
        map.put("fiv", R.mipmap.mindor_share_fiv001);
        map.put("LUM001", R.mipmap.lum001_share_off);

        //        mDevices.add(new ShareBean(1, false, "智能插座"));
        //        mDevices.add(new ShareBean(2, true, "易燃气体插座"));
        //        mDevices.add(new ShareBean(0, false, "零火线单开开关"));

        mAdapter = new OnlineShareDeviceListAdapterShare(ShareDeviceActivity.this, mDevices, map);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        gv_devices.setLayoutManager(layoutManager);
        gv_devices.setHasFixedSize(true);
        gv_devices.setNestedScrollingEnabled(false);
        gv_devices.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        gv_devices.setAdapter(mAdapter);

        mAdapter.setOnViewClickListener(new BaseRecyclerShareViewAdapter.OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                Log.e("TAG", view + ":onViewClick:" + position);
                //选中的时候  应该改变device数据的状态并刷新  选中状态并刷新  先去把布局改一下
                if (mDevices.get(position).isAdapterChecked()) {
                    mDevices.get(position).setAdapterChecked(false);
                } else {
                    mDevices.get(position).setAdapterChecked(true);
                }
                Log.e("TAG", "if_data:" + "\t" + mDevices.get(position).isAdapterChecked());
                mAdapter.resetDevices(mDevices);
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 点击扫码分享  出现一个dialog  dialog里面出现一个二维码，点击二维码可以保存本地相册
                 生成的二维码内容暂时先默认一个json 然后做加密 这个弹框还能难弄的
                 明天还剩下首页的接口修改 也简单  就这样
                 */
                TestingDialogFragment testingDialogFragment = new TestingDialogFragment();
                testingDialogFragment.show(getFragmentManager(), "");
                testingDialogFragment.onSetClickDialogListener(new TestingDialogFragment.SetOnClickDialogListener() {

                    @Override
                    public void onClickDialogListener(int type, boolean boolClick, Bitmap content) {
                        Log.e("TAG", "onClickDialogListener： " + type + "\t" + boolClick + "\t" + content);
                        saveQRCode(ShareDeviceActivity.this, content);
                    }
                });
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // type  弹窗类型，xml页面  ，  chs_about_error_dialog
                AboutDialogErrorFragment aboutDialog = new AboutDialogErrorFragment();
                aboutDialog.setDialogContent(false, R.layout.fragment_item_input);
                aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
                aboutDialog.onSetClickDialogListener(new AboutDialogErrorFragment.SetOnClickDialogListener() {

                    @Override
                    public void onClickDialogListener(int type, boolean boolClick, String content) {
                        Log.e("TAG", "onClickDialogListener： " + type + "\t" + boolClick + "\t" + content);
                        /**
                         //这里应该做网络请求 直接使用go做网络请求
                         接下来  给我找到  go的网络请求在哪里  okgo
                         * */
                        getData(content);
                    }
                });
            }
        });

        commonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    //返回 2
                    finish();
                } else if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {
                    //右侧弹框  4
                    for (int i = 0; i < mDevices.size(); i++) {
                        mDevices.get(i).setAdapterChecked(true);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    private void getData(String edShareString) {
        AddShareBean mAddShareBean = new AddShareBean();
        mAddShareBean.setUserId("minApp113988");
        mAddShareBean.setCode("");
        mAddShareBean.setShare(edShareString);
        mAddShareBean.setDevices("zcz002103910,zcz001101027");
        Gson gson = new Gson();
        String jsonString = gson.toJson(mAddShareBean);
        OkGo.<String>post(url)
                .upJson(jsonString)
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //  UpdateBean mUpdateBean = JSONObject.parseObject(response.body(), UpdateBean.class);
                        Log.e("TAG", "onSuccess:" + response.body());
                        Message msg = new Message();
                        msg.what = 102;
                        msg.obj = response.body();
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e("TAG", "onError:" + response);
                    }
                });
    }


    TimerTask task1 = new TimerTask() {
        @Override
        public void run() {
            /***要执行的操作*/
            AboutDialogErrorFragment aboutDialog = new AboutDialogErrorFragment();
            aboutDialog.setDialogContent(false, R.layout.chs_about_dialog);
            aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
            aboutDialog.onSetClickDialogListener(new AboutDialogErrorFragment.SetOnClickDialogListener() {

                @Override
                public void onClickDialogListener(int type, boolean boolClick, String content) {
                    Log.e("TAG", "onClickDialogListener： " + type + "\t" + boolClick + "\t" + content);
                }
            });
        }
    };

    TimerTask task2 = new TimerTask() {
        @Override
        public void run() {
            /***要执行的操作*/
            AboutDialogErrorFragment aboutDialog = new AboutDialogErrorFragment();
            aboutDialog.setDialogContent(false, R.layout.chs_about_error_dialog);
            aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
            aboutDialog.onSetClickDialogListener(new AboutDialogErrorFragment.SetOnClickDialogListener() {

                @Override
                public void onClickDialogListener(int type, boolean boolClick, String content) {
                    Log.e("TAG", "onClickDialogListener： " + type + "\t" + boolClick + "\t" + content);
                }
            });
        }
    };


    private void saveQRCode2(Bitmap bitmap1) {
        // Drawable drawable = iv_code.getDrawable();
        String title = String.valueOf(new Date().getTime());
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap1, title, "img");
        //  sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
        //  saveImageToGallery(CreateCodeActivity.this, qrcode, "");
        //发送广播通知系统图库刷新数据
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        Toast.makeText(ShareDeviceActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 将Bitmap图片保存到本地相册
     */
    public static void saveQRCode(final Context context, final Bitmap bitmap2) {
        if (bitmap2 == null) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 其次把文件插入到系统图库
                try {
                    //                    MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "IMG"+ , null));
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap2,
                            Calendar.getInstance().getTime().toString(), "");
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片保存至相册", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final Exception e) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片保存失败"+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e("TAG", "图片保存异常：" + e);
                }
            }
        }).start();
    }


}