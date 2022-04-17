package sv.com.udb.prueba.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_PruebaPractica2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnCita:
                irCita();
                break;
            case R.id.btnReporte:
                irReporte();
                break;

        }
    }

    private void irCita(){
        Intent i = new Intent(this, CitaActivity.class);
        startActivity(i);
    }

    private void irReporte(){

    }
}