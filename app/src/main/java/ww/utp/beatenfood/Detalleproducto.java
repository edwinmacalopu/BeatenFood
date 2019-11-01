package ww.utp.beatenfood;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static ww.utp.beatenfood.fragments.home.EXTRA_CREATOR;
import static ww.utp.beatenfood.fragments.home.EXTRA_URL;

public class Detalleproducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleproducto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        String imageurl=intent.getStringExtra(EXTRA_URL);
        String nameprod=intent.getStringExtra(EXTRA_CREATOR);

        ImageView imageView=findViewById(R.id.imagendetalle);
        TextView namee=findViewById(R.id.detallenombre);
        namee.setText(nameprod);
        Picasso.get().load(imageurl).fit().centerInside().into(imageView);
    }

}
