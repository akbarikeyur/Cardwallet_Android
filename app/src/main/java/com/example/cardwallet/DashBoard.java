package com.example.cardwallet;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    private String[] mNavigationDrawerItemTitles;
    public DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    public FloatingActionButton button;
    ArrayList<ModelDashBoard> modelDashBoards;
    ListView cardlistView;
    CardView cardView;
    LinearLayout show_card_view_in_activity;
    Button remove_card_btn,edit_card_btn;
    public ImageView back_btn_image,share_btn_image;
    TextView company_name,company_address,company_owenr_name,company_contract,company_card_Category,company_card_email;
    ImageView company_logo;
    CardView card_view_edit_delete;
    RelativeLayout rlt_for_add_nm_and_num;
    RelativeLayout com_logo_and_name_txt;
    boolean flag=false;
    boolean swap_favorite_icon=false;
    ObjectAnimator oa1,oa2;
    ImageView favorite_icon_process;
   public static Bitmap createimageforlogoname,createimageforemailadd,final_combine_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        findallids();

        mTitle = mDrawerTitle = getTitle();

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_item);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[5];

        drawerItem[0] = new DataModel(R.drawable.favorite_off, "Home");
        drawerItem[1] = new DataModel(R.drawable.favorite_on, "Add Card");
        drawerItem[2] = new DataModel(R.drawable.favorite_off, "My Card");
        drawerItem[3] = new DataModel(R.drawable.favorite_on,"Favorite Card");
        drawerItem[4] = new DataModel(R.drawable.favorite_off,"Log Out");
                getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        //add any other card holder
        button=findViewById(R.id.add_btn_for_new_cust);
        button.setOnClickListener(this);

        modelDashBoards=new ArrayList<>();
        modelDashBoards.add(new ModelDashBoard("Suverna Palace Jwels",this.getResources().getString(R.string._33_34_harekrishna_society_opp_vandna_society_shyamdham_road_nana_varacha_surat_395006),"Soni","548454248",R.drawable.favorite_on,"yogu@gmail.com","shopping"));
        modelDashBoards.add(new ModelDashBoard("Intex",this.getResources().getString(R.string._33_34_harekrishna_society_opp_vandna_society_shyamdham_road_nana_varacha_surat_395006),"S Shah","54588565756",R.drawable.favorite_on,"chirag@gmail.com","E Commerce"));
        modelDashBoards.add(new ModelDashBoard("Flower Nursury",this.getResources().getString(R.string._33_34_harekrishna_society_opp_vandna_society_shyamdham_road_nana_varacha_surat_395006),"yogesh","58456587654",R.drawable.favorite_on,"rahul@gmail.com","devlop"));


        init();


    }

    private void init() {

        CardListAdapter cardListAdapter=new CardListAdapter(this,modelDashBoards);
        cardlistView.setAdapter(cardListAdapter);

    }

    public void show_and_edit_card(final String com_name, final String comp_address, final String comp_owner_name, String comp_contact, int com_logo, String email, String category)
    {


        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        show_card_view_in_activity.setAnimation(aniFade);

        final NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setVisibility(View.INVISIBLE);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //set Data into Card_view
        company_name.setText(com_name);
        company_logo.setImageResource(com_logo);
        company_address.setText(comp_address);
        company_owenr_name.setText(comp_owner_name);
        company_contract.setText(comp_contact);
        company_card_email.setText(email);
        company_card_Category.setText(category);

        rlt_for_add_nm_and_num=findViewById(R.id.rlt_for_add_nm_and_num);
        com_logo_and_name_txt=findViewById(R.id.comp_logo_and_name_img_txt);

        com_logo_and_name_txt.setVisibility(View.VISIBLE);
        rlt_for_add_nm_and_num.setVisibility(View.GONE);

     //   rlt_for_add_nm_and_num.setVisibility(View.GONE);


        show_card_view_in_activity.setVisibility(View.VISIBLE);
        //for back screen no touchable
        show_card_view_in_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    }});


        //back btn click listner here
        back_btn_image.setOnClickListener(this);
        //card view click listner here
        card_view_edit_delete.setOnClickListener(this);

        favorite_icon_process.setOnClickListener(this);

        share_btn_image.setOnClickListener(this);

        edit_card_btn.setOnClickListener(this);

    }

    private void setanim(final ObjectAnimator oa1, final ObjectAnimator oa2, final boolean flag) {


        oa2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                System.out.println("------first_call");
                if (!flag){
                    System.out.println("!------flag ");
                    com_logo_and_name_txt.setVisibility(View.GONE);
                    rlt_for_add_nm_and_num.setVisibility(View.VISIBLE);
                    System.out.println("--flag"+flag);
                }
                else {
                    System.out.println("-----flag");
                    com_logo_and_name_txt.setVisibility(View.VISIBLE);
                    rlt_for_add_nm_and_num.setVisibility(View.GONE);
                    System.out.println("----flag"+flag);

                }
            }
        });

        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                System.out.println("-----set disable ");
                if (!flag){
                    com_logo_and_name_txt.setVisibility(View.VISIBLE);
                    rlt_for_add_nm_and_num.setVisibility(View.GONE);
                    System.out.println("=====first Clicked/.....");
                }
                else {
                    com_logo_and_name_txt.setVisibility(View.GONE);
                    rlt_for_add_nm_and_num.setVisibility(View.VISIBLE);
                    System.out.println("=====first Clicked/.....");
                }
                oa2.start();


            }
        });
        oa1.start();

    }


    private void findallids() {

        cardlistView=findViewById(R.id.lv_view_for_cards);
        show_card_view_in_activity=findViewById(R.id.show_card_layout);
        remove_card_btn=findViewById(R.id.remove_card);
        edit_card_btn=findViewById(R.id.edit_Card);
        back_btn_image=findViewById(R.id.back_to_dash_board);
        share_btn_image=findViewById(R.id.share_card);
        card_view_edit_delete=findViewById(R.id.card_view_edit_delete);
        company_name=findViewById(R.id.card_company_name_edt_dlt);
        company_address=findViewById(R.id.card_company_address_edt_dlt);
        company_owenr_name=findViewById(R.id.card_owner_name_edt_dlt);
        company_contract=findViewById(R.id.card_company_contact_edt_dlt);
        company_logo=findViewById(R.id.company_logo_edit_delete);
        favorite_icon_process=findViewById(R.id.favorite_btn_card_details);

        company_card_Category=findViewById(R.id.card_category);
        company_card_email=findViewById(R.id.card_company_email_edt_dlt);


    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_btn_for_new_cust:
                Intent intent=new Intent(DashBoard.this,AddCardActivity.class);
                startActivity(intent);
                break;

            case R.id.back_to_dash_board:
                show_card_view_in_activity.setVisibility(View.GONE);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

                // com_logo_and_name_txt.setVisibility(View.VISIBLE);
                flag=false;
                swap_favorite_icon=false;

                break;

            case R.id.card_view_edit_delete:
                oa1=ObjectAnimator.ofFloat(card_view_edit_delete, "scaleX", 1f, 0f);
                oa2 = ObjectAnimator.ofFloat(card_view_edit_delete, "scaleX", 0f, 1f);

                oa1.setDuration(400);
                oa2.setDuration(400);

                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());

                if (!flag) {

                    setanim(oa1, oa2, flag);
                    flag=true;
                }
                else {
                    setanim(oa1, oa2, flag);
                    flag=false;
                }

                break;

            case R.id.share_card:


                if (com_logo_and_name_txt.getVisibility()==View.VISIBLE) {
                    createimageforlogoname = getBitmapFromView(card_view_edit_delete);
                    System.out.println("----logo--an---nmae"+createimageforlogoname);

                    com_logo_and_name_txt.setVisibility(View.GONE);
                    rlt_for_add_nm_and_num.setVisibility(View.VISIBLE);

                    System.out.println("---visibility..."+com_logo_and_name_txt.getVisibility());
                    System.out.println("---visibility..."+rlt_for_add_nm_and_num.getVisibility());

                    createimageforemailadd = getBitmapFromView(card_view_edit_delete);

                    System.out.println("------email--"+createimageforemailadd);
                    System.out.println("---email....and....name"+createimageforemailadd);
                    com_logo_and_name_txt.setVisibility(View.VISIBLE);
                    rlt_for_add_nm_and_num.setVisibility(View.GONE);
                }

