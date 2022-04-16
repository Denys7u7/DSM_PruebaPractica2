package sv.com.udb.prueba.exceptions;

public class EntityNotCreatedException extends RuntimeException{

    public EntityNotCreatedException(Throwable cause){
        super(cause);
    }

    public EntityNotCreatedException(){
        super("Failed to create new entity");
    }
}
