package app.myapp.clothesuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import app.myapp.clothesuser.Model.ItemAccept;
import app.myapp.clothesuser.Server.SqliteBascket;

public class ViewClothes extends AppCompatActivity {




    ImageView imageView;
    RadioGroup radioGroup1,radioGroup2;
    TextView checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    Button r1,r2,r3,r4,r5;
    String checkSize;
    String c1,c2,c3,c4,c5,image1,image2,image3,image4,image5,
            number1,number2,number3,number4,number5,number6,name,price;
    TextView view_name,view_price;
    int id;

    String ch,col,i;
    String nm,shoes;

    TextView nz1,nz2,nz3,nz4,nz5,nz6;
    SqliteBascket sqliteBascket;

    LinearLayout layout1,layout2,layout3,layout4,layout5,l1,l2,l3,l4,l5,l6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clothes);


        layout1=findViewById(R.id.l1);
        layout2=findViewById(R.id.l2);
        layout3=findViewById(R.id.l3);
        layout4=findViewById(R.id.l4);
        layout5=findViewById(R.id.l5);


        l1=findViewById(R.id.ll1);
        l2=findViewById(R.id.ll2);
        l3=findViewById(R.id.ll3);
        l4=findViewById(R.id.ll4);
        l5=findViewById(R.id.ll5);
        l6=findViewById(R.id.ll6);





        Intent intent=getIntent();



        id=intent.getIntExtra("id",-1);
        name=intent.getStringExtra("name");
        price=intent.getStringExtra("price");
        shoes=intent.getStringExtra("shoes");

        view_name=findViewById(R.id.textView_name);
        view_price=findViewById(R.id.textView_price);


        nz1=findViewById(R.id.textView_sz1);
        nz2=findViewById(R.id.textView_sz2);
        nz3=findViewById(R.id.textView_sz3);
        nz4=findViewById(R.id.textView_sz4);
        nz5=findViewById(R.id.textView_sz5);
        nz6=findViewById(R.id.textView_sz6);




        sqliteBascket=new SqliteBascket(this);




        imageView=findViewById(R.id.imageView_clothes);




        r1=findViewById(R.id.radioButton1);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton3);
        r4=findViewById(R.id.radioButton4);
        r5=findViewById(R.id.radioButton5);

        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);











        image1=intent.getStringExtra("image1");
        image2=intent.getStringExtra("image2");
        image3=intent.getStringExtra("image3");
        image4=intent.getStringExtra("image4");
        image5=intent.getStringExtra("image5");




        c1=intent.getStringExtra("color1");
        c2=intent.getStringExtra("color2");
        c3=intent.getStringExtra("color3");
        c4=intent.getStringExtra("color4");
        c5=intent.getStringExtra("color5");



        number1=intent.getStringExtra("number1");
        number2=intent.getStringExtra("number2");
        number3=intent.getStringExtra("number3");
        number4=intent.getStringExtra("number4");
        number5=intent.getStringExtra("number5");
        number6=intent.getStringExtra("number6");



        view_name.setText(name);
        view_price.setText(price);



        Picasso.get().load(image1).resize(500, 300).into(imageView);
        getRadio();
        numm();

        shoes();


        col = r1.getText().toString();
        i = image1;


        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image1).resize(500, 300).into(imageView);


                col =c1;

                i = image1;
                layout1.setBackgroundResource(R.color.grey);
                layout2.setBackgroundResource(R.color.white);
                layout3.setBackgroundResource(R.color.white);
                layout4.setBackgroundResource(R.color.white);
                layout5.setBackgroundResource(R.color.white);

            }
        });



        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image2).resize(500, 300).into(imageView);

                col =c2;
                i = image2;
                layout1.setBackgroundResource(R.color.white);
                layout2.setBackgroundResource(R.color.grey);
                layout3.setBackgroundResource(R.color.white);
                layout4.setBackgroundResource(R.color.white);
                layout5.setBackgroundResource(R.color.white);
            }
        });



        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image3).resize(500, 300).into(imageView);


                col =c3;
                i = image3;

                layout1.setBackgroundResource(R.color.white);
                layout2.setBackgroundResource(R.color.white);
                layout3.setBackgroundResource(R.color.grey);
                layout4.setBackgroundResource(R.color.white);
                layout5.setBackgroundResource(R.color.white);
            }
        });





        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image4).resize(500, 300).into(imageView);


                col =c4;
                i = image4;

                layout1.setBackgroundResource(R.color.white);
                layout2.setBackgroundResource(R.color.white);
                layout3.setBackgroundResource(R.color.white);
                layout4.setBackgroundResource(R.color.grey);
                layout5.setBackgroundResource(R.color.white);
            }
        });





        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image5).resize(500, 300).into(imageView);

                col =c5;
                i = image5;

                layout1.setBackgroundResource(R.color.white);
                layout2.setBackgroundResource(R.color.white);
                layout3.setBackgroundResource(R.color.white);
                layout4.setBackgroundResource(R.color.white);
                layout5.setBackgroundResource(R.color.grey);
            }
        });








    }




    private void getRadio(){


        if (c1!="null") {
            r1.setBackgroundColor(Integer.parseInt(c1));

        }else {
            r1.setVisibility(View.GONE);
        }
        if (!c2.equals("null")){
            r2.setBackgroundColor(Integer.parseInt(c2));
        }else {

            r2.setVisibility(View.GONE);
        }

        if (!c3.equals("null")){
            r3.setBackgroundColor(Integer.parseInt(c3));
        }else {

            r3.setVisibility(View.GONE);
        }


        if (!c4.equals("null")){
            r4.setBackgroundColor(Integer.parseInt(c4));
        }else {

            r4.setVisibility(View.GONE);
        }


        if (!c5.equals("null")){
            r5.setBackgroundColor(Integer.parseInt(c5));
        }else {

            r5.setVisibility(View.GONE);
        }





        if (!number1.equals("null")){

            checkBox1.setText("Small");
        }else {

            checkBox1.setVisibility(View.GONE);
        }



        if (!number2.equals("null")){

            checkBox2.setText("Medium");
        }else {

            checkBox2.setVisibility(View.GONE);
        }



        if (!number3.equals("null")){

            checkBox3.setText("Large");
        }else {

            checkBox3.setVisibility(View.GONE);
        }



        if (!number4.equals("null")){

            checkBox4.setText("XL");
        }else {

            checkBox4.setVisibility(View.GONE);
        }


        if (!number5.equals("null")){

            checkBox5.setText("2XL");
        }else {

            checkBox5.setVisibility(View.GONE);
        }


        if (!number6.equals("null")){

            checkBox6.setText("3XL");
        }else {

            checkBox6.setVisibility(View.GONE);
        }




    }


    public void groupe2(View view) {


        String n = ((CheckBox)view).getText().toString().trim();

        if (n.equals(number1)) {

            checkSize=number1;
        } else if (n.equals(number2)) {

            checkSize=number2;

        } else if (n.equals(number3)) {

            checkSize=number3;
        } else if (n.equals(number4)) {

            checkSize = number4;

        }else if (n.equals(number5)){

            checkSize=number5;

        }else{

            checkSize=number1;
        }
    }





