package com.test.cheng.practice.view.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.test.cheng.practice.R;

/**
 * Created by kexiaoderenren on 2016/12/8.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    static {
        /**
         * MODE_NIGHT_NO. 使用亮色（light）主题
           MODE_NIGHT_YES. 使用暗色（dark）主题
           MODE_NIGHT_AUTO. 根据当前时间自动切换 亮色（light）/暗色（dark）主题
           MODE_NIGHT_FOLLOW_SYSTEM(默认选项). 设置为跟随系统，通常为 MODE_NIGHT_NO
         */
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void showHoldLoading() {
        showHoldLoading(getString(R.string.just_working));
    }

    public void showHoldLoading(String message) {
        getDialog();
        pDialog.setMessage(message);
        pDialog.show();
    }

    public void hideHoldLoading() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog.setMessage("");
        }
    }

    public ProgressDialog getDialog() {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
        }
        return pDialog;
    }

}
