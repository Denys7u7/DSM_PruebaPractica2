package sv.com.udb.prueba.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityCitaBinding;
import sv.com.udb.prueba.model.Horario;
import sv.com.udb.prueba.model.Tratamiento;
import sv.com.udb.prueba.repositories.HorarioRepository;
import sv.com.udb.prueba.repositories.TratamientoRepository;
import sv.com.udb.prueba.spinners.SpinnerHorarioAdapter;
import sv.com.udb.prueba.spinners.SpinnerTratamientoAdapter;

public class CitaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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
        Toast.makeText(this,mesage,Toast.LENGTH_LONG).show();
    }

    private void initialize(){
        try {
            tratamientoList = tratamientoRepository.findAll();
            horarioList = horarioRepository.findAll();
            bind();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void bind(){
        binding.spHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualHorario = spinnerHorarioAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
        binding.spTratamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualTratamiento = spinnerTratamientoAdapter.getItem(position);
                binding.txtPrecio.setText("Precio: " + actualTratamiento.getPrecio());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });

        binding.btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        binding.txtFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
    }
}