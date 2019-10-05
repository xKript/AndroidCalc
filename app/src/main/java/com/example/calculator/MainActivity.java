package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

import bsh.EvalError;
import bsh.Interpreter;


public class MainActivity extends AppCompatActivity {
    private TextView result,operation;
    private final Interpreter interpreter;
    private View aboutPage;

    public MainActivity()
    {
        this.interpreter = new Interpreter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);

        Button clean = findViewById(R.id.button_clean);

        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                result.setText("");
                operation.setText("");
            }
        });
    }

    /**
     * //https://beanshell.github.io/license.html
     */
    public void buttonClick(View v)
    {
        Button source = (Button)v;
        String key = source.getText().toString();
        CharSequence oldContent = operation.getText();
        String oldText=(oldContent!=null)?oldContent.toString():"";
        String newOperation = oldText+key;
        operation.setText(newOperation);
        try
        {
            interpreter.eval("res = "+newOperation);
            BigDecimal i = new BigDecimal(interpreter.get("res").toString());
            i.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            result.setText(i.toPlainString());
        } catch (EvalError evalError) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.about)
        {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
