package uz.pdp.clickuzpayments.exception;

public class NullOrEmptyException extends RuntimeException {
    public NullOrEmptyException(String m) {
        super(m + " is empty");
    }
}
