package com.test.cheng.practice.view.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.test.cheng.practice.R;

/**
 * Created by kexiaoderenren on 2016/12/8.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog pDialog;


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
