package com.yamibo.bbs.splashscreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param msg - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    public void showAlertDialog(Context context,String title,String msg,Boolean status){
        AlertDialog alertDialog=new AlertDialog.Builder(context).create();

        //Setting dialog title
        alertDialog.setTitle(title);

        //Setting dialog message
        alertDialog.setMessage(msg);

        if(status!=null){
            //Setting alert dialog icon
            alertDialog.setIcon((status) ? android.R.drawable.checkbox_on_background :
            android.R.drawable.ic_delete);
        }

        //Setting OK button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set ok button onclicks
            }
        });
        //Showing alert message
        alertDialog.show();
    }
}
