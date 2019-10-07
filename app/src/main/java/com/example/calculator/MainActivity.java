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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView result,operation;
    private final ArrayList<String> trigonometricOps;
    private static String lastOperation = "";
    private Button inverse;

    public MainActivity()
    {
        this.trigonometricOps = new ArrayList<String>()
        {{
            add("sin");
            add("cos");
            add("tan");
        }};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);

        Button clean = findViewById(R.id.button_clean);
        inverse = findViewById(R.id.buttonInverse);

        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                CharSequence text = operation.getText();
                if(text==null || text.toString().isEmpty()) {return;}
                String oldTxt = text.toString();
                String newTxt = oldTxt.substring(0,oldTxt.length()-1);
                operation.setText(newTxt);
                result.setText(MathEvaluator.evaluate(newTxt));
            }
        });

        clean.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                result.setText("");
                operation.setText("");
                return true;
            }
        });

        checkIntent(getIntent());
    }

    /**
     * //https://beanshell.github.io/license.html
     */
    public void buttonClick(View v)
    {
        Button source = (Button)v;
        String key = source.getText().toString();
        String oldText = getOperation();
        String newOperation = oldText+key;
        String parenthesis = (trigonometricOps.contains(key))?"(":"";
        operation.setText(newOperation+parenthesis);
        result.setText(MathEvaluator.evaluate(newOperation));
    }

    public void invertNumber(View v)
    {
        String o = getOperation();
        if(o.isEmpty()) {return;}
        String newOp = "1/("+o+")";
        String r = MathEvaluator.evaluate(newOp);
        if(!r.isEmpty())
        {
            result.setText(r);
            operation.setText(newOp);
        }
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
        else if(item.getItemId() == R.id.settings)
        {
            lastOperation = getOperation();
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private String getOperation()
    {
        CharSequence curOperation = operation.getText();
        return (curOperation!=null)?curOperation.toString():"";
    }

    private void checkIntent(Intent intent)
    {
        if(intent!=null)
        {
            Bundle extras = intent.getExtras();
            if(extras==null) {return;}
            String source = extras.getString("SOURCE");
            if(source==null) {return;}
            if(source.equals("SETTINGS"))
            {
                operation.setText(lastOperation);
                result.setText(MathEvaluator.evaluate(lastOperation));
            }
        }
    }
}
