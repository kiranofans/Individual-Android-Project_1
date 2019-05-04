package Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yamibo.bbs.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

import Model.ApiResponses;
import Model.TimeZone;

import static com.yamibo.bbs.splashscreen.AlertDialogManager.progressBar;
import static Utils.ApiConstants.API_RESPONSE_CODE_UNKNOWN_ERROR;

public class Utility {
    private static final String LOG_TAG = Utility.class.getSimpleName();

    public static boolean isApiResponseSuccess(ApiResponses responseMod) {
        return responseMod != null && (responseMod.getRC() == ApiConstants.API_RESPONSE_CODE_SUCCESS);
    }

    public static boolean isTZResponseSuccess(TimeZone responseMod) {
        return responseMod != null && (responseMod.getStatus().equals("OK"));
    }

    public static boolean isConnectToInternet(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static int apiErrorHandler(ApiResponses responseMod) {
        if (responseMod == null) {
            return API_RESPONSE_CODE_UNKNOWN_ERROR;
        } else {
            return responseMod.getRC();
        }
    }

    public static boolean isEmptyList(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static <T> List<T> safeGetArrayList(List<T> list) {
        if (list != null) {
            return new ArrayList<>(list);
        } else {
            return new ArrayList<>();
        }
    }

    public static void setAppPreferenceItem(SharedPreferences pref, String file, String key, Object value) {
        SharedPreferences.Editor editor = pref.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        editor.apply();
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

    public static void showErrorMessageToast(Context context, @StringRes int id) {
        Toast.makeText(context,"", Toast.LENGTH_SHORT).show();
    }

    public static void showLongMessageToast(Context context, @StringRes int id) {
        Toast.makeText(context,"", Toast.LENGTH_LONG).show();
    }

    public static void showErrorMessageToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongMessageToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

  /*  public static void showLogOutDialog(final Context context) {
        new Dialog(context).Builder(context)
                .content("")
                .positiveText("OK")
                .onPositive(new Dialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        UserInfoManager.getInstance().clearKeyPair();
                        Intent intent = new Intent(context, Activity_Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                })
                .show();
    }*/
}
