package com.example.cardwallet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayCardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    CardView cardView;
    private List<ColorModel> colorlist = new ArrayList<>();
    private MyColorListAdapter myColorListAdapter;

    TextView company_name_for_display_card,company_category_for_diaplay_card,
            comapny_owner_name_for_display_card,company_address_for_display_card,comapny_email_for_display_card,comapny_number;

    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card);

        findallidsfromdisplaycard();

        clearcolor();


        ColorAdapter[] colorAdapter=new ColorAdapter[]{new ColorAdapter(getResources().getColor(R.color.alizarin)),
                new ColorAdapter(getResources().getColor(R.color.amethyst)),
                new ColorAdapter(getResources().getColor(R.color.belize_hole)),
                new ColorAdapter(getResources().getColor(R.color.carrot)),
                new ColorAdapter(getResources().getColor(R.color.clouds)),
                new ColorAdapter(getResources().getColor(R.color.emerald)),
                new ColorAdapter(getResources().getColor(R.color.emerald)),
                new ColorAdapter(getResources().getColor(R.color.green_sea)),
                new ColorAdapter(getResources().getColor(R.color.midnight_blue)),
                new ColorAdapter(getResources().getColor(R.color.nephritis)),
                new ColorAdapter(getResources().getColor(R.color.peter_river)),
                new ColorAdapter(getResources().getColor(R.color.sun_flowers)),
                new ColorAdapter(getResources().getColor(R.color.turquoise)),
                new ColorAdapter(getResources().getColor(R.color.wet_asphalt)),
                new ColorAdapter(getResources().getColor(R.color.wisteria))};

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_for_color_palete);

     //   myColorListAdapter=new MyColorListAdapter(colorlist);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        myColorListAdapter =new MyColorListAdapter(this);
        myColorListAdapter.AddAll(colorlist);

       // MyColorListAdapter adapter = new MyColorListAdapter(DisplayCardActivity.this,colorAdapter);

        recyclerView.setAdapter(myColorListAdapter);

        //set data into card view....

        Intent intent=getIntent();
        company_name_for_display_card.setText(intent.getStringExtra("company_name"));
        company_address_for_display_card.setText(intent.getStringExtra("company_address"));
       comapny_owner_name_for_display_card.setText( intent.getStringExtra("comapny_owner_name"));
        comapny_number.setText(intent.getStringExtra("company_contact_number"));
        logo.setImageResource(intent.getIntExtra("company_image",0));
//        intent.getStringExtra("company_email");
//        intent.getStringExtra("company_address");















    }
    private void findallidsfromdisplaycard() {

        cardView=findViewById(R.id.card_view_for_display_card);
        company_name_for_display_card=findViewById(R.id.card_company_name_for_display_card);
        company_address_for_display_card=findViewById(R.id.card_company_address_for_display_card);
        comapny_owner_name_for_display_card=findViewById(R.id.card_owner_name_for_display_card);
        comapny_number=findViewById(R.id.card_company_contact_for_display_card);
        logo=findViewById(R.id.company_logo_for_display_card);








    }

    public void setcolorintocard(int i)
    {
        cardView.setCardBackgroundColor(i);



    }


    public void onclick(int position){

        colorlist.clear();
        clearcolor();

       String color= colorlist.get(position).getColor();
        colorlist.remove(position);
        colorlist.add(position,new ColorModel(color,true));

        cardView.setCardBackgroundColor(Color.parseColor(colorlist.get(position).getColor()));


        System.out.println("---logs");

        myColorListAdapter.AddAll(colorlist);


    }

    public void clearcolor()
    {
        colorlist.add(new ColorModel("#2471A3",false));
        colorlist.add(new ColorModel("#C16B28",false));
        colorlist.add(new ColorModel("#90C128",false));
        colorlist.add(new ColorModel("#287EC1",false));
        colorlist.add(new ColorModel("#F52B2B",false));
        colorlist.add(new ColorModel("#715AE4",false));
        colorlist.add(new ColorModel("#C1A728",false));
        colorlist.add(new ColorModel("#DA2BF5",false));
        colorlist.add(new ColorModel("#F52B9C",false));
        colorlist.add(new ColorModel("#285DC1",false));
        colorlist.add(new ColorModel("#BDA00D",false));
        colorlist.add(new ColorModel("#20AC8C",false));
        colorlist.add(new ColorModel("#C12828",false));


    }



















//    private void coloradd() {
//
//        ColorAdapter colorAdapter1new =new ColorAdapter(getResources().getColor(R.color.alizarin));
//        colorlist.add(colorAdapter1new);
//                ColorAdapter colorAdapter=new ColorAdapter(getResources().getColor(R.color.amethyst));
//                colorlist.add(colorAdapter);
//               ColorAdapter colorAdapter1= new ColorAdapter(getResources().getColor(R.color.belize_hole));
//               colorlist.add(colorAdapter1);
//               ColorAdapter colorAdapter2= new ColorAdapter(getResources().getColor(R.color.carrot));
//               colorlist.add(colorAdapter2);
//               ColorAdapter colorAdapter3= new ColorAdapter(getResources().getColor(R.color.clouds));
//        colorlist.add(colorAdapter3);
//               ColorAdapter colorAdapter4= new ColorAdapter(getResources().getColor(R.color.emerald));
//        colorlist.add(colorAdapter4);
//               ColorAdapter colorAdapter5= new ColorAdapter(getResources().getColor(R.color.emerald));
//        colorlist.add(colorAdapter5);
//               ColorAdapter colorAdapter6= new ColorAdapter(getResources().getColor(R.color.green_sea));
//        colorlist.add(colorAdapter6);
//               ColorAdapter colorAdapter7=new ColorAdapter(getResources().getColor(R.color.midnight_blue));
//        colorlist.add(colorAdapter7);
//               ColorAdapter colorAdapter8= new ColorAdapter(getResources().getColor(R.color.nephritis));
//        colorlist.add(colorAdapter8);
//               ColorAdapter colorAdapter9= new ColorAdapter(getResources().getColor(R.color.peter_river));
//        colorlist.add(colorAdapter9);
//               ColorAdapter colorAdapter10= new ColorAdapter(getResources().getColor(R.color.sun_flowers));
//        colorlist.add(colorAdapter10);
//               ColorAdapter colorAdapter11= new ColorAdapter(getResources().getColor(R.color.turquoise));
//        colorlist.add(colorAdapter11);
//               ColorAdapter colorAdapter12= new ColorAdapter(getResources().getColor(R.color.wet_asphalt));
//        colorlist.add(colorAdapter12);
//               ColorAdapter colorAdapter13= new ColorAdapter(getResources().getColor(R.color.wisteria));
//        colorlist.add(colorAdapter13);
//
//        System.out.println("--------"+colorlist.size());
//
//        myColorListAdapter.notifyDataSetChanged();
//
//
//
//    }







}
