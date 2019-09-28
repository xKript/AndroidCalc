package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bsh.EvalError;
import bsh.Interpreter;


public class MainActivity extends AppCompatActivity {

    private TextView result;
    private float op1, op2,res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        Button clean = findViewById(R.id.button_clean);
        final Button about = findViewById(R.id.button_about);

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,about.class);
                startActivity(intent);

            }
        });


    }

    /**
     * //https://beanshell.github.io/license.html
     * @param s
     */
    private void calculate(String s)
    {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.eval("result = "+s);
            result.setText(interpreter.get("result").toString());
        } catch (EvalError evalError) {
            Toast.makeText(this,"Error: "+evalError.getMessage(),Toast.LENGTH_SHORT).show();
            evalError.printStackTrace();
        }
    }

    public void buttonClick(View v)
    {
        Button source = (Button)v;
        String key = source.getText().toString();

        CharSequence oldContent = result.getText();
        String oldText="";
        if(oldContent!=null)
        {
            oldText = oldContent.toString();
        }

        if(key.equals("="))
        {
            calculate(oldText);
            return;
        }

        result.setText(oldText+key);
    }
}
