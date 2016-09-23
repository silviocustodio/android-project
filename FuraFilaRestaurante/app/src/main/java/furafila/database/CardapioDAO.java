package furafila.database;

/**
 * Created by Silvio on 09/09/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import furafila.furafilarestaurante.Cardapio;

public class CardapioDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CardapioDB";
    private static final String TABLE_FOOD = "tbFood";
    private static final String TABLE_DRINK = "tbDrink";
    private static final String TABLE_DESSERT = "tbDessert";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";

    public CardapioDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FOOD = "CREATE TABLE " + TABLE_FOOD + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " VARCHAR(120),"
                + KEY_PRICE + " VARCHAR(10),"
                + KEY_IMAGE + " VARCHAR(20),"
                + KEY_DESCRIPTION + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_FOOD);

        String CREATE_TABLE_DRINK = "CREATE TABLE " + TABLE_DRINK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " VARCHAR(120),"
                + KEY_PRICE + " VARCHAR(10),"
                + KEY_IMAGE + " VARCHAR(20),"
                + KEY_DESCRIPTION + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_DRINK);

        String CREATE_TABLE_DESSERT = "CREATE TABLE " +  TABLE_DESSERT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " VARCHAR(120),"
                + KEY_PRICE + " VARCHAR(10),"
                + KEY_IMAGE + " VARCHAR(20),"
                + KEY_DESCRIPTION + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_DESSERT);

        Cardapio cardapio = new Cardapio();
        ContentValues values = new ContentValues();

        for (int i = 1; i <= 11; i++) {
            cardapio.setName("A la food " + i);
            cardapio.setPrice("R$ 14,99");
            cardapio.setImage("f_" + i);
            cardapio.setDescription("A la food a la food a la food a la food a la food a la food a la food a la food a la food a la food a la food a la food a la food a la food");

            values.put(KEY_NAME, cardapio.getName());
            values.put(KEY_PRICE, cardapio.getPrice());
            values.put(KEY_IMAGE, cardapio.getImage());
            values.put(KEY_DESCRIPTION, cardapio.getDescription());

            db.insert(TABLE_FOOD, null, values);
        }

        for (int i = 1; i <= 4; i++) {
            cardapio.setName("A la drink " + i);
            cardapio.setPrice("R$ 7,90");
            cardapio.setImage("d_" + i);
            cardapio.setDescription("A la drink a la drink a la drink a la drink a la drink a la drink a la drink a la drink a la drink a la drink a la drink a la drink a la drink");

            values.put(KEY_NAME, cardapio.getName());
            values.put(KEY_PRICE, cardapio.getPrice());
            values.put(KEY_IMAGE, cardapio.getImage());
            values.put(KEY_DESCRIPTION, cardapio.getDescription());

            db.insert(TABLE_DRINK, null, values);
        }

        for (int i = 1; i <= 5; i++) {
            cardapio.setName("A la dessert " + i);
            cardapio.setPrice("R$ 10,90");
            cardapio.setImage("e_" + i);
            cardapio.setDescription("A la dessert a la dessert  A la dessert a la dessert  A la dessert a la dessert  A la dessert a la dessert  A la dessert a la dessert  A la dessert");

            values.put(KEY_NAME, cardapio.getName());
            values.put(KEY_PRICE, cardapio.getPrice());
            values.put(KEY_IMAGE, cardapio.getImage());
            values.put(KEY_DESCRIPTION, cardapio.getDescription());

            db.insert( TABLE_DESSERT, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINK);
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_DESSERT);
        onCreate(db);
    }



    public Cardapio getFoodItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD, new String[] {
                KEY_ID, KEY_NAME, KEY_PRICE, KEY_IMAGE, KEY_DESCRIPTION
        }, KEY_ID + "=?", new String[] {
                String.valueOf(id)
        }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Cardapio cardapio = new Cardapio(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return cardapio;
    }

    public Cardapio getDrinkItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DRINK, new String[] {
                KEY_ID, KEY_NAME, KEY_PRICE, KEY_IMAGE, KEY_DESCRIPTION
        }, KEY_ID + "=?", new String[] {
                String.valueOf(id)
        }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Cardapio cardapio = new Cardapio(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return cardapio;
    }
    public Cardapio getDessertItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query( TABLE_DESSERT, new String[] {
                KEY_ID, KEY_NAME, KEY_PRICE, KEY_IMAGE, KEY_DESCRIPTION
        }, KEY_ID + "=?", new String[] {
                String.valueOf(id)
        }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Cardapio cardapio = new Cardapio(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return cardapio;
    }

    public List<Cardapio> getAllFoods() {
        List<Cardapio> cardapioList = new ArrayList<Cardapio>();

        String selectQuery = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cardapio cardapio = new Cardapio();
                cardapio.setID((int) cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                cardapio.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                cardapio.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                cardapio.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                cardapio.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));

                cardapioList.add(cardapio);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return cardapioList;
    }

    public List<Cardapio> getAllDrinks() {
        List<Cardapio> cardapioList = new ArrayList<Cardapio>();

        String selectQuery = "SELECT * FROM " + TABLE_DRINK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cardapio cardapio = new Cardapio();
                cardapio.setID((int) cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                cardapio.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                cardapio.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                cardapio.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                cardapio.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));

                cardapioList.add(cardapio);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return cardapioList;
    }


    public List<Cardapio> getAllDessert() {
        List<Cardapio> cardapioList = new ArrayList<Cardapio>();

        String selectQuery = "SELECT * FROM " +  TABLE_DESSERT;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cardapio cardapio = new Cardapio();
                cardapio.setID((int) cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                cardapio.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                cardapio.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                cardapio.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                cardapio.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));

                cardapioList.add(cardapio);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return cardapioList;
    }


    }

