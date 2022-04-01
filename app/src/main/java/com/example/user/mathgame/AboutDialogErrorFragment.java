package com.example.user.mathgame;





        import android.app.DialogFragment;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;


public class AboutDialogErrorFragment extends DialogFragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Button AboutSure;

    private SetOnClickDialogListener mSetOnClickListener;

    public void onSetClickDialogListener(SetOnClickDialogListener listener) {
        this.mSetOnClickListener = listener;
    }

    public interface SetOnClickDialogListener {
        void onClickDoalogListener(int type, boolean boolClick);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chs_about_error_dialog, container, false);
        initView(view);
        return view;
    }


    private void initView(View V_AboutDialog) {
        AboutSure = (Button) V_AboutDialog.findViewById(R.id.btn_done_tip_view_btn);

        AboutSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
                if (mSetOnClickListener != null) {
                    mSetOnClickListener.onClickDoalogListener(0, true);
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
