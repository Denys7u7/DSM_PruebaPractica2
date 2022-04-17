package sv.com.udb.prueba.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.SQLException;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.repositories.TratamientoRepository;

public class MainActivity extends AppCompatActivity {
    TratamientoRepository tratamientoRepository = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tratamientoRepository = new TratamientoRepository(getApplicationContext());
        try {
            System.err.println(tratamientoRepository.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setTheme(R.style.Theme_PruebaPractica2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}