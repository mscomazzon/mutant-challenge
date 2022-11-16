package challenge.mercadolibre.MercadoLibre.exceptions;

public class WrongLettersException extends BadRequestException{

    private static String MESSAGE_ERROR = "There is a letter different than A, T, C or G";

    public WrongLettersException(){
        super(MESSAGE_ERROR);
    }
    
}