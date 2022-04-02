package com.example.user.mathgame;


import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AboutDialogErrorFragment extends DialogFragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Button AboutSure;

    private SetOnClickDialogListener mSetOnClickListener;


    private boolean type;  //true表示成功失败风格   false表示输入账号风格

    private int resource; //布局xml


    public void onSetClickDialogListener(SetOnClickDialogListener listener) {
        this.mSetOnClickListener = listener;
    }

    public void setDialogContent(boolean type, int resource) {
        this.type = type;
        this.resource = resource;
    }

    public interface SetOnClickDialogListener {
        void onClickDialogListener(int type, boolean boolClick,String content);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(resource, container, false);
        initView(view);
        return view;
    }

    private void initView(View V_AboutDialog) {
        AboutSure = V_AboutDialog.findViewById(R.id.btn_done_tip_view_btn);
        final EditText et_dev_name = V_AboutDialog.findViewById(R.id.et_dev_name);

        AboutSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
                if (mSetOnClickListener != null) {
                    String etString = et_dev_name.getText().toString();
                    mSetOnClickListener.onClickDialogListener(0, true,etString);
                }
            }
        });

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

}
