package com.iteso.tarea4_parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.tarea4_parcelable.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {

    ItemProduct itemReceived;
    EditText title, store, phone, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        itemReceived = getIntent().getParcelableExtra("ITEM");
        if(itemReceived != null){
            switch (itemReceived.getImage()){
                case 0:
                    ((ImageView) findViewById(R.id.item_product_image)).setImageResource(R.drawable.mac);
                    break;
                case 1:
                    ((ImageView) findViewById(R.id.item_product_image)).setImageResource(R.drawable.alienware);
                    break;
            }
        }

        title = findViewById(R.id.activity_product_edittext_title);
        phone = findViewById(R.id.activity_product_edittext_phone);
        store = findViewById(R.id.activity_product_edittext_store);
        location  = findViewById(R.id.activity_product_edittext_location);

    }

    public void saveChanges(View view){
        itemReceived.setPhone(phone.getText().toString());
        itemReceived.setStore(store.getText().toString());
        itemReceived.setLocation(location.getText().toString());
        itemReceived.setTitle(title.getText().toString());
        Intent result = new Intent();
        result.putExtra("ITEM", itemReceived);
        setResult(RESULT_OK, result);
        finish();
    }

    public void cancelChanges(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

}
