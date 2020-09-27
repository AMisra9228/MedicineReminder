package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {
    SharedPreferences user_sharedpreferences;
    private String myprefer = "profile";
    public String u_nam = "Your Name",u_gender = "genderkey",u_stat = "maritalkey",u_title = "pre",nam,gend="",title,stat="";
    private EditText user_name;
    private Spinner m_stat,gender;
    private RadioGroup prefix;
    private RadioButton mr;
    private RadioButton mrs;
    private RadioButton ms;
    private RadioButton master;
    private RadioButton pre_title;
    private Button btn_submit,btn_get,btn_cancel;
    private TextView show,prefix_title;
    private Integer t,p_title_id = 0;
//    public void checkTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user_name = (EditText) findViewById(R.id.user_name);
        m_stat = (Spinner) findViewById(R.id.m_stat);
        gender = (Spinner) findViewById(R.id.gender);
//        prefix = (RadioGroup) findViewById(R.id.prefix);
//        mr = (RadioButton) findViewById(R.id.mr);
//        master = (RadioButton) findViewById(R.id.master);
//        mrs = (RadioButton) findViewById(R.id.mrs);
//        ms = (RadioButton) findViewById(R.id.ms);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
//        btn_get = (Button) findViewById(R.id.btn_get);
//        show = (TextView) findViewById(R.id.show);
        prefix_title = (TextView) findViewById(R.id.prefix_title);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                gend = gender.getSelectedItem().toString();
//                Toast.makeText(getBaseContext(),"Gender : " + gend, Toast.LENGTH_SHORT).show();
                checkTitle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        m_stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stat = m_stat.getSelectedItem().toString();
//                Toast.makeText(getBaseContext(),"Status : " + stat, Toast.LENGTH_SHORT).show();
                checkTitle();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nam = user_name.getText().toString();

                //Name title prefix
//                if(gend.equals("Male") && stat.equals("Unmarried")){
////                    prefix_title.setText("Mr");
////                }else if(gend.equals("Male") && stat.equals("Married")){
////                    prefix_title.setText("Mr");
////                }else if(gend.equals("Male") && stat.equals("Disclose")){
////                    prefix_title.setText("Mx");
////                } else if(gend.equals("Female") && stat.equals("Unmarried")){
////                    prefix_title.setText("Mrs");
////                }else if(gend.equals("Female") && stat.equals("Married")){
////                    prefix_title.setText("Ms");
////                }else if(gend.equals("Female") && stat.equals("Disclose")){
////                    prefix_title.setText("Mx");
////                } else if(gend.equals("Transgender") && stat.equals("Married")){
////                    prefix_title.setText("Mx");
////                } else if(gend.equals("Transgender") && stat.equals("Unmarried")){
////                    prefix_title.setText("Mx");
////                }else if(gend.equals("Transgender") && stat.equals("Disclose")){
////                    prefix_title.setText("Mx");
////                }

//                Log.d("sharedpreference","submit_test_1.0");

//                // get selected radio button from radioGroup
//                int selectedId = prefix.getCheckedRadioButtonId();
//
//                // find the radiobutton by returned id
//                pre_title = (RadioButton) findViewById(selectedId);
//                title = pre_title.getText().toString();


                //Save into sharedpreferences in myprefer
                user_sharedpreferences = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = user_sharedpreferences.edit();
                editor.putString(u_nam,nam);
                editor.putString(u_gender,gend);
                editor.putString(u_stat,stat);
                editor.putString(u_title,prefix_title.toString());
                editor.putString(u_title,p_title_id.toString());
                editor.commit();
//                Log.d("TITLE_TEST",String.valueOf(title));

//                Toast.makeText(UserProfileActivity.this, pre_title.getText(), Toast.LENGTH_SHORT).show();
                Toast toast= Toast.makeText(UserProfileActivity.this, "Your Data Submitted", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
//                startActivity(intent);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });

//        btn_get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user_sharedpreferences = getSharedPreferences(myprefer,Context.MODE_PRIVATE);
//
//                if (user_sharedpreferences.contains(u_nam)) {
//                    show.setText(user_sharedpreferences.getString(u_nam,""));
//                }
//                if (user_sharedpreferences.contains(u_gender)) {
//                    show.setText(user_sharedpreferences.getString(u_gender, ""));
//                }
//                if (user_sharedpreferences.contains(u_stat)) {
//                    show.setText(user_sharedpreferences.getString(u_stat, ""));
//                }
//                if (user_sharedpreferences.contains(u_title)) {
//                    show.setText(user_sharedpreferences.getBoolean(u_title, "pre"));
//                }
//            }
//        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "'User Profile' action cancelled by user", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });

    }

    public void checkTitle(){
        //Name title
        if(gend.equals("Male") && stat.equals("Unmarried")){
            prefix_title.setText("Mr");
            p_title_id = 1;
        } else if(gend.equals("Male") && stat.equals("Married")){
            prefix_title.setText("Mr");
            p_title_id = 2;
        } else if(gend.equals("Male") && stat.equals("Do not Disclose")){
            prefix_title.setText("Mr");
            p_title_id = 5;
        } else if(gend.equals("Female") && stat.equals("Unmarried")){
            prefix_title.setText("Ms");
            p_title_id = 3;
        } else if(gend.equals("Female") && stat.equals("Married")){
            prefix_title.setText("Mrs");
            p_title_id = 4;
        } else if(gend.equals("Female") && stat.equals("Do not Disclose")){
            prefix_title.setText("Ms");
            p_title_id = 6;
        } else if(gend.equals("Transgender") && stat.equals("Married")){
            prefix_title.setText("Mx");
            p_title_id = 10;
        } else if(gend.equals("Transgender") && stat.equals("Unmarried")){
            prefix_title.setText("Mx");
            p_title_id = 11;
        } else if(gend.equals("Transgender") && stat.equals("Do not Disclose")){
            prefix_title.setText("Mx");
            p_title_id = 12;
        } else if(gend.equals("Do not Disclose") && stat.equals("Married")){
            prefix_title.setText("Mx");
            p_title_id = 7;
        } else if(gend.equals("Do not Disclose") && stat.equals("Unmarried")){
            prefix_title.setText("Mx");
            p_title_id = 8;
        } else if(gend.equals("Do not Disclose") && stat.equals("Do not Disclose")){
            prefix_title.setText("Mx");
            p_title_id = 9;
        }
    }

    //device onBack pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
//        startActivity(intent);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        return;
    }
}
