package uz.pdp.clickuzpayments.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String m){
        super(m);
    }
}
