package com.firebase.prashannt.exc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by prashannt on 5/9/2016.
 */
public class ShowBudgetDetail extends Activity {
    private TextView expenseDetail;

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);

        setContentView(R.layout.showexpensedetail);
        expenseDetail=(TextView)findViewById(R.id.expenseDetail);

        HotOrNot info=new HotOrNot(this);

        try {
            info.open();
            String data = info.getData();
            info.close();
            expenseDetail.setText(data);
        }catch (Exception e){

        }



    }
}
