package com.iteso.tarea4_parcelable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.iteso.tarea4_parcelable.beans.ItemProduct;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    //new array of products
    private ArrayList<ItemProduct> products;
    private Context context;

    public AdapterProduct(Context context, ArrayList<ItemProduct> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    void updateElement(ItemProduct itemProduct){
        for (ItemProduct existingItem : products){
            if(existingItem.getCode() == itemProduct.getCode()) {
                products.set(products.indexOf(existingItem), itemProduct);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //when creation a ViewHolder, get the elements from the layout
        private ImageView image;
        private TextView title;
        private TextView store_phone;
        private TextView store_name;
        private TextView store_location;
        private CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = v.findViewById(R.id.card_view);
            image = v.findViewById(R.id.item_product_image);
            title = v.findViewById(R.id.item_product_title);
            store_name = v.findViewById(R.id.item_product_store);
            store_phone = v.findViewById(R.id.item_product_phone);
            store_location = v.findViewById(R.id.item_product_location);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        //When doing the bind View Holder, set the elements, according to the products arraylist
        holder.title.setText(products.get(position).getTitle());
        holder.store_name.setText(products.get(position).getStore());
        holder.store_phone.setText(products.get(position).getPhone());
        holder.store_location.setText(products.get(position).getLocation());
        switch (products.get(position).getImage()){
            case 0:
                holder.image.setImageResource(R.drawable.mac);
                break;
            case 1:
                holder.image.setImageResource(R.drawable.alienware);
                break;
        }

        //set on click listeners
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityProduct.class);
                intent.putExtra("ITEM", products.get(position));
                ((ActivityMain) context).startActivityForResult(intent, ActivityMain.CHANGE_PRODUCT);
            }
        });

        holder.store_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //try to dial store's phone
                Intent intent;
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + products.get(position).getPhone()));

                try{
                    context.startActivity(intent);
                } catch (SecurityException e ){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
