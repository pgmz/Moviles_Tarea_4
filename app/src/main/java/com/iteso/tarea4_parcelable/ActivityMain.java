package com.iteso.tarea4_parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.iteso.tarea4_parcelable.beans.ItemProduct;
import java.util.ArrayList;
import static java.security.AccessController.getContext;

public class ActivityMain extends AppCompatActivity {

    public static final int CHANGE_PRODUCT = 1;
    RecyclerView recyclerView;
    AdapterProduct adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setHasFixedSize(true);

        ArrayList<ItemProduct> products = new ArrayList<>();
        ItemProduct macItemProduct = new ItemProduct();
        macItemProduct.setImage(0);
        macItemProduct.setTitle(getString(R.string.activity_main_MAC_name));
        macItemProduct.setDescription(getString(R.string.activity_main_MAC_description));
        macItemProduct.setLocation(getString(R.string.activity_main_store_location));
        macItemProduct.setStore(getString(R.string.activity_main_store_name));
        macItemProduct.setPhone(getString(R.string.activity_main_store_phone));

        products.add(macItemProduct);

        ItemProduct alienwareItemProduct = new ItemProduct();
        alienwareItemProduct.setImage(1);
        alienwareItemProduct.setTitle(getString(R.string.activity_main_alienware_name));
        alienwareItemProduct.setDescription(getString(R.string.activity_main_alienware_description));
        alienwareItemProduct.setLocation(getString(R.string.activity_main_store_location));
        alienwareItemProduct.setStore(getString(R.string.activity_main_store_name));
        alienwareItemProduct.setPhone(getString(R.string.activity_main_store_phone));

        products.add(alienwareItemProduct);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterProduct(this, products);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_PRODUCT:
                if(resultCode == RESULT_OK){
                    adapter.updateElement((ItemProduct) data.getParcelableExtra("ITEM"));
                    adapter.notifyDataSetChanged();
                }
                break;

            default:
                break;
        }

    }
}
