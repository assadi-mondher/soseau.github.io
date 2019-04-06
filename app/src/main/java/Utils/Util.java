package Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by jazz on 25/09/2016.
 */
public class Util {

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static void intentActivity(Activity activity, final Context ctx, Class<?> cls, Bundle extrasList )
    {
        Intent intent = new Intent();
        intent.setClass( ctx, cls );
        intent.putExtras(extrasList);
        activity.startActivity(intent);
    }

    public static void intentActivity(Activity activity, final Context ctx, Class<?> cls)
    {
        Intent intent = new Intent();
        intent.setClass( ctx, cls );
        activity.startActivity(intent);
    }

    public static void intentActivity(Activity activity, Intent intent)
    {

        activity.startActivity(intent);
    }


    public static boolean isconnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected() && netInfo.isAvailable()) {
            return true;

        } else {
            return false;
        }
    }


    public final static boolean isValidEmail(String target) {
        return EMAIL_ADDRESS_PATTERN.matcher(target.trim()).matches();
    }

/*
    public static void showWarningAlert(Context ctx,String confirmButtonText,String titleText, String contentText)
    {
        new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setConfirmText(confirmButtonText)
                .setContentText(contentText)
                .show();
    }


    public static void showSuccesAlert(Context ctx,String confirmButtonText,String titleText, String contentText)
    {
        new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(titleText)
                .setConfirmText(confirmButtonText)
                .setContentText(contentText)
                .show();
    }
*/

    public static String getText(EditText v)
    {
        return v.getText().toString();
    }

    public static String getDateOnly(long time) {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(time);
    }

    public static String getDateInverseFormat(long time) {
        return new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(time);
    }

    public static String getDateAndTime(long time) {
        SimpleDateFormat sample = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault());
        return sample.format(new Date(time));
    }
    public static String getDate(long time) {
        SimpleDateFormat sample = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sample.format(new Date(time));
    }

    public static String getTimeOnly(long time) {
        SimpleDateFormat sample = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sample.format(time);
    }

    public static String getContent(TextView mTextView)
    {
        return mTextView.getText().toString();
    }

    public static String getRadioCheckedLabel(RadioGroup radioButtonGroup)
    {
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioButtonGroup.findViewById(radioButtonID);
        String selectedtext = radioButton.getText().toString();
        return selectedtext;
    }

    public static String getRadioCheckedTag(RadioGroup radioButtonGroup)
    {
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioButtonGroup.findViewById(radioButtonID);
        String selectedtext = radioButton.getTag().toString();
        return selectedtext;
    }


}
