package com.example.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView  solution_tv,result_tv;
    MaterialButton button_c, button_open_bracket,button_close_bracket;
    MaterialButton button_plus,button_minus,button_multiply,button_divide,button_equal;
    MaterialButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;
    MaterialButton button_ac, button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);

        assign(button_c,R.id.button_c);
        assign(button_open_bracket,R.id.button_open_bracket);
        assign(button_close_bracket,R.id.button_close_bracket);
        assign(button_plus,R.id.button_plus);
        assign(button_minus,R.id.button_minus);
        assign(button_multiply,R.id.button_multiply);
        assign(button_divide,R.id.button_divide);
        assign(button_equal,R.id.button_equal);
        assign(button_1,R.id.button_1);
        assign(button_2,R.id.button_2);
        assign(button_3,R.id.button_3);
        assign(button_4,R.id.button_4);
        assign(button_5,R.id.button_5);
        assign(button_6,R.id.button_6);
        assign(button_7,R.id.button_7);
        assign(button_8,R.id.button_8);
        assign(button_9,R.id.button_9);
        assign(button_0,R.id.button_0);
        assign(button_dot,R.id.button_dot);
        assign(button_ac,R.id.button_ac);



    }

    void assign(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if (buttonText.equals("AC")){

            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;

        }
        solution_tv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            result_tv.setText(finalResult);
        }



    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return   finalResult;
        }
        catch(Exception e){
            return  "Error";
        }
    }
}