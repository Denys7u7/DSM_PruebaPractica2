package sv.com.udb.prueba.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityCitaBinding;
import sv.com.udb.prueba.databinding.ActivityNewAutoBinding;
import sv.com.udb.prueba.model.Color;
import sv.com.udb.prueba.model.Horario;
import sv.com.udb.prueba.model.Marca;
import sv.com.udb.prueba.model.TipoAutomovil;
import sv.com.udb.prueba.model.Tratamiento;
import sv.com.udb.prueba.repositories.AutoRepository;
import sv.com.udb.prueba.repositories.ColoresRepository;
import sv.com.udb.prueba.repositories.HorarioRepository;
import sv.com.udb.prueba.repositories.MarcasRepository;
import sv.com.udb.prueba.repositories.TipoRepository;
import sv.com.udb.prueba.repositories.TratamientoRepository;
import sv.com.udb.prueba.spinners.SpinnerHorarioAdapter;
import sv.com.udb.prueba.spinners.SpinnerTratamientoAdapter;
import sv.com.udb.prueba.ui.admin.automovil.SpinnerColorAdapter;
import sv.com.udb.prueba.ui.admin.automovil.SpinnerMarcaAdapter;
import sv.com.udb.prueba.ui.admin.automovil.SpinnerTipoAdapter;

public class Cita extends AppCompatActivity {

    private final static String EMPTY = "";
    private TratamientoRepository tratamientoRepository;
    private HorarioRepository horarioRepository;

    private ActivityCitaBinding binding;

    private List<Tratamiento> tratamientoList = new ArrayList<>();
    private List<Horario> horarioList = new ArrayList<>();
    private SpinnerTratamientoAdapter spinnerTratamientoAdapter;
    private SpinnerHorarioAdapter spinnerHorarioAdapter;
    private Tratamiento actualTratamiento = null;
    private Horario actualHorario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
        binding = ActivityCitaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tratamientoRepository = new TratamientoRepository(getApplicationContext());
        horarioRepository = new HorarioRepository(getApplicationContext());
        initialize();

        spinnerHorarioAdapter = new SpinnerHorarioAdapter(this, android.R.layout.simple_spinner_dropdown_item,horarioList.toArray(new Horario[horarioList.size()]));
        spinnerTratamientoAdapter = new SpinnerTratamientoAdapter(this, android.R.layout.simple_spinner_dropdown_item,tratamientoList.toArray(new Tratamiento[tratamientoList.size()]));

        binding.spTratamiento.setAdapter(spinnerTratamientoAdapter);
        binding.spHorario.setAdapter(spinnerHorarioAdapter);

        binding.btnAceptar.setOnClickListener(this::onAgendarCita);
    }

    private void onAgendarCita(View view){
        showToast("Agendando cita");
    }

    private void showToast(String mesage){
        Toast.makeText(this,mesage,Toast.LENGTH_LONG);
    }

    private void initialize(){
        try {
            tratamientoList = tratamientoRepository.findAll();
            horarioList = horarioRepository.findAll();
            //bind();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void bind(){
        binding.spHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualTratamiento = spinnerTratamientoAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
        binding.spTratamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualHorario = spinnerHorarioAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
    }
}