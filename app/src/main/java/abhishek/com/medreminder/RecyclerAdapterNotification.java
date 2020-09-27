package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapterNotification extends RecyclerView.Adapter<RecyclerAdapterNotification.ViewHolder> {

    private ArrayList<Medicine> medicines;
    private Context mContext;
    //    MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(mContext);
    public int med_id, e_repeat, e_snooze, repetition;
    public String m_name, m_des, n_date, e_date, e_from, e_to, e_status,start_date;
    int snooze_time = 10;

    int m_id;

    Speech sp = new Speech();

    //public Button btnSnooze,btnSkip,btnTaken;

    public RecyclerAdapterNotification(ArrayList<Medicine> medicinesList,Context mContext) {
        Log.d("Notification Inbox ::","2");
        this.medicines = medicinesList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerAdapterNotification.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);

        RecyclerAdapterNotification.ViewHolder viewHolder = new RecyclerAdapterNotification.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterNotification.ViewHolder holder, final int position) {
        holder.txtName.setText(medicines.get(position).getMedicineName());
        holder.txtDes.setText(medicines.get(position).getMedicineDes());
        holder.txtN_Date.setText(medicines.get(position).getStartDate());
        Log.d("Notification Inbox ::","4");
        //Initial speech
        sp.Speech(mContext,"");


        holder.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                mediaPlayer.stop();
                // call class.method
                m_id = medicines.get(position).getId();
                UpdateRecord(m_id, "skipped and reminder set to", -1);
                //MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(mContext);
                medicines.remove(position);
                notifyDataSetChanged();
                Log.d("Notification Inbox ::","5");
            }
        });

        holder.btnTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call class.method
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                mediaPlayer.stop();
                m_id = medicines.get(position).getId();
                Log.d("Notification Inbox ::"," 6.1 "+ m_id);
                UpdateRecord(m_id, "taken and reminder set to", 0);
                medicines.remove(position);
                notifyDataSetChanged();
                Log.d("Notification Inbox ::","6");
            }
        });

        holder.btnSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call class.method
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                mediaPlayer.stop();
                m_id = medicines.get(position).getId();
                UpdateRecord(m_id, "snoozed and reminder set to", snooze_time);
//                medicines.remove(position);
//                notifyDataSetChanged();
                Log.d("Notification Inbox ::","7");
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public TextView txtName,txtDes,txtN_Date;
        public Button btnSnooze,btnTaken,btnSkip;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName_Card);
            txtDes = itemView.findViewById(R.id.txtMedDes);
            txtN_Date = itemView.findViewById(R.id.txtMedTime);

            btnSkip = itemView.findViewById(R.id.btnSkip);

            btnSnooze = (Button) itemView.findViewById(R.id.btnSnooze);
            btnTaken = (Button) itemView.findViewById(R.id.btnTaken);
            Log.d("Notification Inbox ::","3");

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    Snackbar.make(v, "clicked on: " + position, Snackbar.LENGTH_SHORT)
//                            .setAction("Action", null)
//                            .show();
//                }
//            });
        }
    }



