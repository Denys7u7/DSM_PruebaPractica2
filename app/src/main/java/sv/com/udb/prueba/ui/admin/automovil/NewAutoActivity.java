package sv.com.udb.prueba.ui.admin.automovil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityNewAutoBinding;
import sv.com.udb.prueba.model.Automovil;
import sv.com.udb.prueba.model.Color;
import sv.com.udb.prueba.model.Marca;
import sv.com.udb.prueba.model.TipoAutomovil;
import sv.com.udb.prueba.repositories.AutoRepository;
import sv.com.udb.prueba.repositories.ColoresRepository;
import sv.com.udb.prueba.repositories.MarcasRepository;
import sv.com.udb.prueba.repositories.TipoRepository;

public class NewAutoActivity extends AppCompatActivity {

    private final static String EMPTY = "";
    private MarcasRepository marcasRepository;
    private ColoresRepository coloresRepository;
    private TipoRepository tipoRepository;
    private AutoRepository autoRepository;
    private List<Marca> marcas = new ArrayList<>();
    private List<Color> colores = new ArrayList<>();
    private List<TipoAutomovil> tipoAutomoviles = new ArrayList<>();
    private ActivityNewAutoBinding binding;
    private SpinnerColorAdapter spinnerColorAdapter;
    private SpinnerMarcaAdapter spinnerMarcaAdapter;
    private SpinnerTipoAdapter spinnerTipoAdapter;
    private Marca actualMarca = null;
    private Color actualColor = null;
    private TipoAutomovil actualTipo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_auto);
        binding = ActivityNewAutoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        marcasRepository = new MarcasRepository(getApplicationContext());
        coloresRepository = new ColoresRepository(getApplicationContext());
        tipoRepository = new TipoRepository(getApplicationContext());
        autoRepository = new AutoRepository(getApplicationContext());
        initialize();
        spinnerColorAdapter = new SpinnerColorAdapter(this, android.R.layout.simple_spinner_dropdown_item,colores.toArray(new Color[colores.size()]));
        spinnerMarcaAdapter = new SpinnerMarcaAdapter(this, android.R.layout.simple_spinner_dropdown_item,marcas.toArray(new Marca[marcas.size()]));
        spinnerTipoAdapter = new SpinnerTipoAdapter(this, android.R.layout.simple_spinner_dropdown_item,tipoAutomoviles.toArray(new TipoAutomovil[tipoAutomoviles.size()]));
        binding.spColor.setAdapter(spinnerColorAdapter);
        binding.spMarca.setAdapter(spinnerMarcaAdapter);
        binding.spTipo.setAdapter(spinnerTipoAdapter);
        binding.btnAceptar.setOnClickListener(this::onAceptarListener);
    }

    private void onAceptarListener(View view){
        String modelo;
        String vin;
        String chasis;
        String motor;
        Integer asientos;
        Integer anio;
        Float precio;
        String descripcion;
        try{
            modelo = binding.txtModelo.getText().toString();
            vin = binding.txtVin.getText().toString();
            chasis = binding.txtChasis.getText().toString();
            motor = binding.txtMotor.getText().toString();
            asientos = Integer.parseInt(binding.txtAsientos.getText().toString());
            anio = Integer.parseInt(binding.txtAnio.getText().toString());
            precio = Float.parseFloat(binding.txtPrice.getText().toString());
            descripcion = binding.txtDescripcion.getText().toString();
            if (EMPTY.equals(modelo) || EMPTY.equals(vin) || EMPTY.equals(chasis) || EMPTY.equals(motor)
                    || (asientos < 1 || asientos > 10) || (anio < 2000 || anio > 2022) || precio < 1 || EMPTY.equals(descripcion)
                    || actualColor == null || actualMarca == null || actualTipo == null){
                showToast("Informacion incorrecta, no puede estar vacio, asientos 1<10, anio min 2000, precio > 1");
                return;
            }
            Automovil instance = new Automovil(null,modelo,vin,chasis,motor,asientos,anio,asientos,precio,"",descripcion,actualMarca,actualTipo,actualColor);
            autoRepository.create(instance);
            showToast("Sucess!");
            finish();
        }catch (Exception e){
            showToast("Failed to create new car");
        }
    }

    private void showToast(String mesage){
        Toast.makeText(this,mesage,Toast.LENGTH_LONG);
    }


    private void initialize(){
        try {
            marcas = marcasRepository.findAll();
            colores = coloresRepository.findAll();
            tipoAutomoviles = tipoRepository.findAll();
            bind();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void bind(){
        binding.spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualColor = spinnerColorAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
        binding.spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualMarca = spinnerMarcaAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
        binding.spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualTipo = spinnerTipoAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
    }
}