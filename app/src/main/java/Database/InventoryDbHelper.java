package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product_inventory.db";

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + ProductInventoryContract.ProductInventoryEntry.TABLE_NAME + " ("
                + ProductInventoryContract.ProductInventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                + ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
