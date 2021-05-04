package app.myapp.clothesuser.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import app.myapp.clothesuser.Model.ItemNoty;
import app.myapp.clothesuser.R;

public class AdapterNotifay extends RecyclerView.Adapter<AdapterNotifay.MyHolder> {


    ArrayList<ItemNoty> arrayList;
    Context context;

    public AdapterNotifay(ArrayList<ItemNoty> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterNotifay.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.split_notifay, parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotifay.MyHolder holder, int position) {

        ItemNoty itemNoty=arrayList.get(position);

        holder.name.setText(itemNoty.getName());
        holder.content.setText(itemNoty.getContent());
        holder.time.setText(formatdate(itemNoty.getTime()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name,content,time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name_noty);
            content=itemView.findViewById(R.id.content_noty);
            time=itemView.findViewById(R.id.time_noty);



        }



    }



    private String formatdate(String newDate){



        CharSequence  niceDateStr=null;

        Date date=null;

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s");

        try {
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            date=inputFormat.parse(newDate);
            niceDateStr = DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return niceDateStr.toString();

    }
}
