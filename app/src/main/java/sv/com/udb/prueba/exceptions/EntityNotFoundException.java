package sv.com.udb.prueba.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(){
        super("Entity could not be found");
    }
}
