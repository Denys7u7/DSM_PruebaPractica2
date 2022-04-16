package sv.com.udb.prueba.ui.admin.usuarios;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityUsuariosBinding;
import sv.com.udb.prueba.model.Role;
import sv.com.udb.prueba.model.Usuario;
import sv.com.udb.prueba.repositories.LoginRepositiory;
import sv.com.udb.prueba.repositories.RoleRepository;

public class UsuariosActivity extends AppCompatActivity {

    private final static String EMPTY = "";
    private List<Role> tipoRole = new ArrayList<>();
    private SpinnerRolAdapter spinnerRolAdapter;
    private ActivityUsuariosBinding binding;
    private LoginRepositiory usuarioRepository;
    private Role actualRole = null;
    private RoleRepository roleRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        binding = ActivityUsuariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        roleRepository = new RoleRepository(getApplicationContext());
        init();
        spinnerRolAdapter = new SpinnerRolAdapter(this, android.R.layout.simple_spinner_dropdown_item,tipoRole.toArray(new Role[tipoRole.size()]));
        binding.slRol.setAdapter(spinnerRolAdapter);

        usuarioRepository = new LoginRepositiory(getApplicationContext());
        binding.btnAgregar.setOnClickListener(this::onAgregarListener);

        binding.slRol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualRole = spinnerRolAdapter.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //ignored
            }
        });
    }

    private void onAgregarListener(View view){
        String nombres;
        String apellidos;
        String email;
        String user;
        String pass;

        try {
            nombres = binding.nombres.getText().toString();
            apellidos = binding.apellidos.getText().toString();
            email = binding.email.getText().toString();
            user = binding.username.getText().toString();
            pass = binding.password.getText().toString();

            if (EMPTY.equals(nombres) || EMPTY.equals(apellidos) || EMPTY.equals(email) || EMPTY.equals(user) || EMPTY.equals(pass)
                    || actualRole == null){
                showToast("Informacion incorrecta, no puede estar vacio");
                return;
            }

            Usuario instance = new Usuario(null, nombres, apellidos, email, user, pass, actualRole);
            usuarioRepository.create(instance);
            showToast("Sucess!");
            finish();
        }catch (Exception e){
            showToast("Failed to create new car");
        }

    }

    private void showToast(String mesage){
        Toast.makeText(this,mesage,Toast.LENGTH_LONG);
    }

    private void init(){
        try{
            tipoRole = roleRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Failed to recover roles",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}