package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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
        Button about = findViewById(R.id.button_about);

        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                result.setText("");
                operation.setText("");
            }
        });

        about.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
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
            result.setText(interpreter.get("res").toString());
        } catch (EvalError evalError) {}
    }
}
