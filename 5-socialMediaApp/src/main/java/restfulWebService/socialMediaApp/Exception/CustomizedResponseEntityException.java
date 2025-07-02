package restfulWebService.socialMediaApp.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import restfulWebService.socialMediaApp.user.UserNotFoundException;

import org.springframework.http.HttpHeaders;

@ControllerAdvice // This annotation allows us to handle exceptions globally across all controllers
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) 
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
        ErrorDetails errorDetails= new ErrorDetails(ex.getMessage(), 
                request.getDescription(false), 
                LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    } 

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        ErrorDetails errorDetails= new ErrorDetails(ex.getMessage(), 
                request.getDescription(false), 
                LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getFieldError().getDefaultMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    

}
