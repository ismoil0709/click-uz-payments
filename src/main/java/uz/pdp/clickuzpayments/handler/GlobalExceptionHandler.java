package uz.pdp.clickuzpayments.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.exception.BadRequestException;
import uz.pdp.clickuzpayments.exception.InvalidArgumentException;
import uz.pdp.clickuzpayments.exception.NotFoundException;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public CustomResponseEntity<?> handleNotFoundException(NotFoundException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(NullOrEmptyException.class)
    public CustomResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public CustomResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public CustomResponseEntity<?> handleException(BadRequestException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public CustomResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
}