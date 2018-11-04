package com.ajaykumarsaini.udacityinventoryapp1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.InventoryDbHelper;
import Database.ProductInventoryContract;

public class AddItemActivity extends AppCompatActivity {

    public InventoryDbHelper dbHelper;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productQuantityEditText;
    private EditText supplierNameEditText;
    private EditText supplierContactEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        productNameEditText = findViewById(R.id.productName);
        productPriceEditText = findViewById(R.id.productPrice);
        productQuantityEditText = findViewById(R.id.productQuantity);
        supplierNameEditText = findViewById(R.id.productSupplierName);
        supplierContactEditText = findViewById(R.id.productSupplierPhoneNumber);
        dbHelper = new InventoryDbHelper(this);

        Button addNewProductButton = findViewById(R.id.submitButton);
        addNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
    }

    private void addProduct() {
        String productName;
        if (TextUtils.isEmpty(productNameEditText.getText())) {
            productNameEditText.setError(getString(R.string.required_field));
            return;
        } else {
            productName = productNameEditText.getText().toString().trim();
        }

        String productPrice;
        if (TextUtils.isEmpty(productPriceEditText.getText())) {
            productPriceEditText.setError(getString(R.string.required_field));
            return;
        } else {
            productPrice = productPriceEditText.getText().toString().trim();
        }

        String productQuantity;
        if (TextUtils.isEmpty(productQuantityEditText.getText())) {
            productQuantityEditText.setError(getString(R.string.required_field));
            return;
        } else {
            productQuantity = productQuantityEditText.getText().toString().trim();
        }

        String supplierName;
        if (TextUtils.isEmpty(supplierNameEditText.getText())) {
            supplierNameEditText.setError(getString(R.string.required_field));
            return;
        } else {
            supplierName = supplierNameEditText.getText().toString().trim();
        }

        String supplierContact;
        if (TextUtils.isEmpty(supplierContactEditText.getText())) {
            supplierContactEditText.setError(getString(R.string.required_field));
            return;
        } else {
            supplierContact = supplierContactEditText.getText().toString().trim();
        }


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int productPriceInt = Integer.parseInt(productPrice);
        ContentValues values = new ContentValues();
        values.put(ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_PRICE, productPriceInt);
        values.put(ProductInventoryContract.ProductInventoryEntry.COLUMN_PRODUCT_QUANTITY, productQuantity);
        values.put(ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(ProductInventoryContract.ProductInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierContact);

        long newRowId = db.insert(ProductInventoryContract.ProductInventoryEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, getString(R.string.error_while_adding), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.product_added) + " " + newRowId, Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
