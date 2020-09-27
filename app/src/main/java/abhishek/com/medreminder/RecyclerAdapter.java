package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


import static abhishek.com.medreminder.MedicineDatabaseHelper.COL_1;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Medicine> medicines;
    private Context mContext;

    Speech sp = new Speech();
    String m_nam;


    public RecyclerAdapter(ArrayList<Medicine> medicinesList,Context mContext) {
        Log.d("M_Card 1 :","3");
        this.medicines = medicinesList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {
        Log.d("M_Card 1 :","4");
        holder.txtName.setText(medicines.get(position).getMedicineName());
        holder.txtDes.setText(medicines.get(position).getMedicineDes());
        holder.txtN_Date.setText(medicines.get(position).getStartDate());

        m_nam = medicines.get(position).getMedicineName();

        //Initial speech
        sp.Speech(mContext,"");

        holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Display option menu
                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int m_id;
                        switch (item.getItemId()) {
                            case R.id.mnu_item_edit:
                                m_id = medicines.get(position).getId();
                                Intent intent;
                                intent = new Intent(mContext,EditMedicine.class);
                                intent.putExtra("m_id",m_id);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                mContext.startActivity(intent);
                                break;
                            case R.id.mnu_item_delete:
                                m_id = medicines.get(position).getId();
                                MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(mContext);
                                //Delete item from Medicine_table
                                if(myDb.deleteMedicine("Medicine_table",m_id)) {
                                    myDb.execMyQuery("update Report_table set med_status = 'TERMINATED' where med_id = "+m_id);
                                    medicines.remove(position);         //delete from medicine list
                                    notifyDataSetChanged();

                                    String text = m_nam + "has been removed from your reminder list";
                                    sp.Speech(mContext,text);
                                    Toast.makeText(mContext,"Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(mContext,"Error: Unable to Delete", Toast.LENGTH_SHORT).show();
                                }

//                                Intent intent1;
//                                intent1= new Intent(mContext,MainActivity.class);
//                                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                mContext.startActivity(intent1);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public TextView row_id,txtName, txtDes, txtN_Date, txtOptionDigit;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName_Card);
            txtDes = itemView.findViewById(R.id.txtDes_Card);
            txtN_Date = itemView.findViewById(R.id.txtNDate_Card);
            txtOptionDigit = itemView.findViewById(R.id.txtOptionDigit);
            //row_id = itemView.findViewById(R.id.row_id);

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

    public boolean deleteFromPosition(int listPosition) {
        MedicineDatabaseHelper medicineDatabaseHelper = new MedicineDatabaseHelper(mContext);
        // int m_id = medicines.get(position).getMedicineId()
        boolean delete = medicineDatabaseHelper.deleteListMedicine(listPosition);
        if (delete == true) {
            Toast.makeText(mContext, "Deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mContext, "Deletion Unsuccessful", Toast.LENGTH_LONG).show();
        }
        return delete;
    }
}

