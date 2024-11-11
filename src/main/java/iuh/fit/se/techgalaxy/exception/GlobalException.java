package iuh.fit.se.techgalaxy.exception;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<DataResponse> handleException() {
        DataResponse dataResponse = DataResponse.builder().status(ErrorCode.UNCATEGORIZED_ERROR.getCode()).message(ErrorCode.UNCATEGORIZED_ERROR.getMessage()).build();
        return ResponseEntity.badRequest().body(dataResponse);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<DataResponse> handleAppException(AppException ex) {
        DataResponse dataResponse = DataResponse.builder().status(ex.getErrorCode().getCode()).message(ex.getErrorCode().getMessage()).build();
        return ResponseEntity.badRequest().body(dataResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<DataResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode;
        try {
            errorCode = ErrorCode.valueOf(ex.getBindingResult().getFieldError().getDefaultMessage());
        }
        catch(Exception e){
            errorCode = ErrorCode.INVALID_KEY;
        }
        DataResponse dataResponse = DataResponse.builder().status(errorCode.getCode()).message(errorCode.getMessage()).build();
        return ResponseEntity.badRequest().body(dataResponse);
    }
}
