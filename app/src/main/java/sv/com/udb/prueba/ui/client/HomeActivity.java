package sv.com.udb.prueba.ui.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityHomeBinding;
import sv.com.udb.prueba.ui.client.automovil.AutoMovilActivity;
import sv.com.udb.prueba.ui.client.favoritos.FavoritosActivity;
import sv.com.udb.prueba.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userId = getIntent().getIntExtra("userId",-1);
        if(userId == -1){
            Toast.makeText(this,"Failed to retrieve user",Toast.LENGTH_LONG).show();
            finish();
        }
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCarros.setOnClickListener(this::btnCarrosListener);
        binding.btnFav.setOnClickListener(this::btnFavListener);
        binding.btnCerrar.setOnClickListener(this::btnCerrarListener);
    }

    private void btnCerrarListener(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void btnCarrosListener(View view){
        Intent i = new Intent(this, AutoMovilActivity.class);
        i.putExtra("userId",userId);
        startActivity(i);
    }

    private void btnFavListener(View view){
        Intent i = new Intent(this, FavoritosActivity.class);
        i.putExtra("userId",userId);
        startActivity(i);
    }
}