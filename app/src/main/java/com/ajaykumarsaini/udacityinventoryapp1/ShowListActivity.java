package com.ajaykumarsaini.udacityinventoryapp1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import Database.InventoryDbHelper;
import Database.ProductInventoryContract;

public class ShowListActivity extends AppCompatActivity {

    private InventoryDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(ShowListActivity.this,AddItemActivity.class);
               startActivity(intent);
            }
        });
                dbHelper = new InventoryDbHelper(this);
        displayDatabaseInfo();
    }



    private Cursor queryData() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM Products"
        // to get a Cursor that contains all rows from the books table.
        String[] projection = {
                ProductInventoryContract.ProductInventoryEntry._ID,
                ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_NAME,
                ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_PRICE,
                ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_QUANTITY,
                ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_NAME,
                ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
        };


        Cursor cursor;
        cursor = db.query(ProductInventoryContract.ProductInventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }


    private void displayDatabaseInfo() {

        Cursor cursor = queryData();

        TextView displayView = findViewById(R.id.listOfDatabaseItem);
        displayView.setText(getString(R.string.the_list_contains));
        displayView.append(" " + cursor.getCount() + " ");
        displayView.append(getString(R.string.products) + "\n\n");

        int idColumnIndex = cursor.getColumnIndex(ProductInventoryContract.ProductInventoryEntry._ID);
        int productNameColumnIndex = cursor.getColumnIndex(ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_NAME);
        int productPriceColumnIndex = cursor.getColumnIndex(ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_PRICE);
        int productQuantityColumnIndex = cursor.getColumnIndex(ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_QUANTITY);
        int supplierNameColumnIndex = cursor.getColumnIndex(ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_NAME);
        int supplierContactColumnIndex = cursor.getColumnIndex(ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

        try {
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentProductPrice = cursor.getInt(productPriceColumnIndex);
                int currentProductQuantity = cursor.getInt(productQuantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierContact = cursor.getString(supplierContactColumnIndex);

                displayView.append("\n" + ProductInventoryContract.ProductInventoryEntry._ID + "  : " + currentID + "\n" +
                        ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_NAME + "  : " + currentProductName + "\n" +
                        ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_PRICE + "  : " + currentProductPrice + "\n" +
                        ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_QUANTITY + "  : " + currentProductQuantity + "\n" +
                        ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_NAME + "  : " + currentSupplierName + "\n" +
                        ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "  : " + currentSupplierContact + "\n");
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
