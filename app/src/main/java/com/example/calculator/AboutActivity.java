package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element versionElement = new Element();
        versionElement.setTitle(getString(R.string.version)+" 0.1");
        View aboutPage = new AboutPage(AboutActivity.this)
                .setDescription(getString(R.string.description))
                .isRTL(false)
                .setImage(R.drawable.about_icon_facebook)
                .addItem(versionElement)
                .addGroup(getString(R.string.connect_us))
                .addEmail("juanpablobc495@gmail.com")
                .addFacebook("juanpablo")
                .addTwitter("almostHuman19")
                .addGitHub("xKript","JuanP")
                .addInstagram("juanpabloxt")
                .create();
        setContentView(aboutPage);
    }
}
