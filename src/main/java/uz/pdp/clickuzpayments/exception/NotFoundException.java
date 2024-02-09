package uz.pdp.clickuzpayments.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String m) {
        super(m + " not found");
    }
}
