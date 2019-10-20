package ww.utp.beatenfood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class AddProducts extends AppCompatActivity {
    Button btnDatePicker;
    private int mYear, mMonth, mDay;
    TextView fechesc;
    ImageView imagefoto;
    RadioButton est1,est2,est3,radioButton;
    RadioGroup Grup_est;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Declarando los Radio-Buttons
        Grup_est=(RadioGroup)findViewById(R.id.Rad_condi);
        est1=(RadioButton)findViewById(R.id.cond1);
        est2=(RadioButton)findViewById(R.id.cond2);
        est3=(RadioButton)findViewById(R.id.cond3);
        btnDatePicker=(Button)findViewById(R.id.btnfecha);
        fechesc=findViewById(R.id.txtfechaesc);
        imagefoto=findViewById(R.id.imagenprod);
        imagefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
    }


    public void escfecha(View v){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        int RadioId= Grup_est.getCheckedRadioButtonId();
        radioButton=findViewById(RadioId);
        switch (RadioId){
            case 0:
                mDay=mDay+3;
                break;
            case 1:
                mDay=mDay;
                break;
            case 2:
                mDay=mDay-3;
                break;
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        fechesc.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=(Bitmap) data.getExtras().get("data");
        imagefoto.setImageBitmap(bitmap);
    }
}
