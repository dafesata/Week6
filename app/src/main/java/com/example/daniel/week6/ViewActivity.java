package com.example.daniel.week6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class ViewActivity extends Activity {
    EditText editText1,editText2;
    private String TAG= Constanst.TAG;
    private boolean viewmode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        editText1 = (EditText) findViewById(R.id.editField1);
        editText2 = (EditText) findViewById(R.id.editField2);

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            viewmode=true;
            DataEntry dataEntry = (DataEntry) bundle.getSerializable("Entryview");
            editText1.setText(dataEntry.field1+"");
            editText2.setText(dataEntry.field2+"");

        }else{
            viewmode=false;
        }
        editText1.setFocusable(!viewmode);
        editText1.setFocusableInTouchMode(!viewmode);
        editText2.setFocusable(!viewmode);
        editText2.setFocusableInTouchMode(!viewmode);

    }

    public void onClickAceptar(View view){
        if(viewmode){
            finish();
        }
        if(TextUtils.isEmpty(editText1.getText().toString())){
            editText1.setError("No puede estar vacio");
        }else{
            if(TextUtils.isEmpty(editText2.getText().toString())){
                editText2.setError("No puede estar vacio");
            }else{
                DataEntry de= new DataEntry(Integer.parseInt(editText1.getText().toString()),Integer.parseInt(editText2.getText().toString()));
                Intent i= getIntent();
                i.putExtra("entry",de);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        }

    }
}
