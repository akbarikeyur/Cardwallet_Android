package com.example.cardwallet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class CardListAdapter extends BaseAdapter {

Context context;
private LayoutInflater layoutInflater=null;
ArrayList<ModelDashBoard> modeldash;



    public CardListAdapter(DashBoard dashBoard, ArrayList<ModelDashBoard> modelDashBoards) {

        context=dashBoard;
        modeldash=modelDashBoards;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return modeldash.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class Holder
    {

        TextView comapany_name,company_address,company_owner,company_contact;
        ImageView company_logo;
        Layout show_card_view;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();

        //inflate layout for showing card into list view
        convertView = layoutInflater.inflate(R.layout.layout_cards_list_item,null);
          holder.comapany_name =convertView.findViewById(R.id.card_company_name);
          holder.company_address=convertView.findViewById(R.id.card_company_address);
          holder.company_owner=convertView.findViewById(R.id.card_owner_name);
          holder.company_contact=convertView.findViewById(R.id.card_company_contact);
          holder.company_logo=convertView.findViewById(R.id.company_logo);



          //set background in card view
          CardView cardView=convertView.findViewById(R.id.card_view);
          cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_view_clr));

        System.out.println("====="+modeldash.get(0).getCompany_address());
        System.out.println("====="+modeldash.get(1).getCompany_name());
        System.out.println("====="+modeldash.get(2).getContact_number());


        //set data into card view in list view
        holder.comapany_name.setText(modeldash.get(position).getCompany_name());
        holder.company_address.setText(modeldash.get(position).getCompany_address());
        holder.company_owner.setText(modeldash.get(position).getName());
        holder.company_contact.setText(modeldash.get(position).getContact_number());
        holder.company_logo.setImageResource(modeldash.get(position).getLogo());


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                //set data into
                if(context instanceof DashBoard){
                    ((DashBoard)context).show_and_edit_card(modeldash.get(position).getCompany_name(),
                            modeldash.get(position).getCompany_address(),modeldash.get(position).getName(),modeldash.get(position).getContact_number(),
                            modeldash.get(position).getLogo(),modeldash.get(position).getEmail(),modeldash.get(position).getCategory());
                }






               // show second activiy for card show....

//                Intent intent=new Intent(context,ShowCardActivity.class);

//                intent.putExtra(modeldash.get(position).getCompany_name(),"company_name");
//                intent.putExtra(modeldash.get(position).getCompany_address(),"company_address");
//                intent.putExtra(modeldash.get(position).getName(),"company_owner_name");
//                intent.putExtra(modeldash.get(position).getContact_number(),"company_number");
//                intent.putExtra(String.valueOf(modeldash.get(position).getLogo()),"company_logo");

//                context.startActivity(intent);

                //toast for click any card...
                Toast.makeText(context, "Selected Card is"+position, Toast.LENGTH_SHORT).show();

                return false;

            }
        });

        return convertView;
    }
}
