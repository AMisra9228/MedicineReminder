package abhishek.com.medreminder;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.QUEUE_ADD;

public class Speech extends AppCompatActivity {
    TextToSpeech toSpeech;
    int result;


    void Speech(Context mContext, final String text){

        //Voice Initialization
        toSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = toSpeech.setLanguage(Locale.US);

                    if(result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{

                        //Voice enable
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                        {
                            Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //text = "1";
                            Log.d("Speech Test",text);
                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                            toSpeech.playSilentUtterance(2000, QUEUE_ADD, null);

                        }
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });


    }

}