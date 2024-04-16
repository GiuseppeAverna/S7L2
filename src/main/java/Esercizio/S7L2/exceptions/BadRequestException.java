package Esercizio.S7L2.exceptions;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.ObjectError;

@Getter
public class BadRequestException extends RuntimeException{
    private List<ObjectError> errorsList;
    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorsList){
        super("Ci sono stati errori di validazione nel payload!");
        this.errorsList = errorsList;
    }
}