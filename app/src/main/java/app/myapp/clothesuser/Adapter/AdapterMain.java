package app.myapp.clothesuser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import app.myapp.clothesuser.Model.ItemMain;
import app.myapp.clothesuser.R;
import app.myapp.clothesuser.ViewPartClothes;


public class AdapterMain extends RecyclerView.Adapter<AdapterMain.MyHolder> {

    ArrayList<ItemMain> arrayList;
    Context context;

    public AdapterMain(ArrayList<ItemMain> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMain.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view= LayoutInflater.from(context).inflate(R.layout.split_main,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMain.MyHolder holder, int position) {

        ItemMain itemMain=arrayList.get(position);

        int id=itemMain.getId();


        holder.textView.setText(itemMain.getName());

        Picasso.get().load(itemMain.getImage()).resize(300,300).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewPartClothes.class);
                intent.putExtra("id",id);
                intent.putExtra("name",itemMain.getName());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageView;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_main);
            textView=itemView.findViewById(R.id.text_name_main);
        }
    }



}
