package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class userNewPIN extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedpreferences;
    private String mypreference = "login_pin";
    private static final String TAG = "userNewPIN";
    ImageView i1, i2, i3, i4;
    int pinLength = 0, prev=0;
    private String m_pin = "", n_pin ="";
    TextView tv;
    String text;
    boolean flag = false;
    Vibrator vibrator;
    int delay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.findViewById(R.id.btnNumpad1).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad2).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad3).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad4).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad5).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad6).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad7).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad8).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad9).setOnClickListener(this);
        this.findViewById(R.id.btnNumpad0).setOnClickListener(this);
        this.findViewById(R.id.btnNumpadLeft).setOnClickListener(this);
        this.findViewById(R.id.btnNumpadRight).setOnClickListener(this);

        tv = (TextView) findViewById(R.id.my_text_view);
        text = "Set new 4-digit-PIN";
        tv.setText(""  + text);
        i1 = (ImageView) findViewById(R.id.imageview_circle1);
        i2 = (ImageView) findViewById(R.id.imageview_circle2);
        i3 = (ImageView) findViewById(R.id.imageview_circle3);
        i4 = (ImageView) findViewById(R.id.imageview_circle4);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        // vibratation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(50);
        }

        //tv.setText(""  + text);
        //tv.setText(""  + text + "." + m_pin + "*" + n_pin + ".");
        if (pinLength < 4) {
            switch (v.getId()) {
                case R.id.btnNumpad1:
                    m_pin = m_pin + "1";
//                    Toast.makeText(this, "Number Key 1 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad2:
                    m_pin = m_pin + "2";
//                    Toast.makeText(this, "Number Key 2 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad3:
                    m_pin = m_pin + "3";
//                    Toast.makeText(this, "Number Key 3 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad4:
                    m_pin = m_pin + "4";
//                    Toast.makeText(this, "Number Key 4 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad5:
                    m_pin = m_pin + "5";
//                    Toast.makeText(this, "Number Key 5 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad6:
                    m_pin = m_pin + "6";
//                    Toast.makeText(this, "Number Key 6 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad7:
                    m_pin = m_pin + "7";
//                    Toast.makeText(this, "Number Key 7 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad8:
                    m_pin = m_pin + "8";
//                    Toast.makeText(this, "Number Key 8 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad9:
                    m_pin = m_pin + "9";
//                    Toast.makeText(this, "Number Key 9 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpad0:
                    m_pin = m_pin + "0";
//                    Toast.makeText(this, "Number Key 0 Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpadLeft:
                    m_pin = "";
//                    Toast.makeText(this, "Clear Key Pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnNumpadRight:
                    if (m_pin != "") {
                        if(m_pin.length() == 1) {
                            m_pin = "";
                        } else {
                            m_pin = m_pin.substring(0, m_pin.length() - 1);
                        }
                    }
//                    Toast.makeText(this, "Backspace Key Pressed", Toast.LENGTH_SHORT).show();
            }
            pinLength = m_pin.length();
        }
        //Toast.makeText(this, "Entered wPIN : " + m_pin, Toast.LENGTH_SHORT).show();

        //pinLength = m_pin.length();
        if (pinLength != prev) {
            if (pinLength < 4) {
                if (pinLength < prev) {
                    while (pinLength < prev) {
                        if (prev == 1) {
                            i1.setImageResource(R.drawable.circle);
                            Log.d(TAG, "onKey: cleared key1");
                        } else if (prev == 2) {
                            i2.setImageResource(R.drawable.circle);
                            Log.d(TAG, "onKey: cleared key2");
                        } else if (prev == 3) {
                            i3.setImageResource(R.drawable.circle);
                            Log.d(TAG, "onKey: cleared key3");
                        }
                        prev = prev - 1;
                    }
                } else {
                    if (pinLength == 1) {
                        Log.d(TAG, "onKey: pressed pin_key1");
                        i1.setImageResource(R.drawable.circle2);
                    } else if (pinLength == 2) {
                        Log.d(TAG, "onKey: pressed pin_key2");
                        i2.setImageResource(R.drawable.circle2);
                    } else if (pinLength == 3) {
                        Log.d(TAG, "onKey: pressed pin_key3");
                        i3.setImageResource(R.drawable.circle2);
                    }
                }
            } else {
                //m_pin = m_pin.substring(0,4);
                m_pin = m_pin.substring(0,4);
                Log.d(TAG, "onKey: pressed pin_key4");
                i4.setImageResource(R.drawable.circle2);
                //Toast.makeText(this, "Entered PIN : " + m_pin, Toast.LENGTH_SHORT).show();

                if(flag && (m_pin.length() == 4)) {
                    if (m_pin.equals(n_pin)) {
                        //Toast.makeText(this, "PIN Setup Successful", Toast.LENGTH_SHORT).show();
                        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(mypreference, m_pin);
                        editor.commit();
                        m_pin = "";
                        n_pin = "";
                        pinLength = 0;
                        prev = 0;
                        Toast.makeText(this, "PIN Changed Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(userNewPIN.this, MainActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent1);
                        startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        finish();
                    } else {
                        Toast.makeText(this, "PIN doesn't match. Try Again...", Toast.LENGTH_SHORT).show();
                        prev = m_pin.length();
                        pinLength = 0;
                        // set delay
                        text = "Set New 4-digit-PIN";
                        tv.setText(""  + text);
                        while (pinLength < prev) {
                            if (prev == 1) {
                                i1.setImageResource(R.drawable.circle);
                                Log.d(TAG, "onKey: cleared key1");
                            } else if (prev == 2) {
                                i2.setImageResource(R.drawable.circle);
                                Log.d(TAG, "onKey: cleared key2");
                            } else if (prev == 3) {
                                i3.setImageResource(R.drawable.circle);
                                Log.d(TAG, "onKey: cleared key3");
                            } else if (prev == 4) {
                                i4.setImageResource(R.drawable.circle);
                                Log.d(TAG, "onKey: cleared key3");
                            }
                            prev = prev - 1;
                        }
                        flag = false;
                        m_pin = "";
                        n_pin = "";
                        pinLength = 0;
                        prev = 0;
                    }
                }
                if(!flag && (m_pin.length() == 4)) {
                    n_pin = m_pin;
                    text = "Confirm 4-digit PIN";
                    tv.setText(""  + text);
                    n_pin = m_pin;
                    m_pin = "";
                    pinLength = 0;
                    prev = 0;
                    // set delay
                    i1.setImageResource(R.drawable.circle);
                    i2.setImageResource(R.drawable.circle);
                    i3.setImageResource(R.drawable.circle);
                    i4.setImageResource(R.drawable.circle);
                    flag = true;
                }
                //finish();
            }
            prev = pinLength;
        }
        //Toast.makeText(this, "Backspace Key Pressed-"+m_pin+"-"+pinLength+"-"+prev, Toast.LENGTH_SHORT).show();
        prev = pinLength;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent intent =new Intent(userNewPIN.this,PasswordSettingsActivity.class);
//        startActivity(intent);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }
}
