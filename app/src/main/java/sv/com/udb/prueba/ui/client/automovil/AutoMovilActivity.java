package sv.com.udb.prueba.ui.client.automovil;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.adapter.AutoFavAdapter;
import sv.com.udb.prueba.databinding.ActivityAutoHomeClientBinding;
import sv.com.udb.prueba.model.Automovil;
import sv.com.udb.prueba.model.Favoritos;
import sv.com.udb.prueba.model.Usuario;
import sv.com.udb.prueba.repositories.AutoRepository;
import sv.com.udb.prueba.repositories.FavoritoRepository;
import sv.com.udb.prueba.repositories.LoginRepositiory;

public class AutoMovilActivity extends AppCompatActivity {

    private ActivityAutoHomeClientBinding binding;
    private AutoRepository autoRepository;
    private List<Automovil> automoviles = new ArrayList<>();
    private AutoFavAdapter autoAdapter;
    private FavoritoRepository favoritoRepository;
    private LoginRepositiory loginRepositiory;
    private Integer userId;
    private Usuario usuario;

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
        autoAdapter = new AutoFavAdapter(automoviles, this::onFavListener);
        binding.recyclerView.setAdapter(autoAdapter);
        autoRepository = new AutoRepository(getApplicationContext());
        registerForContextMenu(binding.recyclerView);
        favoritoRepository = new FavoritoRepository(getApplicationContext());
        loginRepositiory = new LoginRepositiory(getApplicationContext());
        init();
    }

    private void onFavListener(Automovil payload){
        Favoritos instance = new Favoritos(usuario,payload,new Date(System.currentTimeMillis()));
        try{
            favoritoRepository.create(instance);
            Toast.makeText(this,"Added to favorities",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Failed to create new favorities",Toast.LENGTH_LONG).show();
        }
    }

    private void init(){
        try{
            automoviles = autoRepository.findAll();
            usuario = loginRepositiory.getOne(userId);
            autoAdapter.update(automoviles);
        }catch (Exception e){
            e.printStackTrace();
            showToast("Failed to recover cars");
            finish();
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}