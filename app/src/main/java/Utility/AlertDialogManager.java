package Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yamibo.bbs.splashscreen.R;

import Model.AlertDialogBase;
import Model.ApiResponses;

public class AlertDialogManager extends AlertDialogBase{
    private static Context _context;
    public AlertDialog.Builder alertDialog;
    public static ProgressBar progressBar;
    private static AlertDialogManager alertDialogMgr;

    public static AlertDialogManager getInstance(){
        if(alertDialogMgr ==null){
            alertDialogMgr =new AlertDialogManager(_context);
        }
        return alertDialogMgr;
    }
    /** Function to display simple Alert Dialog
     * @param context - application context
     * @param msg - alert message * */
    public static void getAlertDialog(Context context, String msg, boolean status){
       AlertDialog alertDialog=new AlertDialog.Builder(context).create();
        //alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //alertDialog.setContentView(R.layout.fragment_loading_dialog);
       /* progressBar = (ProgressBar) alertDialog.findViewById(R.id.progressBar);
        TextView progressText = (TextView) alertDialog.findViewById(R.id.loadingTxt);*/

       ApiResponses apiResponses=new ApiResponses();

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        //progressText.setVisibility(View.VISIBLE);
        //progressBar.setVisibility(View.VISIBLE);
        //progressBar.setIndeterminate(true);

        alertDialog.setMessage(msg);
        //progressText.setText("" + msg);
        if( status==true){
            //Setting alert dialog icon
            /*alertDialog.setIcon((status) ? R.drawable.ic_dialog_ok :
                    R.drawable.ic_dialog_cancel);*/
            alertDialog.show();

        }else if(Utility.isApiResponseSuccess(apiResponses)){
           alertDialog.dismiss();
        }
    }

    public AlertDialogManager(Context context){
        this._context=context;
    }

}
