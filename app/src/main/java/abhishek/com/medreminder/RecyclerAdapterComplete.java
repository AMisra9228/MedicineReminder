package abhishek.com.medreminder;

import android.content.Context;
import android.content.Intent;
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

public class RecyclerAdapterComplete extends RecyclerView.Adapter<RecyclerAdapterComplete.ViewHolder> {

    private ArrayList<Medicine> medicines;
    private Context mContext;

    public RecyclerAdapterComplete(ArrayList<Medicine> medicinesList,Context mContext) {
        Log.d("M_Card 2 :","3");
        this.medicines = medicinesList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerAdapterComplete.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_med_card, parent, false);

        RecyclerAdapterComplete.ViewHolder viewHolder = new RecyclerAdapterComplete.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapterComplete.ViewHolder holder, final int position) {
        Log.d("M_Card 2 :","4");
        holder.txtName.setText(medicines.get(position).getMedicineName());
        holder.txtDes.setText(medicines.get(position).getMedicineDes());
        holder.txtN_Date.setText(medicines.get(position).getStartDate());

//        holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Display option menu
//                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOptionDigit);
//                popupMenu.inflate(R.menu.option_menu_complete);
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//
//                        int m_id;
//                        switch (item.getItemId()) {
//                            case R.id.complete_item_view:
//                                m_id = medicines.get(position).getId();
//                                Intent intent;
//                                intent = new Intent(mContext,IndividualReport.class);
//                                intent.putExtra("m_id",m_id);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                mContext.startActivity(intent);
//                                break;
//                            case R.id.complete_item_delete:
//                                m_id = medicines.get(position).getId();
//                                MedicineDatabaseHelper myDb = new MedicineDatabaseHelper(mContext);
//                                //Delete item from Medicine_table
//                                if(myDb.deleteMedicine("Report_table",m_id)) {
//                                    //reload Cards
//                                    for(int i=medicines.size()-1;i>=0;i--){
//                                        if(medicines.get(i).getId() == m_id){
//                                           medicines.remove(i);
//                                        }
//                                    }
//                                    notifyDataSetChanged();
//                                    Toast.makeText(mContext,"Deleted Successfully. Refresh Requested.", Toast.LENGTH_SHORT).show();
//                                }
//                                else {
//                                    Toast.makeText(mContext,"Error: Unable to Delete", Toast.LENGTH_SHORT).show();
//                                }
//
////                                Intent intent1;
////                                intent1= new Intent(mContext,MainActivity.class);
////                                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                mContext.startActivity(intent1);
//                                break;
//                            default:
//                                break;
//                        }
//                        return false;
//                    }
//                });
//                popupMenu.show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public TextView txtName, txtDes,txtN_Date, txtOptionDigit;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName_Card);
            txtDes = itemView.findViewById(R.id.txtDes_Card);
            txtN_Date = itemView.findViewById(R.id.txtNDate_Card);

            txtOptionDigit = itemView.findViewById(R.id.txtOptionDigit);

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
}
