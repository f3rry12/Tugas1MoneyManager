package com.amamipro.moneymanager;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amamipro.moneymanager.dbHelper.BulanHelper;

import java.util.ArrayList;

public class BulanAdapter extends RecyclerView.Adapter<BulanAdapter.CustomViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<BulanModel> bulanan;
    private Context context;
    private BulanHelper bulanHelper;


    public BulanAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bulanHelper = new BulanHelper(context);


    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.month_view, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String bulan = bulanan.get(position).getBulan();
        final String tahun = bulanan.get(position).getTahun();
        final String saldo = bulanan.get(position).getSaldo();
        holder.editBulan.setText(bulan);
        holder.editTahun.setText(tahun);
        holder.editSaldo.setText(saldo);


        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                bulanan.get(position).setBulan(holder.editBulan.getText().toString());
                bulanan.get(position).setTahun(holder.editTahun.getText().toString());
                bulanan.get(position).setSaldo(holder.editSaldo.getText().toString());

                bulanHelper.open();
                bulanHelper.update(bulanan.get(position));
                bulanHelper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem(bulanan.get(position).getId());
                bulanan.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    private void deleteitem(int id) {

        bulanHelper.open();
        bulanHelper.delete(id);
        bulanHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return bulanan.size();
    }

    public void addItem(ArrayList<BulanModel> mData) {
        this.bulanan = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText editBulan, editTahun, editSaldo;
        private Button btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editBulan = (EditText) itemView.findViewById(R.id.edit_bulan);
            editTahun = (EditText) itemView.findViewById(R.id.edit_tahun);
            editSaldo = (EditText) itemView.findViewById(R.id.edit_saldo);
            btnupdate = (Button) itemView.findViewById(R.id.btn_update);
            btndelete = (Button) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv);



        }

    }

    public void setFilter(ArrayList<BulanModel> newList){
        bulanan = new ArrayList<>();
        bulanan.addAll(newList);
        notifyDataSetChanged();
    }

}
