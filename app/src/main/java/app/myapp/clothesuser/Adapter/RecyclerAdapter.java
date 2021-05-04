package app.myapp.clothesuser.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.myapp.clothesuser.MenuBascket;
import app.myapp.clothesuser.Model.ItemAccept;
import app.myapp.clothesuser.R;
import app.myapp.clothesuser.Server.SqliteBascket;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {


    ArrayList<ItemAccept> arrayList;
    Context context;
    public RecyclerAdapter(ArrayList<ItemAccept> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.split_bascket, parent, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyHolder holder, final int position) {

        ItemAccept itemFoodUser=arrayList.get(position);

        holder.name.setText(itemFoodUser.getName());
        holder.price.setText(itemFoodUser.getPrice()+"\t"+"$");
        holder.number.setText("الحجم"+"\t"+itemFoodUser.getNumber());
        holder.col.setBackgroundColor(Integer.parseInt(itemFoodUser.getColor()));
        final int id=itemFoodUser.getId();
       // if (itemFoodUser.getImagefood().equals("no")) {


       // }else {
            Picasso.get().load(itemFoodUser.getImage()).resize(250, 250).into(holder.imageView);

       // }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               SqliteBascket sqliteBascket=new SqliteBascket(context);


               sqliteBascket.delete(id);
               arrayList.remove(position);
                MenuBascket.noty();

            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,number;
        Button button,col;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_food_user);
            name=itemView.findViewById(R.id.name_food_user);
            price=itemView.findViewById(R.id.price_food_user);
            number=itemView.findViewById(R.id.number_food_user);
            col=itemView.findViewById(R.id.color);
             button=itemView.findViewById(R.id.but_delete_food);
        }
    }











//    private void Delete(int p,int id){
//
//
//        arrayList.remove(p);
//        SqliteBascket s=new SqliteBascket(context);
//        s.delete(id);
//        MenuRequest.noti();
//
//    }
}