//                if (rlt_for_add_nm_and_num.getVisibility()==View.GONE)
//                {
//                    com_logo_and_name_txt.setVisibility(View.GONE);
//                    rlt_for_add_nm_and_num.setVisibility(View.VISIBLE);
//                    createimageforemailadd = getBitmapFromView(card_view_edit_delete);
//                    com_logo_and_name_txt.setVisibility(View.VISIBLE);
//                    rlt_for_add_nm_and_num.setVisibility(View.GONE);
//
//
//
//                }



//                else {
//                    com_logo_and_name_txt.setVisibility(View.VISIBLE);
//                    rlt_for_add_nm_and_num.setVisibility(View.GONE);
//                    createimageforlogoname = getBitmapFromView(card_view_edit_delete);
//                    com_logo_and_name_txt.setVisibility(View.GONE);
//                    rlt_for_add_nm_and_num.setVisibility(View.VISIBLE);
//                    createimageforemailadd = getBitmapFromView(card_view_edit_delete);
//                    com_logo_and_name_txt.setVisibility(View.VISIBLE);
//                    rlt_for_add_nm_and_num.setVisibility(View.GONE);
//                }


                System.out.println("----"+createimageforemailadd);
                System.out.println("----"+createimageforlogoname);


                 final_combine_image=finalcombieimage(createimageforlogoname,createimageforemailadd);
                System.out.println("=------"+final_combine_image);
                 showPictureDialog();

