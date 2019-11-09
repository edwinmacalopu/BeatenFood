package ww.utp.beatenfood;

import androidx.appcompat.app.AppCompatActivity;
import ww.utp.beatenfood.interfaces.Login;
import ww.utp.beatenfood.interfaces.Navbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validalogin();
    }

    public void validalogin(){
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        //  String name = sharedPreferences.getString(“signature”, "");
        // SharedPreferences prefs = this.getActivity().getSharedPreferences("pref",0);
        String valida=sharedPreferences.getString("session","SIN DATOS");
        if (valida.equals("activo")){
            Intent intent=new Intent(MainActivity.this, Navbar.class);
            startActivity(intent);
        }else{
            Intent intent=new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }


    }
}
