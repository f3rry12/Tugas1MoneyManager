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
import android.widget.Toast;

import com.amamipro.moneymanager.dbHelper.HutangHelper;

import java.util.ArrayList;

public class HutangAdapter extends RecyclerView.Adapter<HutangAdapter.CustomViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<HutangModel> hutang;
    private Context context;
    private HutangHelper hutangHelper;


    public HutangAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hutangHelper = new HutangHelper(context);


    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.month_view, viewGroup, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final String nama = hutang.get(position).getNama();
        final String jumlahu = hutang.get(position).getJumlahu();
        holder.editNama.setText(nama);
        holder.editJumlahu.setText(jumlahu);


        holder.btnupdateu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                hutang.get(position).setNama(holder.editNama.getText().toString());
                hutang.get(position).setJumlahu(holder.editJumlahu.getText().toString());

                hutangHelper.open();
                hutangHelper.update(hutang.get(position));
                hutangHelper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.btndeleteu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem(hutang.get(position).getId());
                hutang.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    private void deleteitem(int id) {

        hutangHelper.open();
        hutangHelper.delete(id);
        hutangHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return hutang.size();
    }

    public void addItem(ArrayList<HutangModel> mData) {
        this.hutang = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText editNama, editJumlahu;
        private Button btnupdateu, btndeleteu;
        private CardView cv2;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editNama = (EditText) itemView.findViewById(R.id.edit_nama);
            editJumlahu = (EditText) itemView.findViewById(R.id.edit_jumlahu);
            btnupdateu = (Button) itemView.findViewById(R.id.btn_updateu);
            btndeleteu = (Button) itemView.findViewById(R.id.btn_deleteu);
            cv2 = (CardView) itemView.findViewById(R.id.cv2);



        }

    }

    public void setFilter(ArrayList<HutangModel> newList){
        hutang = new ArrayList<>();
        hutang.addAll(newList);
        notifyDataSetChanged();
    }

}
