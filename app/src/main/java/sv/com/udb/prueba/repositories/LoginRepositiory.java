package sv.com.udb.prueba.repositories;

import android.content.Context;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.com.udb.prueba.exceptions.LoginFaildException;
import sv.com.udb.prueba.model.Usuario;

public final class LoginRepositiory extends AbstractRepository<Usuario,Integer> {
    public LoginRepositiory(Context context){
        super(context);
    }

    public Usuario acceder(String user, String pass){
        Map<String,Object> fields = new HashMap<>();
        fields.put("user",user);
        fields.put("password",pass);
        try {
            List<Usuario> response = genericDao.queryForFieldValues(fields);
            return response.get(0);
        } catch (SQLException| IndexOutOfBoundsException throwables) {
            throwables.printStackTrace();
            throw new LoginFaildException();
        }
    }
}
