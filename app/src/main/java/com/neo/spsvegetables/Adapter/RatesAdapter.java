package com.neo.spsvegetables.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import com.neo.spsvegetables.Model.Ratesmodel;
import com.neo.spsvegetables.R;
import com.neo.spsvegetables.Rates;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.ManageViewHolder> implements Filterable, View.OnClickListener {

    private List<Ratesmodel> managelist;
    private List<Ratesmodel> managelistfull;
    Context mContext;
    View v,customLayout;

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= managelist.get(position);
        Ratesmodel dataModel=(Ratesmodel)object;
/*
        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                Log.e("fffffffffffffffffffff",""+dataModel.getDesignation());
                break;
        }*/
    }

    class ManageViewHolder extends RecyclerView.ViewHolder {
            TableLayout stk;

        ManageViewHolder(View itemView) {
            super(itemView);

            stk = itemView.findViewById(R.id.table_main);


        }
    }

    public RatesAdapter(List<Ratesmodel> manageList, Context context) {
        this.managelist = manageList;
        managelistfull = new ArrayList<>(manageList);
        this.mContext=context;
    }

    @NonNull
    @Override
    public ManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.managelist,
                parent, false);
        return new ManageViewHolder(v);
    }
    private int lastPosition =-1;
    @Override
    public void onBindViewHolder(@NonNull ManageViewHolder holder, int position) {

        holder.stk.removeAllViewsInLayout();


        final TableRow tbrow0 = new TableRow(mContext);
        TextView tv1 = new TextView(mContext);
        tv1.setText("           Items          ");
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(Gravity.CENTER);
        tv1.setBackgroundResource(R.drawable.cell_shape);
//            tv1.setBackgroundColor(getResources().getColor(R.color.md_green_900));
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);


        TextView tv2 = new TextView(mContext);
        tv2.setText("        Rate / Unit        ");
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setPadding(5, 5, 5, 5);
        tv2.setGravity(Gravity.CENTER);
        tv2.setBackgroundResource(R.drawable.cell_shape);
//            tv2.setBackgroundColor(getResources().getColor(R.color.red_dark));
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);


        TextView tv4 = new TextView(mContext);
        tv4.setText("        Market Price         ");
        tv4.setTypeface(null, Typeface.BOLD);
        tv4.setPadding(5, 5, 5, 5);
        tv4.setGravity(Gravity.CENTER);
        tv4.setBackgroundResource(R.drawable.cell_shape);
//            tv4.setBackgroundColor(getResources().getColor(R.color.yellow));
//      tv4  .setTextColor(Color.WHITE);
        tv4.setTextColor(Color.BLACK);
        tbrow0.addView(tv4);
        holder.stk.addView(tbrow0);

        final Ratesmodel currentItem = managelist.get(position);



     /*   Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        v.startAnimation(animation);
        lastPosition = position;*/




        TableRow tbrow = new TableRow(mContext);



        TextView t4v = new TextView(mContext);
        t4v.setText(""+currentItem.getProduct_name());
        t4v.setPadding(5, 5, 5, 5);
        t4v.setBackgroundResource(R.drawable.cell_shape);
        t4v.setTextColor(Color.BLACK);
        t4v.setGravity(Gravity.CENTER);
        tbrow.addView(t4v);

        TextView t2v = new TextView(mContext);
        t2v.setText(""+currentItem.getRate()+"/"+currentItem.getCode());
        t2v.setPadding(5, 5, 5, 5);
        t2v.setBackgroundResource(R.drawable.cell_shape);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        tbrow.addView(t2v);



        TextView t3v = new TextView(mContext);
        t3v.setText(""+currentItem.getMarket_price()+"/"+currentItem.getCode());
        t3v.setPadding(5, 5, 5, 5);
        t3v.setBackgroundResource(R.drawable.cell_shape);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        tbrow.addView(t3v);
        holder.stk.addView(tbrow);


      /*  holder.text_view4.setText(currentItem.getMarket_price());holder.textView1.setText(currentItem.getProduct_name());
        holder.textView2.setText(currentItem.getCode());*/
//        Picasso.get().load(currentItem.getPhoto()).networkPolicy(NetworkPolicy.NO_CACHE).into(holder.imageView);

     /*   holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showAlertDialogButtonClicked(v);

                Intent i=new Intent(mContext, Common_Profile.class);
//                Log.e("cccccccccccccccccccc",""+title);


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                i.putExtra("getId",""+currentItem.getId());
                i.putExtra("getName", ""+currentItem.getName());
                i.putExtra("getDob", ""+currentItem.getDob());
                i.putExtra("getDesignation", ""+currentItem.getDesignation());
                i.putExtra("getMembership_id", ""+currentItem.getMembership_id());
                i.putExtra("getMobile_no", ""+currentItem.getMobile_no());
                i.putExtra("getEmail_id", ""+currentItem.getEmail_id());
                i.putExtra("getAddress", ""+currentItem.getAddress());
                i.putExtra("getWedding_date", ""+currentItem.getWedding_date());
                i.putExtra("getBusiness_name", ""+currentItem.getBusiness_name());
                i.putExtra("getBusiness_keywords", ""+currentItem.getBusiness_keywords());
                i.putExtra("getBlood_group", ""+currentItem.getBlood_group());
                i.putExtra("getPhoto", ""+currentItem.getPhoto());
//                Toast.makeText(mContext, ""+currentItem.getPhoto(), Toast.LENGTH_SHORT).show();
                mContext.getApplicationContext().startActivity(i);


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return managelist.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Ratesmodel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(managelistfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Ratesmodel item : managelistfull) {
                    if (item.getProduct_name().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }else  if (item.getCode().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }else  if (item.getMarket_price().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            managelist.clear();
            managelist.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
