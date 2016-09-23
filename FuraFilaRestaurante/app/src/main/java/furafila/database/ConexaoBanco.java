package furafila.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class ConexaoBanco extends SQLiteOpenHelper {

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

	String CREATE_TABLE_FOOD = "CREATE TABLE " + TABLE_FOOD + "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " VARCHAR(120),"
			+ KEY_PRICE + " VARCHAR(10),"
			+ KEY_IMAGE + " VARCHAR(20),"
			+ KEY_DESCRIPTION + " TEXT" + ")";

	String CREATE_TABLE_DRINK = "CREATE TABLE " + TABLE_DRINK + "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " VARCHAR(120),"
			+ KEY_PRICE + " VARCHAR(10),"
			+ KEY_IMAGE + " VARCHAR(20),"
			+ KEY_DESCRIPTION + " TEXT" + ")";

	String CREATE_TABLE_DESSERT = "CREATE TABLE " +  TABLE_DESSERT + "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " VARCHAR(120),"
			+ KEY_PRICE + " VARCHAR(10),"
			+ KEY_IMAGE + " VARCHAR(20),"
			+ KEY_DESCRIPTION + " TEXT" + ")";

	// construtor do banco de dados
	public ConexaoBanco(Context context) {
		super(context, DATABASE_NAME, null, 1);

	}

	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_FOOD);
		database.execSQL(CREATE_TABLE_DRINK);
		database.execSQL(CREATE_TABLE_DESSERT);
	}

	public void onUpgrade(SQLiteDatabase database, int oldversion,
			int newversion) {
		// criar condição parar não perder dados usuario

		onCreate(database);
	}
	
	public void onClose(SQLiteDatabase database){
		if(database.isOpen()){
			database.close();
		}
	}

}
