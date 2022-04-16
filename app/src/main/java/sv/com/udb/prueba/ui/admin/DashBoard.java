package sv.com.udb.prueba.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityDashboardBinding;
import sv.com.udb.prueba.databinding.ActivityLoginBinding;
import sv.com.udb.prueba.ui.admin.automovil.AutoHomeActivity;
import sv.com.udb.prueba.ui.admin.color.ColorHomeActivity;
import sv.com.udb.prueba.ui.admin.marcas.MarcaHomeActivity;
import sv.com.udb.prueba.ui.admin.tipo.TipoHomeActivity;
import sv.com.udb.prueba.ui.admin.usuarios.UsuariosActivity;
import sv.com.udb.prueba.ui.login.LoginActivity;

public class DashBoard extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUsuario.setOnClickListener(this::btnUsuarioListener);
        binding.btnMarcas.setOnClickListener(this::btnMarcaListener);
        binding.btnAuto.setOnClickListener(this::btnAutoListener);
        binding.btnColor.setOnClickListener(this::btnColorListener);
        binding.btnTipo.setOnClickListener(this::btnTipoListener);
        binding.btnCerrar.setOnClickListener(this::btnCerrarListener);
    }

    private void btnCerrarListener(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void btnMarcaListener(View view){
        title = "Agregar Marca";
        Intent i = new Intent(this, MarcaHomeActivity.class);
        i.putExtra("Title", title);
        startActivity(i);
    }

    private void btnAutoListener(View view){
        Intent i = new Intent(this, AutoHomeActivity.class);
        startActivity(i);
    }

    private void btnColorListener(View view){
        title = "Agregar Color";
        Intent i = new Intent(this, ColorHomeActivity.class);
        i.putExtra("Title", title);
        startActivity(i);
    }

    private void btnTipoListener(View view){
        title = "Agregar Tipo";
        Intent i = new Intent(this, TipoHomeActivity.class);
        i.putExtra("Title", title);
        startActivity(i);
    }

    private void btnUsuarioListener(View view){
        Intent i = new Intent(this, UsuariosActivity.class);
        startActivity(i);
    }

}