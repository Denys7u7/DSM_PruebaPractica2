package sv.com.udb.prueba.exceptions;

public class InvalidRepositoryException extends RuntimeException{

    public InvalidRepositoryException(){
        super("This class is not valid to a repository");
    }

}