//    public void group1(View view) {
//
//
//        String n = ((RadioButton) view).getText().toString().trim();
//
//        if (n.equals(c2)) {
//
//            Picasso.get().load(image1).resize(500, 300).into(imageView);
//            Toast.makeText(getApplicationContext(),c2,Toast.LENGTH_SHORT).show();
//
//        } else if (n.equals(c1)) {
//
//            Picasso.get().load(image2).resize(500, 300).into(imageView);
//            Toast.makeText(getApplicationContext(),c1,Toast.LENGTH_SHORT).show();
//        } else if (n.equals(c3)) {
//
//            Picasso.get().load(image3).resize(500, 300).into(imageView);
//
//            Toast.makeText(getApplicationContext(),c3,Toast.LENGTH_SHORT).show();
//        } else if (n.equals(c4)) {
//
//            Picasso.get().load(image4).resize(500, 300).into(imageView);
//
//            Toast.makeText(getApplicationContext(),c4,Toast.LENGTH_SHORT).show();
//        }else{
//
//            Toast.makeText(getApplicationContext(),c5,Toast.LENGTH_SHORT).show();
//            Picasso.get().load(image5).resize(500, 300).into(imageView);
//        }
//    }


    public void Add(View view) {


        sqliteBascket.insertData(new ItemAccept(view_name.getText().toString().trim(),price,ch,i,col,id,nm));

        
    }

    public void ch1(View view) {

        ch="Small";
        nm=number1;

        l1.setBackgroundResource(R.color.grey);
        l2.setBackgroundResource(R.color.white);
        l3.setBackgroundResource(R.color.white);
        l4.setBackgroundResource(R.color.white);
        l5.setBackgroundResource(R.color.white);
        l6.setBackgroundResource(R.color.white);

    }

    public void ch2(View view) {
        ch="Medium";
        nm=number2;

        l1.setBackgroundResource(R.color.white);
        l2.setBackgroundResource(R.color.grey);
        l3.setBackgroundResource(R.color.white);
        l4.setBackgroundResource(R.color.white);
        l5.setBackgroundResource(R.color.white);
        l6.setBackgroundResource(R.color.white);
    }

    public void ch3(View view) {
        ch="Large";
        nm=number3;

        l1.setBackgroundResource(R.color.white);
        l2.setBackgroundResource(R.color.white);
        l3.setBackgroundResource(R.color.grey);
        l4.setBackgroundResource(R.color.white);
        l5.setBackgroundResource(R.color.white);
        l6.setBackgroundResource(R.color.white);
    }

    public void ch4(View view) {
        ch="XL";
        nm=number4;

        l1.setBackgroundResource(R.color.white);
        l2.setBackgroundResource(R.color.white);
        l3.setBackgroundResource(R.color.white);
        l4.setBackgroundResource(R.color.grey);
        l5.setBackgroundResource(R.color.white);
        l6.setBackgroundResource(R.color.white);
    }


    public void ch5(View view) {
        ch="2XL";
        nm=number5;

        l1.setBackgroundResource(R.color.white);
        l2.setBackgroundResource(R.color.white);
        l3.setBackgroundResource(R.color.white);
        l4.setBackgroundResource(R.color.white);
        l5.setBackgroundResource(R.color.grey);
        l6.setBackgroundResource(R.color.white);
    }

    public void ch6(View view) {
        ch="3XL";
        nm=number6;

        l1.setBackgroundResource(R.color.white);
        l2.setBackgroundResource(R.color.white);
        l3.setBackgroundResource(R.color.white);
        l4.setBackgroundResource(R.color.white);
        l5.setBackgroundResource(R.color.white);
        l6.setBackgroundResource(R.color.grey);
    }







    private void numm(){


        if (number1.equals("1")||number1.equals("2")){

            nz1.setText("الكمية المتبقية"+number1);
            nz1.setVisibility(View.VISIBLE);

        }

        if (number2.equals("1")||number2.equals("2")){

            nz2.setText("الكمية المتبقية"+number2);

            nz2.setVisibility(View.VISIBLE);

        }

        if (number3.equals("1")||number3.equals("2")){

            nz3.setText("الكمية المتبقية"+number3);
            nz3.setVisibility(View.VISIBLE);

        }

        if (number4.equals("1")||number4.equals("2")){

            nz4.setText("الكمية المتبقية"+number4);
            nz4.setVisibility(View.VISIBLE);


        }


        if (number5.equals("1")||number5.equals("2")){

            nz5.setText("الكمية المتبقية"+number5);
            nz5.setVisibility(View.VISIBLE);


        }

        if (number6.equals("1")||number6.equals("2")){

            nz6.setText("الكمية المتبقية"+number6);
            nz6.setVisibility(View.VISIBLE);

        }




    }

    private void shoes(){

        if (shoes.equals("احذية")){





            if (!number1.equals("null")){

                checkBox1.setText("37");
            }else {

                checkBox1.setVisibility(View.GONE);
            }



            if (!number2.equals("null")){

                checkBox2.setText("38");
            }else {

                checkBox2.setVisibility(View.GONE);
            }



            if (!number3.equals("null")){

                checkBox3.setText("39");
            }else {

                checkBox3.setVisibility(View.GONE);
            }



            if (!number4.equals("null")){

                checkBox4.setText("40");
            }else {

                checkBox4.setVisibility(View.GONE);
            }


            if (!number5.equals("null")){

                checkBox5.setText("41");
            }else {

                checkBox5.setVisibility(View.GONE);
            }


            if (!number6.equals("null")){

                checkBox6.setText("42");
            }else {

                checkBox6.setVisibility(View.GONE);
            }












        }


    }

}