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
public class RecordMonthBudget extends Activity implements View.OnClickListener {
    private Button createButton;
    private EditText setBudget;

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.monthbudget);

        createButton = (Button)findViewById(R.id.createButton);
        setBudget = (EditText)findViewById(R.id.getMonthBudget);

        createButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.createButton:
                boolean didItWork=true;
                String budgetAmount=setBudget.getText().toString();

                HotOrNot entry=new HotOrNot(RecordMonthBudget.this);
                try {
                    entry.open();
                    entry.createEntry(budgetAmount);
                    entry.close();

                } catch (SQLException e) {
                    didItWork=false;
                    e.printStackTrace();
                }finally {
                    if(didItWork){
                        Dialog d= new Dialog(this);
                        d.setTitle("Heck Ya");
                        TextView tv= new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }


                break;

        }
    }
}
