package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button applyButton = findViewById(R.id.apply);
        final TextView decimalPlaces = findViewById(R.id.tv_presicion);

        applyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CharSequence text = decimalPlaces.getText();
                if(text == null || text.toString().isEmpty())
                {
                    return;
                }
                int precision = Integer.parseInt(text.toString());
                if(precision>=0 && precision<=24)
                {
                    MathEvaluator.setPrecision(precision);
                    Toast.makeText(SettingsActivity.this,R.string.settings_applied,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
                    intent.putExtra("SOURCE","SETTINGS");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SettingsActivity.this,R.string.precision_out_range,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
