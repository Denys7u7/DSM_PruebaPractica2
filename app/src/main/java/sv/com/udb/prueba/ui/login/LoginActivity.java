package sv.com.udb.prueba.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.ui.client.HomeActivity;
import sv.com.udb.prueba.databinding.ActivityLoginBinding;
import sv.com.udb.prueba.model.Usuario;
import sv.com.udb.prueba.repositories.LoginRepositiory;
import sv.com.udb.prueba.ui.admin.DashBoard;

public class LoginActivity extends AppCompatActivity {

    public static final String ADMIN = "ADMIN";
    public static final String CLIENT = "CLIENT";
    private ActivityLoginBinding binding;
    private LoginRepositiory loginRepositiory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PruebaPractica2);
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAcceder.setOnClickListener(this::btnAccederListener);
        loginRepositiory = new LoginRepositiory((getApplicationContext()));

//        try{
//
//            loginRepositiory.create(new Usuario(5,"Alejandr","Alejo","alejandro714@gmail.com","alejo","12345",new Role(2,"CLIENT")));
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    private void btnAccederListener(View view){
        final String user = binding.username.getText().toString();
        final String password = binding.password.getText().toString();

        try {
            Usuario login = loginRepositiory.acceder(user, password);
            boolean isAdmin = ADMIN.equals(login.getRole().getRole());
            boolean isClient = CLIENT.equals(login.getRole().getRole());
            if (isAdmin){
                Intent i = new Intent(this, DashBoard.class);
                startActivity(i);
            }else if(isClient) {
                Intent i = new Intent(this, HomeActivity.class);
                i.putExtra("userId",login.getId());
                startActivity(i);
            }else {
                throw new RuntimeException("User is not an admin or user or unexpected error");
            }
        }catch (Exception e){
            e.printStackTrace();
            showLoginFailed(e.toString());
        }
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}