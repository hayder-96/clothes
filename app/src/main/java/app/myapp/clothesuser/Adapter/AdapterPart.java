package app.myapp.clothesuser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.myapp.clothesuser.Model.ItemPart;
import app.myapp.clothesuser.R;
import app.myapp.clothesuser.ViewClothes;


public class AdapterPart extends RecyclerView.Adapter<AdapterPart.MyHolder> {



    ArrayList<ItemPart> arrayList;
    Context context;
    String name;



    public AdapterPart(ArrayList<ItemPart> arrayList, Context context,String name) {
        this.arrayList = arrayList;
        this.context = context;
        this.name=name;
    }

    public AdapterPart(ArrayList<ItemPart> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPart.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.split_part_clothes, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPart.MyHolder holder, int position) {

        ItemPart itemPart=arrayList.get(position);

        holder.name.setText(itemPart.getName());
        holder.price.setText("$"+itemPart.getPrice());
        int id=itemPart.getId();

        Toast.makeText(context,itemPart.getType()+"فغحث",Toast.LENGTH_SHORT).show();

        Picasso.get().load(itemPart.getImage1()).resize(300,200).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ViewClothes.class);



                intent.putExtra("id",id);
                intent.putExtra("shoes",itemPart.getType());
                intent.putExtra("name",itemPart.getName());
                intent.putExtra("price",itemPart.getPrice());
                intent.putExtra("image1",itemPart.getImage1());
                intent.putExtra("image2",itemPart.getImage2());
                intent.putExtra("image3",itemPart.getImage3());
                intent.putExtra("image4",itemPart.getImage4());
                intent.putExtra("image5",itemPart.getImage5());
                intent.putExtra("number1",itemPart.getNumber1());
                intent.putExtra("number2",itemPart.getNumber2());
                intent.putExtra("number3",itemPart.getNumber3());
                intent.putExtra("number4",itemPart.getNumber4());
                intent.putExtra("number5",itemPart.getNumber5());
                intent.putExtra("number6",itemPart.getNumber6());
                intent.putExtra("color1",itemPart.getColor1());
                intent.putExtra("color2",itemPart.getColor2());
                intent.putExtra("color3",itemPart.getColor3());
                intent.putExtra("color4",itemPart.getColor4());
                intent.putExtra("color5",itemPart.getColor5());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name,price;
        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.text_name_peice);
            price=itemView.findViewById(R.id.text_price_price);
            imageView=itemView.findViewById(R.id.image_part_add);
        }
    }
}
