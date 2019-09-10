package com.example.cardwallet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.circularreveal.cardview.CircularRevealCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

class MyColorListAdapter extends RecyclerView.Adapter<MyColorListAdapter.ViewHolder> {

    private ColorAdapter[] colorAdapters;
    Context context;
    ArrayList<Boolean>  arrayList=new ArrayList();
    private List<ColorModel> colorlist = new ArrayList<>();

    public MyColorListAdapter(Context context) {

        this.context=context;

    }

    @NonNull
    @Override
    public MyColorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        final View listItem= LayoutInflater.from(context).inflate(R.layout.color_item_list_for_display_card, viewGroup, false);

        return new ViewHolder(listItem);

    }

    @Override
    public int getItemCount() {
        return colorlist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyColorListAdapter.ViewHolder viewHolder, final int position) {


        System.out.println("---clor"+colorlist.get(position).getColor());


        viewHolder.imageView.setBackgroundColor(Color.parseColor((colorlist.get(position).getColor())));


        if (!colorlist.get(position).isT()){

            viewHolder.setselect_or_unselect.setVisibility(View.GONE);
            System.out.println("---logs"+colorlist.get(position).isT());
        }
        else {


            viewHolder.setselect_or_unselect.setVisibility(View.VISIBLE);
            System.out.println("---logs"+colorlist.get(position).isT());
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DisplayCardActivity)context).onclick(position);



            }
        });




    }

    public void AddAll(List<ColorModel> colorlist) {
        try{
            this.colorlist.clear();
            this.colorlist.addAll(colorlist);
            notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


         CircularRevealCardView imageView;
         ImageView setselect_or_unselect;
         FrameLayout frameLayout;


        public ViewHolder(final View itemView) {
            super(itemView);
            this.imageView =itemView.findViewById(R.id.color_list_item);
            this.setselect_or_unselect=itemView.findViewById(R.id.setselect_or_unselect);
            this.frameLayout=itemView.findViewById(R.id.framelayout_for_clr_data);


        }

    }
}
