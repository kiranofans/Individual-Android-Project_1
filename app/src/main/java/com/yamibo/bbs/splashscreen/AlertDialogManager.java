package com.yamibo.bbs.splashscreen;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.TaskStackBuilder;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

import Model.AlertDialogBase;
import Model.ApiResponses;
import Utils.Utility;

import static android.support.v4.app.NotificationCompat.DEFAULT_ALL;

public class AlertDialogManager extends AlertDialogBase{
    private static Context _context;
    public static AlertDialog.Builder alertDialog;
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

       ApiResponses apiResponses=new ApiResponses();

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setMessage(msg);
        //progressText.setText("" + msg);
        if( status==true){
            //Setting alert dialog icon
            /*alertDialog.setIcon((status) ? R.drawable.ic_dialog_ok :
                    R.drawable.ic_dialog_cancel);*/
            alertDialog.show();

        }else if(Utility.isApiResponseSuccess(apiResponses)){
           alertDialog.hide();
        }
    }

    public void showDialog(){
        alertDialog=new AlertDialog.Builder(_context);
        alertDialog.setCancelable(false);
        alertDialog.setMessage("加載中...");

        alertDialog.setView(R.id.progressBar_cyclic).show();
    }
    public void dismissDialog(){
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
    }

    public AlertDialogManager(Context context){
        this._context=context;
    }
    //Alert Dialog
    public void alertDialog() {
        alertDialog = new AlertDialog.Builder(_context);
        //Set title
        alertDialog.setTitle("Warning!");

        //Set dialogue Message
        alertDialog.setCancelable(false).setMessage("")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //continue with delete
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if this button is clicked, just close
                //the dialog
                dialog.cancel();
            }

            //AlertDialog dialog = dialogBuilder.create();

        }).setIcon(android.R.drawable.ic_dialog_alert);
        //Create alert dialog for countdown
        final AlertDialog alert2=alertDialog.create();
        alert2.show();
        CountDownTimer countdown=new CountDownTimer(300000,1000){
            @Override
            public void onTick(long l) {
                alert2.setMessage("Living organism inside your car!\nStarts Countdown"
                        +String.format("\n%d:%d mins",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit
                                .MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
            }

            @Override
            public void onFinish() {
                alert2.setMessage("Finished!");
            }

        }.start();
    }
    public void notification() {
        Context notifContext=null;
        String mainMsg = "Notification message from TransLink Vancouver...";

        //Create Notification Builder and setting properties
        Notification.Builder notifBuilder =
                new Notification.Builder(_context)
                        .setStyle(new Notification.BigTextStyle().bigText(mainMsg))
                        .setSmallIcon(R.mipmap.lily_flower)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setDefaults(DEFAULT_ALL)
                        .setContentTitle("Transit Push Notification Simulator")
                        .setContentText(mainMsg);

        //Attach Actions with action buttons
        Intent notifyIntent = new Intent(_context,MainNavTabActivity.class);

        //Answer intent
        Intent answerIntent=new Intent(_context,MainNavTabActivity.class);
        answerIntent.setAction("Confirm");

        //Answer pendingIntent for Confirm
        PendingIntent pdConfirm= PendingIntent
                .getActivity(_context,1,answerIntent,
                        0);
        answerIntent.setAction("Cancel");

        PendingIntent contentIntent = PendingIntent
                .getActivity(_context, 0, notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(_context);
        stackBuilder.addParentStack(MainNavTabActivity.class);

        notifBuilder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager)_context.
                getSystemService(Context.NOTIFICATION_SERVICE);

        //Send notification
        int currentNoticeId=+1;
        int noticeId=currentNoticeId;

        if(noticeId== Integer.MAX_VALUE-1){

            noticeId=0;
        }else {
            manager.notify(noticeId,notifBuilder.build());
        }

        //Sound, vibration, and LED light
        notifBuilder.setDefaults(Notification.DEFAULT_SOUND);
        notifBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        notifBuilder.setLights(Color.GREEN, 3000, 3000);
    }
}
