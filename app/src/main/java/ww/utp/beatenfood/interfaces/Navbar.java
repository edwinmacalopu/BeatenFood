package ww.utp.beatenfood.interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.fragments.ajustes;
import ww.utp.beatenfood.fragments.home;
import ww.utp.beatenfood.fragments.reportes;
import ww.utp.beatenfood.fragments.vencidos;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navbar extends AppCompatActivity {

    private ActionBar toolbar;
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigationview);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Compras");
        loadFragment(new home());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.comida:
                    toolbar.setTitle("Compras");
                    loadFragment(new home());
                    return true;
                case R.id.dos:
                    toolbar.setTitle("Vencidos");
                    loadFragment(new vencidos());
                    return true;
                case R.id.reporte:
                    toolbar.setTitle("Reporte");
                    loadFragment(new reportes());
                    return true;
                case R.id.tres:
                    toolbar.setTitle("Ajustes");
                    loadFragment(new ajustes());
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
