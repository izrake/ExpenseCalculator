package com.firebase.prashannt.exc;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by prashannt on 5/8/2016.
 */
public class ExpenseSave extends Activity implements View.OnClickListener {

    private EditText setAmount,setDetail;
    private Button recordDetail;

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.expensedetail);

        setAmount = (EditText)findViewById(R.id.setAmount);
        setDetail = (EditText) findViewById(R.id.setDetail);
        recordDetail = (Button)findViewById(R.id.recordButton);

        recordDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.recordButton:
                boolean didItWork=true;
                String amount=setAmount.getText().toString();
                String detail=setDetail.getText().toString();

                HotOrNot entry=new HotOrNot(ExpenseSave.this);
                try {
                    entry.open();
                    entry.createEntry(amount, detail);
                    entry.close();
                } catch (SQLException e) {
                    didItWork=false;
                    e.printStackTrace();
                }finally {
                    if(didItWork){
                        Dialog d= new Dialog(this);
                        d.setTitle("HEY DETAILS RECOEDED");
                        TextView tv= new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

        }

    }
}
