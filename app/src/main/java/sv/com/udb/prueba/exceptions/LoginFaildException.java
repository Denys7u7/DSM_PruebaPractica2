package sv.com.udb.prueba.exceptions;

import android.content.Context;

public class LoginFaildException extends RuntimeException{

    public LoginFaildException() {
        super("Usuario o contraseña inconrrectos");
    }
}
