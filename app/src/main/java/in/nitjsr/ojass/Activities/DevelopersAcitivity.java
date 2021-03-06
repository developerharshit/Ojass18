package in.nitjsr.ojass.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import in.nitjsr.ojass.Adapters.DevRVAdapter;
import in.nitjsr.ojass.R;
import in.nitjsr.ojass.Utils.Constants;
import in.nitjsr.ojass.Utils.Utilities;

public class DevelopersAcitivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView tvName, tvDesc, tvEmail;
    private static ImageView ivImg;
    private static String linkedIn, fb, github, whatsApp;
    private static Context context;
    private static final String abhiImage = "http://www.ojass.in/app/Images/Dev/ahishek.jpg";
    private static final String abhiFb = "https://www.facebook.com/ak47gyani";
    private static final String abhiLinkedIn = "https://www.linkedin.com/in/abhishek-kumar-1706/";
    private static final String abhiGithub = "https://github.com/abigyani";
    private static final String abhiwhatsAp = "7858030449";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers_acitivity);

        Utilities.changeStatusBarColor(this);
        Picasso.with(this).load(R.drawable.menu_bg).fit().into(((ImageView)findViewById(R.id.iv_dev_bg)));
        Picasso.with(this).load(R.drawable.ojass_bg).fit().into(((ImageView)findViewById(R.id.iv_dev_ojass_bg)));

        findViewById(R.id.dev_linked_in).setOnClickListener(this);
        findViewById(R.id.dev_fb).setOnClickListener(this);
        findViewById(R.id.dev_github).setOnClickListener(this);
        findViewById(R.id.dev_whats_app).setOnClickListener(this);
        findViewById(R.id.ib_back_dev).setOnClickListener(this);

        tvName = findViewById(R.id.tv_dev_name);
        tvDesc = findViewById(R.id.tv_dev_desc);
        tvEmail = findViewById(R.id.tv_dev_email);
        ivImg = findViewById(R.id.iv_dev_img);

        context = this;

        setInitialValue();

        prepareRecyclerView();
    }

    private void setInitialValue() {
        tvName.setText("Abhishek Kumar");
        tvDesc.setText("Computer Science & Engg.\nB.Tech. (Hons.)\n2014-2018");
        tvEmail.setText("abhishek1706@hotmail.com");
        Utilities.setPicassoImage(this, abhiImage, ivImg, Constants.SQUA_PLACEHOLDER);
        fb = abhiFb;
        linkedIn = abhiLinkedIn;
        github = abhiGithub;
        whatsApp = abhiwhatsAp;
    }

    private void prepareRecyclerView() {
        RecyclerView rv = findViewById(R.id.rv_dev);
        LinearLayoutManager ll = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(ll);
        rv.setHasFixedSize(true);
        rv.setAdapter(new DevRVAdapter(this));
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.dev_linked_in:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedIn));
                startActivity(intent);
                break;
            case R.id.dev_fb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fb));
                startActivity(intent);
                break;
            case R.id.dev_github:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(github));
                startActivity(intent);
                break;
            case R.id.dev_whats_app:
                String userName = ""+ FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=91" + whatsApp + "&text=Hey! I'm "+userName+".";
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.ib_back_dev:
                onBackPressed();
        }
    }

    public static void setDevData(String image, String name, String email, String desc, String linkedInLink,
                                  String fbLink, String githubLink, String whatsAppPhone) {
        Utilities.setPicassoImage(context, image, ivImg, Constants.SQUA_PLACEHOLDER);
        Picasso.with(context).load(image).placeholder(R.drawable.placeholder_sqaure).fit().into(ivImg);
        tvName.setText(name);
        tvEmail.setText(email);
        tvDesc.setText(desc);
        linkedIn = linkedInLink;
        fb = fbLink;
        github = githubLink;
        whatsApp = whatsAppPhone;
    }
}
