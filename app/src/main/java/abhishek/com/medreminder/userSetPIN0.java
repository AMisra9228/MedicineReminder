package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static java.lang.System.exit;


public class userSetPIN0 extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedpreferences;
    private String mypreference = "login_pin";
    private String p_enabled = "";
    private static final String TAG = "userSetPIN";
    public String Name = "Your Name";
    ImageView i1, i2, i3, i4;
    int pinLength = 0, prev=0;
    private String m_pin = "", n_pin ="", pass;
    TextView tv;
    String text;
    boolean flag = false;
    Vibrator vibrator;
    boolean doubleBackToExitPressedOnce = false;
    //    TextToSpeech toSpeech;
//    int result;
    Speech sp = new Speech();

    private String login_pin1, user_name1, sleep_from1, sleep_to1, sleep_fto1, m_report1;

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
        text = "Enter a 4-digit-PIN";
        tv.setText(""  + text);
        i1 = (ImageView) findViewById(R.id.imageview_circle1);
        i2 = (ImageView) findViewById(R.id.imageview_circle2);
        i3 = (ImageView) findViewById(R.id.imageview_circle3);
        i4 = (ImageView) findViewById(R.id.imageview_circle4);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        Initial speech
        sp.Speech(this,"");


//        toSpeech=new TextToSpeech(userSetPIN.this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if(status==TextToSpeech.SUCCESS)
//                {
//                    result=toSpeech.setLanguage(Locale.UK);
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
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

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "1";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"1");
                    break;
                case R.id.btnNumpad2:
                    m_pin = m_pin + "2";
//                    Toast.makeText(this, "Number Key 2 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "2";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"2");
                    break;
                case R.id.btnNumpad3:
                    m_pin = m_pin + "3";
//                    Toast.makeText(this, "Number Key 3 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "3";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"3");
                    break;
                case R.id.btnNumpad4:
                    m_pin = m_pin + "4";
//                    Toast.makeText(this, "Number Key 4 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "4";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"4");
                    break;
                case R.id.btnNumpad5:
                    m_pin = m_pin + "5";
//                    Toast.makeText(this, "Number Key 5 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "5";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"5");
                    break;
                case R.id.btnNumpad6:
                    m_pin = m_pin + "6";
//                    Toast.makeText(this, "Number Key 6 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "6";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"6");
                    break;
                case R.id.btnNumpad7:
                    m_pin = m_pin + "7";
//                    Toast.makeText(this, "Number Key 7 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "7";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"7");
                    break;
                case R.id.btnNumpad8:
                    m_pin = m_pin + "8";
//                    Toast.makeText(this, "Number Key 8 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "8";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"8");
                    break;
                case R.id.btnNumpad9:
                    m_pin = m_pin + "9";
//                    Toast.makeText(this, "Number Key 9 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "9";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"9");
                    break;
                case R.id.btnNumpad0:
                    m_pin = m_pin + "0";
//                    Toast.makeText(this, "Number Key 0 Pressed", Toast.LENGTH_SHORT).show();

                    //Voice enable
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        text = "0";
//                        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                    }
                    sp.Speech(userSetPIN0.this,"0");
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
            //Toast.makeText(this, m_pin, Toast.LENGTH_SHORT).show();
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
                Log.d(TAG, "onKey: pressed pin_key4");
                i4.setImageResource(R.drawable.circle2);
                //Delay
                //Toast.makeText(this, "Entered PIN : " + m_pin, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Re-enter PIN to enable app...", Toast.LENGTH_SHORT).show();

                //Voice enable
//                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                {
//                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    text = "Re-enter PIN to enable app";
//                    toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                }
                sp.Speech(userSetPIN0.this,"Re-enter PIN to enable app");

                if(flag && (m_pin.length() == 4)) {
                    if (m_pin.equals(n_pin)) {
                        //Toast.makeText(this, "PIN Setup Successful", Toast.LENGTH_SHORT).show();
                        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                        user_name1 = sharedpreferences.getString("user_name", null);
//                        login_pin1 = sharedpreferences.getString("login_pin", null);
                        sleep_from1 = sharedpreferences.getString("sleep_from", null);
                        sleep_to1 = sharedpreferences.getString("sleep_to", null);
                        sleep_fto1 = sharedpreferences.getString("sleep_fto", null);
                        m_report1 = sharedpreferences.getString("m_report", null);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("user_name", user_name1);
                        editor.putString(mypreference, m_pin);
                        editor.putString("sleep_from", sleep_from1);
                        editor.putString("sleep_to", sleep_to1);
                        editor.putString("sleep_fto", sleep_fto1);
                        editor.putString("m_report", m_report1);
                        editor.putString("p_enabled", "1");
                        editor.putString("p_enable", "1");
                        editor.commit();
                        m_pin = "";
                        n_pin = "";
                        pinLength = 0;
                        prev = 0;
                        Toast.makeText(this, "PIN Enabled Successfully", Toast.LENGTH_SHORT).show();

                        //Enable voice
//                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                        {
//                            Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            text = "PIN Enabled Successfully";
//                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                        }
                        sp.Speech(userSetPIN0.this,"PIN Enabled Successfully");

//                        Intent intent1 = new Intent(userSetPIN.this, MainActivity.class);
//                        startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        //finish();

                        //Delay for few seconds for transition
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(userSetPIN0.this,MainActivity.class);
//                                startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                finish();
                            }
                        }, 1000);

                    } else {
                        Toast.makeText(this, "PIN doesn't match. Try Again...", Toast.LENGTH_SHORT).show();

                        //Voice enable
//                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                        {
//                            Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            text = "PIN doesn't match. Try Again";
//                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                        }
                        sp.Speech(userSetPIN0.this,"PIN doesn't match. Try Again");

                        prev = m_pin.length();
                        pinLength = 0;
                        // set delay
                        text = "Enter a 4-digit-PIN";
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
                    text = "Re-enter 4-digit PIN";
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
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

        }
    }

}
