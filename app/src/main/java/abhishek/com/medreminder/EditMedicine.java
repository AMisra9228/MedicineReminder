package abhishek.com.medreminder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditMedicine extends AppCompatActivity {
    private TextView edt_start_date,edt_end_date,edt_start_time,edt_repeat,result,edt_c_snooze,edt_from,edt_to,edt_status,edt_repetition;
    private EditText edt_med_name,edt_med_des;
    private Button btn_cancel,btn_reset,btn_done;
    String m_name,m_des,n_date,e_date,e_from,e_to,e_status,start_date;
    int e_repeat,e_snooze,med_id,repetition; //pos;
    MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(this);
    Speech sp = new Speech();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_medicine);

        //Initial speech
        sp.Speech(this,"");

        edt_med_name = (EditText) findViewById(R.id.edt_med_name);
        edt_med_des = (EditText) findViewById(R.id.edt_med_des);
        edt_start_date = (TextView) findViewById(R.id.edt_start_date);
        edt_end_date = (TextView) findViewById(R.id.edt_end_date);
        //edt_start_time = (TextView) findViewById(R.id.edt_start_time);
        edt_repetition = (TextView) findViewById(R.id.edt_repetition);
        edt_repeat = (TextView) findViewById(R.id.edt_repeat);
        edt_c_snooze = (TextView) findViewById(R.id.edt_c_snooze);
        edt_from = (TextView) findViewById(R.id.edt_from);
        edt_to = (TextView) findViewById(R.id.edt_to);
        edt_status = (TextView) findViewById(R.id.edt_status);
        result = (TextView) findViewById(R.id.details);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_done = (Button) findViewById(R.id.btn_done);

        med_id = getIntent().getExtras().getInt("m_id");
//        Toast.makeText(this,"Position of :"+ med_id,Toast.LENGTH_SHORT).show();
        med_id = getData(med_id);       //display data from medicine table using med_id

        m_name = edt_med_name.getText().toString();
        m_des = edt_med_des.getText().toString();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                m_name = edt_med_name.getText().toString();
//                show();
                Intent intent = new Intent(EditMedicine.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset fields to default
                edt_med_name.setText(m_name);
                edt_med_des.setText(m_des);
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateData(med_id);       //update medicine data
//                m_name = edt_med_name.getText().toString();
//                show();
                // display toast
                Intent intent = new Intent(EditMedicine.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });
    }

    public int getData(int pos){
        int m_id = pos;//myDb.getRow(pos,"Medicine_table");
        Cursor res = myDb.getRowData(m_id,"Medicine_table");
        if(res!=null && res.getCount()>0){
            while (res.moveToNext()){
//                Toast.makeText(this,"m_id :"+m_id+"Med_id :"+res.getString(0),Toast.LENGTH_LONG).show();
                edt_med_name.setText(res.getString(1));
                edt_med_des.setText(res.getString(2));
                edt_start_date.setText(res.getString(4));
                edt_end_date.setText(res.getString(5));
                edt_repetition.setText(res.getString(6));
                edt_repeat.setText(res.getString(7));
                edt_from.setText(res.getString(8));
                edt_to.setText(res.getString(9));
                edt_status.setText(res.getString(10));
                edt_c_snooze.setText(res.getString(11));
                return Integer.valueOf(res.getString(0));
            }
        }
        else {
            Toast.makeText(EditMedicine.this,"No data to retrieve",Toast.LENGTH_SHORT).show();
        }
        return 0;
    }

    public void UpdateData(int m_id){
        med_id = m_id;
        m_name = edt_med_name.getText().toString();
        m_des = edt_med_des.getText().toString();
        start_date = edt_start_date.getText().toString();
        n_date= edt_start_date.getText().toString();
        e_date = edt_end_date.getText().toString();
        e_from = edt_from.getText().toString();
        e_to = edt_to.getText().toString();
        e_status = edt_status.getText().toString();
        repetition = Integer.parseInt(edt_repetition.getText().toString());
        e_repeat = Integer.parseInt(edt_repeat.getText().toString());
        e_snooze = Integer.parseInt(edt_c_snooze.getText().toString());

        myDb.updateData(new MedicineUp(med_id,m_name, m_des, start_date,n_date, e_date, repetition,e_repeat, e_from, e_to, e_status,e_snooze));

        myDb.execMyQuery("update Report_table set med_name = '"+m_name+"', med_des = '"+m_des+"' where med_id = " + med_id);

        String text = "Details of" + m_name + "has been updated";
        sp.Speech(this,text);
        Toast.makeText(EditMedicine.this, "Medicine: " + m_name + " updated successfully ", Toast.LENGTH_SHORT).show();
    }


//    public void show(){
//        Cursor res = medicineDatabaseHelper.getMedData();
//        StringBuffer stringBuffer = new StringBuffer();
//        if(res!=null && res.getCount()>0){
//            while (res.moveToNext()){
//                stringBuffer.append("Med. Id: "+res.getString(0)+"\n");
//                stringBuffer.append("Med. Name: "+res.getString(1)+"\n");
//                stringBuffer.append("Med. Des: "+res.getString(2)+"\n");
//                stringBuffer.append("Start Date: "+res.getString(3)+"\n");
//                stringBuffer.append("End Date: "+res.getString(4)+"\n");
//                stringBuffer.append("Current Time: "+res.getString(5)+"\n");
//                stringBuffer.append("Occurrence: "+res.getString(6)+"\n");
//                stringBuffer.append("Status: "+res.getString(7)+"\n");
//                stringBuffer.append("\n");
//            }
//            result.setText(stringBuffer.toString());
//        }
//    }

    public boolean update() {
        //boolean isUpdate = medicineDatabaseHelper.updateData(new Medicine(m_name,m_des));

        return true;
    }

//    @Override
//    public void onBackPressed() {
//
//        super.onBackPressed();
//
//        Intent intent =new Intent(EditMedicine.this,MainActivity.class);
//        startActivity(intent);
//        finish();
//        return;
//    }
}
