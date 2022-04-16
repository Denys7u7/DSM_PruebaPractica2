package sv.com.udb.prueba.ui.admin.marcas;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.adapter.MarcaAdapter;
import sv.com.udb.prueba.databinding.ActivityMarcasBinding;
import sv.com.udb.prueba.exceptions.EntityNotCreatedException;
import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.model.Marca;
import sv.com.udb.prueba.repositories.MarcasRepository;

public class MarcaHomeActivity extends AppCompatActivity {

    private static final String EMPTY = "";
    private ActivityMarcasBinding binding;
    private MarcasRepository marcasRepository;
    private List<Marca> marcas = new ArrayList<>();
    private MarcaAdapter marcaAdapter;
    private Marca current = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcas);
        binding = ActivityMarcasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String title = getIntent().getStringExtra("Title");
        binding.lblTitle.setText(title);
        binding.btnAceptar.setOnClickListener(this::btnAceptarListener);
        binding.btnEdit.setOnClickListener(this::btnEditarListener);
        binding.btnDelete.setOnClickListener(this::btnEliminarLister);
        marcaAdapter = new MarcaAdapter(marcas,this::itemClickListener);
        binding.recyclerView.setAdapter(marcaAdapter);
        marcasRepository = new MarcasRepository(getApplicationContext());
        init();
    }

    private void itemClickListener(Marca payload) {
        current = payload;
        binding.txtName.setText(payload.getNombre());
    }

    private void init(){
        try{
            marcas = marcasRepository.findAll();
            marcaAdapter.update(marcas);
            System.out.println("asd");
        }catch (Exception e){
            e.printStackTrace();
            showToast("Fallo al obtener marcas");
        }
    }

    private void btnEditarListener(View view){
        try{
            check();
            final String title = binding.txtName.getText().toString();
            current.setNombre(title);
            marcasRepository.update(current);
            tearDown();
        }catch (SQLException e){
            showToast("Fallo al editar");
        }
        catch (RuntimeException e){
            showToast(e.getMessage());
        }
    }

    private void btnEliminarLister(View view){
        try{
            check();
            marcasRepository.deleteById(current.getId());
            tearDown();
        } catch (SQLException e){
        showToast("Fallo al eliminar");
        }catch (RuntimeException e){
            showToast(e.getMessage());
        }
    }

    private void btnAceptarListener(View view) {
        final String title = binding.txtName.getText().toString();
        if ( null == title || EMPTY.equals(title) || title.length() < 3){
            showToast("Title cannot be empty or be less than 3");
            return;
        }
        Marca instance = new Marca(title);
        try{
            marcasRepository.create(instance);
            tearDown();
        }catch (SQLException | EntityNotCreatedException e){
            e.printStackTrace();
            showToast("Failed to create  new");
        }
    }

    private void check(){
        if(current == null){
            throw new RuntimeException("No ha seleccionado ninguna marca");
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void tearDown(){
        showToast("Sucess!");
        current = null;
        binding.txtName.setText(EMPTY);
        init();
    }

}
