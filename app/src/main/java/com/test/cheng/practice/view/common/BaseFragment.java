package com.test.cheng.practice.view.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.test.cheng.practice.R;

/**
 * Created by gaokuncheng on 2016/12/9.
 */
public class BaseFragment extends Fragment{

    private ProgressDialog pDialog;
    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mActivity = activity;
        }
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
            pDialog = new ProgressDialog(mActivity);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
        }
        return pDialog;
    }

    protected Activity getMyActivity() {
        return mActivity;
    }

}
