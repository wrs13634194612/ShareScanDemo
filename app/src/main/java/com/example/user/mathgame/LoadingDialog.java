package com.example.user.mathgame;


        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.DialogFragment;
        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.view.animation.LinearInterpolator;
        import android.widget.ImageView;
        import android.widget.TextView;


/**
 * 默认加载中对话框
 *
 * @author caibou
 */
public class LoadingDialog extends DialogFragment {

    private TextView tvMessage;
    private ImageView ivLoading;

    private String message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_loading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvMessage = view.findViewById(R.id.tv_message_dialog_loading);
        if (!TextUtils.isEmpty(message)) tvMessage.setText(message);
        ivLoading = view.findViewById(R.id.iv_loading_dialog_loading);
        setupLoadingIcon(view.getContext());
    }

    private void setupLoadingIcon(Context context) {
        Animation animRotate = AnimationUtils.loadAnimation(context, R.anim.rotate_loading_icon);
        LinearInterpolator interpolator = new LinearInterpolator();
        animRotate.setInterpolator(interpolator);
        ivLoading.startAnimation(animRotate);
    }

    public void setMessage(String message) {
        if (tvMessage != null ) tvMessage.setText(message);
        else this.message = message;
    }

    public void setMessage(int resId) {
        setMessage(getString(resId));
    }

}
