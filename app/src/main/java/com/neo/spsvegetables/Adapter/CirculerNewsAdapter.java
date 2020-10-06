package com.neo.spsvegetables.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.neo.spsvegetables.Model.Newscirculer;
import com.neo.spsvegetables.R;
import com.neo.spsvegetables.Viewcirculer;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CirculerNewsAdapter extends RecyclerView.Adapter<CirculerNewsAdapter.ProductViewHolder> {

    View view;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Newscirculer> newsList;
    Newscirculer news;
    String title;
    String subtitle;
    String date;
    String link;
    String image;

    //getting the context and product list with constructor
    public CirculerNewsAdapter(Context mCtx, List<Newscirculer> newsList) {
        this.mCtx = mCtx;
        this.newsList = newsList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        view = inflater.inflate(R.layout.newslistview, null);

        return new ProductViewHolder(view);
    }
    private int lastPosition =-1;
    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        news = newsList.get(position);


        Animation animation = AnimationUtils.loadAnimation(mCtx, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        view.startAnimation(animation);
        lastPosition = position;
        //binding the data with the viewholder views

        holder.textViewTitle.setText(news.getTitle());
        holder.textViewShortDesc.setText(news.getMessage());
        holder.textViewRating.setText(news.getDate());
        holder.id.setText(news.getHtml_link());
        holder.time.setText("\u20B9"+news.getHtml_tittle());
        holder.venue.setText(news.getMessage());
//        holder.color.setBackgroundColor(Integer.parseInt(news.getColor()));
//        holder.imageeye.setImageDrawable(mCtx.getResources().getDrawable(news.getImageeye()));

     Picasso.get().load("https://neophrontech.com/spsvegetables.com/"+news.getImage()).networkPolicy(NetworkPolicy.NO_CACHE).into(holder.imageView);
//       Picasso.get().load(""+news.getImage()).into(holder.imageView);
        Log.e("fggtgrtgrtgftbc",""+news.getImage());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news = newsList.get(position);
                Intent i=new Intent(mCtx, Viewcirculer.class);
                Log.e("cccccccccccccccccccc",""+title);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                i.putExtra("student_title",""+news.getTitle());
                i.putExtra("subtitle", ""+news.getMessage());
                i.putExtra("date", ""+news.getDate());
                i.putExtra("link", ""+news.getHtml_link());
                i.putExtra("image", "https://neophrontech.com/spsvegetables.com/"+news.getImage());
                i.putExtra("time", ""+news.getHtml_tittle());
                i.putExtra("location", ""+news.getTitle());

//                Toast.makeText(mCtx, ""+news.getImage(), Toast.LENGTH_SHORT).show();
                mCtx.getApplicationContext().startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, id,time,venue,color;
        ImageView imageView,imageeye;
        CardView card;


        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.date);
            id = itemView.findViewById(R.id.id);
            imageView = itemView.findViewById(R.id.imageView);
            imageeye = itemView.findViewById(R.id.eye);
            time=itemView.findViewById(R.id.time);
            venue=itemView.findViewById(R.id.locationtext);
            color=itemView.findViewById(R.id.color);
           card=itemView.findViewById(R.id.card);
        }


    }
}
