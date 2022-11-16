package challenge.mercadolibre.MercadoLibre.exceptions;


public class NotSquareException extends BadRequestException{

    private static String MESSAGE_ERROR = "The matrix must be square nxn";

    public NotSquareException(){
        super(MESSAGE_ERROR);
    }
    
}
