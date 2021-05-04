package app.myapp.clothesuser.Server;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import app.myapp.clothesuser.Model.ItemAccept;


public class SqliteBascket extends SQLiteOpenHelper {


    public static final String DATA_BASE = "bascketclothes";
    public static final int version = 1;
    public static final String TABLE = "bascket";
    public static final String ID = "id";
    public static final String NAMEFOOD = "name";
    public static final String PRICE = "price";
    public static final String NUMBER = "number";
    public static final String IMAGE = "image";
    public static final String COLOR = "color";
    public static final String NUMSIZE = "numsize";
    public static final String CLOTHES_ID = "clothes_id";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ NAMEFOOD + " TEXT ," + PRICE + " TEXT ," +COLOR+" Text,"+ NUMBER+ " TEXT,"+CLOTHES_ID+" Text,"+IMAGE+" Text,"+NUMSIZE+" Text);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE;


    public SqliteBascket(@Nullable Context context) {
        super(context,DATA_BASE,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
    }





    public long insertData(ItemAccept itemFoodUser) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMEFOOD,itemFoodUser.getName());
        contentValues.put(PRICE,itemFoodUser.getPrice());
        contentValues.put(NUMBER,itemFoodUser.getNumber());
        contentValues.put(IMAGE,itemFoodUser.getImage());
        contentValues.put(COLOR,itemFoodUser.getColor());
        contentValues.put(CLOTHES_ID,itemFoodUser.getClothes_id());
        contentValues.put(NUMSIZE,itemFoodUser.getNumsize());

        long l= sqLiteDatabase.insert(TABLE, null, contentValues);
        sqLiteDatabase.close();
        return l;
    }






        public ArrayList<ItemAccept> getData() {
        ArrayList<ItemAccept> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE, null);
        if (cursor.moveToFirst() && cursor != null) {

            do {

                int id=cursor.getInt(cursor.getColumnIndex(ID));
               String name= cursor.getString(cursor.getColumnIndex(NAMEFOOD));
               String price=cursor.getString(cursor.getColumnIndex(PRICE));
                String number=cursor.getString(cursor.getColumnIndex(NUMBER));
                String image=cursor.getString(cursor.getColumnIndex(IMAGE));
                String color=cursor.getString(cursor.getColumnIndex(COLOR));
                String nm=cursor.getString(cursor.getColumnIndex(NUMSIZE));
                int clothes_id=cursor.getInt(Integer.parseInt(String.valueOf(cursor.getColumnIndex(CLOTHES_ID))));

                arrayList.add(new ItemAccept(id,name,price,number,image,color,clothes_id,nm));

            } while (cursor.moveToNext());

            return arrayList;
        }
        return null;
    }



    public int delete(int id){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String[] IDI={id+""};

        int ii= sqLiteDatabase.delete(TABLE,ID+" =?",IDI);
        return ii;
    }





    public void delete() {
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE, null, null);
    }




























}
