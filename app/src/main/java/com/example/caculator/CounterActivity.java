package com.example.caculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CounterActivity extends Activity {


    private TextView calResult;
    private Button[] buttonNum = new Button[11];
    private Button[] buttonSign = new Button[6];
    private Button calCancel = null;
    private Button negative = null;
    private Button confirm = null;
    private Button cancel = null;
    private Button del = null ;
    private boolean firstFlag = true; //是否為第一次運算
    private boolean clearFlag = false; //是否要清空
    private String lastSign = "=" ;
    private double result = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        initialize();
        calResult.setText("0.0");
        NumberAction(buttonNum);
        SignAction(buttonSign);
    }

    public void initialize() {
        buttonNum[0] = (Button) findViewById(R.id.num0);
        buttonNum[1] = (Button) findViewById(R.id.num1);
        buttonNum[2] = (Button) findViewById(R.id.num2);
        buttonNum[3] = (Button) findViewById(R.id.num3);
        buttonNum[4] = (Button) findViewById(R.id.num4);
        buttonNum[5] = (Button) findViewById(R.id.num5);
        buttonNum[6] = (Button) findViewById(R.id.num6);
        buttonNum[7] = (Button) findViewById(R.id.num7);
        buttonNum[8] = (Button) findViewById(R.id.num8);
        buttonNum[9] = (Button) findViewById(R.id.num9);
        buttonNum[10] = (Button) findViewById(R.id.dot);

        buttonSign[0] = (Button) findViewById(R.id.add);
        buttonSign[1] = (Button) findViewById(R.id.minus);
        buttonSign[2] = (Button) findViewById(R.id.multiplier);
        buttonSign[3] = (Button) findViewById(R.id.divider);
        buttonSign[4] = (Button) findViewById(R.id.mod);
        buttonSign[5] = (Button) findViewById(R.id.equal);

        del = (Button) findViewById(R.id.del) ;
        calResult = (TextView) findViewById(R.id.calResult);

    }

    private void NumberAction(Button[] buttonNum) {
        for (Button num : buttonNum) {
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view;
                    String btn_num_string = btn.getText().toString();
                    if (firstFlag) {
                        //如果是"."就不做
                        if (".".equals(btn_num_string)) {
                            return;
                        }
                        //如果是"0.0"就清空
                        if ("0.0".equals(calResult.getText().toString())) {
                            calResult.setText("");
                        }
                        firstFlag = false;
                    } else {
                        String textView_num = calResult.getText().toString();
                        //如果顯示區域有"."再次輸入"."，就不做任何事
                        if (textView_num.indexOf(".") > -1 && ".".equals(btn_num_string)) {
                            return;
                        }
                        //如果顯示區域是0再次輸入不是"."的話，就不做任何事
                        if ("0".equals(textView_num) && !".".equals(btn_num_string)) {
                            return;
                        }
                    }
                    //如果出現符號+數字則清空
                    if(clearFlag){
                        calResult.setText("") ;
                        clearFlag = false ;
                    }
                    calResult.setText(calResult.getText().toString() + btn_num_string);
                }
            });
        }
    }
    private void SignAction(Button[] buttonSign) {
        for (Button sign : buttonSign) {
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view ;
                    String btnSign = btn.getText().toString() ;
                    if(firstFlag){

                    }else{
                        if(!clearFlag){
                            caculate(Double.parseDouble(calResult.getText().toString())) ;
                        }
                        lastSign = btnSign ;
                        clearFlag = true ; //輸入運算符號後畫面清空
                    }
                }
            });
        }
    }
    private void caculate(double num){
        switch(lastSign){
            case "+":
                result += num ;
                break ;
            case "-":
                result -= num ;
                break ;
            case "*":
                result *= num ;
                break ;
            case "/":
                result /= num ;
                break ;
            case "%":
                result %= num ;
                break ;
            case "=":
                result = num ;
        }

            calResult.setText("" + result) ;

    }

    public void cancel(View view){
        calResult.setText("0.0") ;
        firstFlag = true ;
        clearFlag = false ;
    }

    public void delete(View view){
        if("0.0".equals(calResult.getText().toString())){
            return ;
        }
        if(calResult.getText().toString().length()>1){
            calResult.setText(calResult.getText().toString().substring(0,calResult.getText().toString().length()-1)) ;
        }else{
            calResult.setText("0.0") ;
            firstFlag = true ;
            clearFlag = false ;
        }


    }




}
