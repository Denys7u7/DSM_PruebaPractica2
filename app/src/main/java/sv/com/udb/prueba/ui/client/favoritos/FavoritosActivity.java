package sv.com.udb.prueba.ui.client.favoritos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.adapter.AutoAdapter;
import sv.com.udb.prueba.databinding.ActivityAutoHomeBinding;
import sv.com.udb.prueba.databinding.ActivityAutoHomeClientBinding;
import sv.com.udb.prueba.model.Automovil;
import sv.com.udb.prueba.model.Favoritos;
import sv.com.udb.prueba.repositories.AutoRepository;
import sv.com.udb.prueba.repositories.FavoritoRepository;

public class FavoritosActivity extends AppCompatActivity {

    private ActivityAutoHomeClientBinding binding;
    private FavoritoRepository favoritoRepository;
    private List<Automovil> automoviles = new ArrayList<>();
    private AutoAdapter autoAdapter;
    private Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_home_client);
        userId = getIntent().getIntExtra("userId",-1);
        if(userId == -1){
            Toast.makeText(this,"Failed to retrieve user",Toast.LENGTH_LONG).show();
            finish();
        }
        binding = ActivityAutoHomeClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        autoAdapter = new AutoAdapter(automoviles,payload -> System.out.println("Soft click")
                ,(Automovil automovil) -> System.out.println("Long Click baby"));
        binding.recyclerView.setAdapter(autoAdapter);
        favoritoRepository = new FavoritoRepository(getApplicationContext());
        registerForContextMenu(binding.recyclerView);
        init();
    }

    private void init(){
        try{
            List<Favoritos> favs = favoritoRepository.getFavoritiesByUserId(userId);
            for(Favoritos fav : favs){
                automoviles.add(fav.getAutomovil());
            }
            autoAdapter.update(automoviles);
        }catch (Exception e){
            e.printStackTrace();
            showToast("Failed to recover cars");
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}