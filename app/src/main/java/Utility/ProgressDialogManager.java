package Utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yamibo.bbs.splashscreen.R;

import java.util.logging.Handler;

import Model.ApiResponses;

import static Utility.AlertDialogManager.progressBar;

public class ProgressDialogManager{
    private static ProgressBar progressBar;
    private static Context context;
    public static Dialog dialog;
    private android.os.Handler handler=new android.os.Handler();
    public static ProgressDialogManager dialogMgr;
    private TextView progressTxt;
    private AlertDialog.Builder builder;
    private ProgressDialogManager(Context context){
        this.context=context;
    }
    public static ProgressDialogManager getInstance(){
        if(dialogMgr==null){
            dialogMgr=new ProgressDialogManager(context);
        }
        return dialogMgr;
    }
    public void dismissDialog(ApiResponses apiResponses){
        if(isApiResponseSuccess(apiResponses)){
            dialog.dismiss();
        }
    }
    public static boolean isApiResponseSuccess(ApiResponses responseMod) {
        return responseMod != null && (responseMod.getRC() ==
                ApiConstants.API_RESPONSE_CODE_SUCCESS);
    }
    public static Dialog getProgressDialog(Context context,String msg) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_loading_dialog);

        progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
        TextView progressText = (TextView) dialog.findViewById(R.id.loadingTxt);
        progressText.setText("" + msg);
        progressText.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        return dialog;
    }
}
