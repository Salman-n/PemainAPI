package tech.fpax.daftarpemain;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PemainAdapter extends RecyclerView.Adapter<PemainAdapter.PemainViewHolder> {


    private ArrayList<Pemain> dataList;

    public PemainAdapter(ArrayList<Pemain> dataList) {
        this.dataList = dataList;
    }

    @Override
    public PemainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_pemain, parent, false);
        return new PemainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PemainViewHolder holder, final int position) {


        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtGambar.setText(dataList.get(position).getGambar());
        Picasso.get().load(dataList.get(position).getGambar()).into(holder.imageView);
        holder.txtNoHp.setText(dataList.get(position).getId());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("RecyclerView", "onClick：" + position);
                Intent intent = new Intent(v.getContext(), viewPemainActivity.class);
                intent.putExtra("id", dataList.get(position).getId().toString());
                v.getContext().startActivity(intent);

            }
        });


        
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PemainViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtGambar, txtNoHp;
        private ImageView imageView;

        public PemainViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.tv1);
            txtGambar = (TextView) itemView.findViewById(R.id.htvgambar);
            txtNoHp = (TextView) itemView.findViewById(R.id.htvid);
            imageView =  (ImageView) itemView.findViewById(R.id.iv1);




        }
    }
}