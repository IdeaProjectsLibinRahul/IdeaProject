package tech.libin.rahul.ideaproject.views.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 16/04/2017.
 */

public class MakeCall extends AppCompatActivity {

    Context mContext;
    private static final int REQUEST_CALL_PHONE = 110;
    private static String mPhoneNum;

    public MakeCall(Context context) {
        this.mContext = context;
    }

    public void setCallClick(View view) {
        try {

            //just open dialer, not need any permission
//            Linkify.addLinks((TextView) view, Linkify.PHONE_NUMBERS);
//            String phoneNum = ((TextView) view).getText().toString().trim();
//                        if (phoneNum.length() > 9) {
//                            ((TextView) view).setAutoLinkMask(Linkify.PHONE_NUMBERS);
//                            ((TextView) view).setLinksClickable(true);
//                            ((TextView) view).setMovementMethod(LinkMovementMethod.getInstance());
//                        }


//            //to directly call, to use jus un comment and comment above code
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (v instanceof TextView) {
//                        String phoneNum = ((TextView) v).getText().toString().trim();
//                        if (phoneNum.length() > 9) {
//                            makeCall(phoneNum);
//                        }
//                    }
//                }
//            });
        } catch (Exception ex) {

        }
    }

    public void makeCall(String phoneNum) {
        mPhoneNum = phoneNum;
        if (ActivityCompat.checkSelfPermission((Activity) mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            showAlert();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mPhoneNum));
        mContext.startActivity(intent);
    }

    public void showAlert() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert);
            builder.setMessage("Please grant permission to make this call").setNeutralButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    makeCall(mPhoneNum);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(mContext, "Call permission rejected", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

}
