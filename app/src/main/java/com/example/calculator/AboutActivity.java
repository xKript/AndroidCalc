package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        * https://github.com/medyo/android-about-page
        * */
        super.onCreate(savedInstanceState);
        Element versionElement = new Element();
        versionElement.setTitle(getString(R.string.version)+" 1.1");
        View aboutPage = new AboutPage(AboutActivity.this)
                .setDescription(getString(R.string.description))
                .isRTL(false)
                .setImage(R.drawable.calculator_256)
                .addItem(versionElement)
                .addGroup(getString(R.string.connect_us))
                .addEmail("juanpablo495@hotmail.com")
                .addFacebook("juanpablolel")
                .addTwitter("almostHuman19")
                .addGitHub("xKript","JuanP")
                .addInstagram("juanpabloxt")
                .create();
        setContentView(aboutPage);
    }
}