//    public void UpdateData(int position, String stringMessage, int snooze_time) {
//        int m_id = myDb.getRow(position, "Medicine_table");
//        Cursor res = myDb.getRowData(m_id, "Medicine_table");
//        if (res != null && res.getCount() > 0) {
//            while (res.moveToNext()) {
//                if (med_id == m_id) {
//                    ParseString parseString = new ParseString();
//
//                    med_id = m_id;
//                    m_name = res.getString(1);
//                    m_des = res.getString(2);
//                    n_date = res.getString(3);
//                    e_date = res.getString(4);
//                    e_from = res.getString(6);
//                    e_to = res.getString(7);
//                    e_status = res.getString(8);
//                    e_repeat = Integer.parseInt(res.getString(5));
//                    e_snooze = Integer.parseInt(res.getString(9));
//
//                    if(snooze_time < 0) {
//                        n_date = parseString.addDate(n_date, (long) e_repeat);
//                        e_snooze = 0;
//                    }   // skip
//                    else if(snooze_time == 0) {
//                        String c_date = parseString.stringCurrentDate();
//                        n_date = parseString.addDate(c_date, (long) e_repeat);
//                        e_snooze = 0;
//                    }   // taken
//                    else if(snooze_time > 0) {
//                        String c_date = parseString.stringCurrentDate();
//                        n_date = parseString.addDate(c_date, (long) snooze_time);
//                        e_snooze = 2;
//                    }    // snooze
//
//                    boolean update = myDb.updateData(new MedicineUp(med_id, m_name, m_des, n_date, e_date, e_repeat, e_from, e_to, e_status, e_snooze));
//                    if(update == true && snooze_time == 0)
//                        myDb.execMyQuery("insert into Report_table med_id = '"+med_id+" med_name = '"+m_name+"', med_des = '" +m_des +"', next_date = '"
//                                +n_date+"', end_date = '"+e_date+"', occurrence = "+e_repeat+", sleep_from = '"+e_from+"', sleep_to = '"
//                                +e_to+"', med_status = '"+e_status+"', c_snooze = "+e_snooze+ " where med_id = "+med_id);
//                    else
//                        Toast.makeText(mContext, "Internal Error: Please Try Again Later", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "Medicine: " + m_name + stringMessage + " " + n_date, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

    public void UpdateRecord(int m_id1, String stringMessage, int snooze_time) {

        MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(mContext);
        Log.d("Notification Inbox ::","Update record");
        Cursor res = myDb.getRowData0(m_id1, "Medicine_table");
        Log.d("Notification Inbox ::","Passed Cursor");
        if (res != null && res.getCount() > 0) {
            Log.d("Notification Inbox ::","Checking");
            while (res.moveToNext()) {
                med_id = res.getInt(0);
                Log.d("Notification Inbox ::","While"+res.getString(0));
                if (med_id == m_id1) {
                    ParseString parseString = new ParseString();

                    med_id = m_id1;
                    Log.d("Notification Inbox ::"," 6.2 "+ med_id);
                    m_name = res.getString(1);
                    m_des = res.getString(2);
                    start_date = res.getString(3);
                    n_date = res.getString(4);
                    e_date = res.getString(5);
                    e_from = res.getString(8);
                    e_to = res.getString(9);
                    e_status = res.getString(10);
                    repetition = Integer.parseInt(res.getString(6));

                    long handle_occurance = 0,occurrence;
                    occurrence = Integer.parseInt(res.getString(7));
                    if(repetition == 1){
                        handle_occurance = 60 * occurrence;
                    }
                    else if(repetition == 2) {
                        if (occurrence == 1)
                            handle_occurance = 60 * 24;
                        else
                            handle_occurance = 60 * (15 / (occurrence - 1));
                    }

                    e_repeat = (int) occurrence;
                    e_snooze = Integer.parseInt(res.getString(11));

                    if(snooze_time < 0) {
                        n_date = parseString.addDate(n_date, handle_occurance);
                        e_snooze = 0;
                    }   // skip
                    else if(snooze_time == 0) {
                        String c_date = parseString.stringCurrentDate();
                        n_date = parseString.addDate(c_date, handle_occurance);
                        e_snooze = 0;
                    }   // taken
                    else if(snooze_time > 0) {
                        String c_date = parseString.stringCurrentDate();
                        n_date = parseString.addDate(c_date, (long) snooze_time);
                        e_snooze = 2;
                    }    // snooze

                    boolean update = myDb.updateData(new MedicineUp(med_id, m_name, m_des, start_date,n_date, e_date, repetition,e_repeat, e_from, e_to, e_status, e_snooze));
                    if(update == true && snooze_time == 0) {
                        myDb.execMyQuery("insert into 'Report_table' (med_id, med_name, med_des, start_date, next_date, end_date, repetition, occurrence, sleep_from, sleep_to, med_status, c_snooze) values ("
                                + med_id + ", '" + m_name + "', '" + m_des + "', '" + start_date + "', '" + n_date + "', '" + e_date + "', " + repetition + ", " + e_repeat + ", '" + e_from + "', '" + e_to + "', '" + e_status
                                + "', " + e_snooze + ")");

                        String text = "Thank you for taking" + m_name + "Your next reminder for " + m_name + "is" + n_date;
                        sp.Speech(mContext, text);
                    }
                    else
                        Toast.makeText(mContext, "Internal Error: Please Try Again Later", Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, "Medicine: " + m_name + stringMessage + " " + n_date, Toast.LENGTH_SHORT).show();
                }
            }
        }
        checkComplete(e_date,n_date,med_id,m_name);
    }

    public boolean checkComplete(String end_date, String next_date, int id, String med_name) {
        ParseString parseString = new ParseString();
        if(parseString.compareDate(end_date,next_date) == -1){
            MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(mContext);
            String med_status = "COMPLETE";
            Toast.makeText(mContext,"Medicine "+med_name+" course is complete",Toast.LENGTH_SHORT).show();
            myDb.execMyQuery("update Report_table set med_status = 'COMPLETED' where med_id = " + id);
            myDb.deleteMedicine("Medicine_table", id);

            return true;
        }

        return false;
    }
}
