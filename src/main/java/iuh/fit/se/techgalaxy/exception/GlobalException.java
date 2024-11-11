package iuh.fit.se.techgalaxy.exception;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<DataResponse> handleException() {
        DataResponse dataResponse = DataResponse.builder().status(ErrorCode.UNCATEGORIZED_ERROR.getCode()).message(ErrorCode.UNCATEGORIZED_ERROR.getMessage()).build();
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_ERROR.getHttpStatus()).body(dataResponse);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<DataResponse> handleAppException(AppException ex) {
        DataResponse dataResponse = DataResponse.builder().status(ex.getErrorCode().getCode()).message(ex.getErrorCode().getMessage()).build();
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(dataResponse);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<DataResponse> handleNoResourceFoundException() {
        DataResponse dataResponse = DataResponse.builder().status(ErrorCode.NO_RESOURCE_FOUND.getCode()).message(ErrorCode.NO_RESOURCE_FOUND.getMessage()).build();
        return ResponseEntity.status(ErrorCode.NO_RESOURCE_FOUND.getHttpStatus()).body(dataResponse);
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
        return ResponseEntity.status(errorCode.getHttpStatus()).body(dataResponse);
    }
}
