package ww.utp.beatenfood;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ww.utp.beatenfood.fragments.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import static ww.utp.beatenfood.fragments.home.INT_CANT;
import static ww.utp.beatenfood.fragments.home.INT_FECHINI;
import static ww.utp.beatenfood.fragments.home.INT_FECHIVENC;
import static ww.utp.beatenfood.fragments.home.INT_NOM;
import static ww.utp.beatenfood.fragments.home.INT_TIPOPRO;
import static ww.utp.beatenfood.fragments.home.INT_UNID;
import static ww.utp.beatenfood.fragments.home.INT_URL;

public class Detalleproducto extends AppCompatActivity {
    TextView name,tipo,cantid,unida,fechai,fechav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleproducto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        String imageurl=intent.getStringExtra(INT_URL);
        String nameprod=intent.getStringExtra(INT_NOM);
        String tipoprod=intent.getStringExtra(INT_TIPOPRO);
        int canti=intent.getIntExtra(INT_CANT,0);
        String fechain=intent.getStringExtra(INT_FECHINI);
        String unid=intent.getStringExtra(INT_UNID);
        String fechavenc=intent.getStringExtra(INT_FECHIVENC);


        ImageView imageView=findViewById(R.id.imagendetalle);
       name=findViewById(R.id.detallenombre);
       tipo=findViewById(R.id.detalleTipo);
       cantid=findViewById(R.id.detalleCantidad);
       fechai=findViewById(R.id.detalleFechacomp);
       fechav=findViewById(R.id.detalleFechacaduc);
        unida=findViewById(R.id.detalleUnidad);
        name.setText(": "+nameprod);
        tipo.setText(": "+tipoprod);
        cantid.setText(": "+String.valueOf(canti));
        //Toast.makeText(this,"Bienvenido "+canti, Toast.LENGTH_SHORT).show();
        fechai.setText(": "+fechain);
        fechav.setText(": "+fechavenc);
        unida.setText(": "+unid);
        Picasso.get().load(imageurl).fit().centerInside().into(imageView);
    }

}
