package sv.com.udb.prueba.ui.admin.automovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.adapter.AutoAdapter;
import sv.com.udb.prueba.databinding.ActivityAutoHomeBinding;
import sv.com.udb.prueba.model.Automovil;
import sv.com.udb.prueba.repositories.AutoRepository;

public class AutoHomeActivity extends AppCompatActivity {

    private ActivityAutoHomeBinding binding;
    private AutoRepository autoRepository;
    private List<Automovil> automoviles = new ArrayList<>();
    private AutoAdapter autoAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_home);
        binding = ActivityAutoHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        autoAdapter = new AutoAdapter(automoviles,payload -> System.out.println("Soft click")
                ,(Automovil automovil) -> System.out.println("Long Click baby"));
        binding.recyclerView.setAdapter(autoAdapter);
        autoRepository = new AutoRepository(getApplicationContext());
        binding.btnAdd.setOnClickListener(this::btnAddListener);
        registerForContextMenu(binding.recyclerView);
        init();
    }

    private void btnAddListener(View view){
        Intent i = new Intent(this, NewAutoActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        try{
            Automovil payload = autoAdapter.getPosition();
            System.out.println("Color " + payload.getColores() + " Marca: "+payload.getMarca() + " Tipo: " + payload.getTipoAutomovil());
            String itemTitle = item.getTitle().toString();
            if(AutoAdapter.AutoViewHolder.EDIT.equals(itemTitle)){
                Intent i = new Intent(this, EditAutoActivity.class);
                i.putExtra("id", payload.getId());
                startActivity(i);
            }else if(AutoAdapter.AutoViewHolder.DELETE.equals(itemTitle)) {
                autoRepository.deleteById(payload.getId());
                showToast("Eliminado!");
                init();
            }
        }catch (Exception e){
            e.printStackTrace();
            showToast("Unknow error, please see log");
        }
        return super.onContextItemSelected(item);
    }


    private void init(){
        try{
            automoviles = autoRepository.findAll();
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