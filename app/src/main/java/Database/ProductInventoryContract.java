package Database;

import android.provider.BaseColumns;

public class ProductInventoryContract {
    private ProductInventoryContract(){
    }

    public static abstract class ProductInventoryEntry implements BaseColumns{
        private ProductInventoryEntry() {
        }

        public static final String TABLE_NAME = "Products";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }

}