//
//                Intent intent1=new Intent(this,FavoriteCardActivity.class);
//                startActivity(intent1);



                break;

            case R.id.favorite_btn_card_details:

                System.out.println("----favorite btn clicked");

                if (!swap_favorite_icon) {

                    System.out.println("------set on btn");
                    favorite_icon_process.setImageResource(R.drawable.favorite_on);
                    swap_favorite_icon=true;
                    System.out.println("----"+swap_favorite_icon);


                }
                else
                {
                    System.out.println("=-------set fav off");
                    favorite_icon_process.setImageResource(R.drawable.favorite_off);
                    swap_favorite_icon=false;

                }

                break;

            case R.id.edit_Card:

                Intent editactivitytoadd=new Intent(DashBoard.this,AddCardActivity.class);

                editactivitytoadd.putExtra("company_name_for_edit",company_name.getText());
                editactivitytoadd.putExtra("company_owner_name__for_edit",company_owenr_name.getText());
                editactivitytoadd.putExtra("company_contact_edit",company_contract.getText());
                editactivitytoadd.putExtra("comapny_address_edit",company_address.getText());
                editactivitytoadd.putExtra("company_email_edit",company_card_email.getText());
                editactivitytoadd.putExtra("company_category_edit",company_card_Category.getText());

                System.out.println("======-------"+company_name.getText());

                startActivity(editactivitytoadd);

        }
    }


    //method for share any card from share button.................
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(DashBoard.this);
        pictureDialog.setTitle("Select Action");

        String[] pictureDialogItems = {
                "Internal Share",
                "External Share"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                             //   internalshare();
                                break;
                            case 1:
                                 ExternalShare();
                                break;
                        }
                    }

                    private void ExternalShare() {

                        Intent intent = new Intent();
                       // intent.putExtra(Intent.EXTRA_STREAM, final_combine_image);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        final_combine_image.compress(Bitmap.CompressFormat.PNG, 100,stream );
                        byte[] byteArray = stream.toByteArray();
                        intent.putExtra(Intent.ACTION_SEND, byteArray);

                        startActivity(Intent.createChooser(intent, "Share image using"));


                    }
                });
        pictureDialog.show();

    }

    private void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();

    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }



    //Inner class for draweritemclicklistner for click any item in drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {

            switch (position) {

                //any select option then here process
                case 0:

                    mDrawerLayout.closeDrawers();
                    break;

                case 1:
                    Intent intent=new Intent(DashBoard.this,AddCardActivity.class);
                    mDrawerLayout.closeDrawers();
                    startActivity(intent);

                    break;
                case 2:
                    Intent intent2=new Intent(DashBoard.this,MyCardActivity.class);
                    mDrawerLayout.closeDrawers();
                    startActivity(intent2);

                    break;

                case 3:

                    Intent intent3=new Intent(DashBoard.this,FavoriteCardActivity.class);
                    mDrawerLayout.closeDrawers();
                    startActivity(intent3);

                    break;

                case 4:


                    break;

                default:
                    break;

            }


        }


    }

    @Override
    public void onBackPressed() {


        if (show_card_view_in_activity.getVisibility()==View.VISIBLE)
        {
            show_card_view_in_activity.setVisibility(View.GONE);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            flag=false;
            swap_favorite_icon=false;
        }
        else {
            flag=false;
            swap_favorite_icon=false;
            finish();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public Bitmap finalcombieimage(Bitmap c, Bitmap s) {
        Bitmap cs = null;
        DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        Rect dest1 = new Rect(0, 0, width, height/4); // left,top,right,bottom
        comboImage.drawBitmap(c, null, dest1, null);
        Rect dest2 = new Rect(0, width/2, width, height/2);
        comboImage.drawBitmap(s, null, dest2, null);
        return cs;
    }

}
