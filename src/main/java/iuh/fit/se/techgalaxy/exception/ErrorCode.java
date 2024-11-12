package iuh.fit.se.techgalaxy.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    // 9000 - 9999: System error with authorization
    INVALID_KEY(9998, "Invalid key", HttpStatus.UNAUTHORIZED),
    JWT_INVALID(9997, "JWT invalid",  HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED_ERROR(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    //8000 - 8999: error with authentication
    AUTHENTICATION_ERROR(8000, "You no access to method", HttpStatus.FORBIDDEN),

    // 1000 - 1999: Business error ( call data from database)
    PRODUCT_NOTFOUND(1000, "Product not found", HttpStatus.NOT_FOUND),
    ACCOUNT_NOTFOUND(1001, "Account not found", HttpStatus.NOT_FOUND),
    CUSTOMER_NOTFOUND(1002, "Customer not found", HttpStatus.NOT_FOUND),

    // 2000 - 2999: Validation error in request body
    PRODUCT_NAME_INVALID(2002, "Product name min length is 1 and max length is 20", HttpStatus.BAD_REQUEST),
    NO_RESOURCE_FOUND(4000, "No resource found", HttpStatus.NOT_FOUND),



    // 4000 - 4999: Storage and file error
    FILE_EMPTY(4000, "File is empty. Please upload a file.", HttpStatus.BAD_REQUEST),
    INVALID_FILE_EXTENSION(4001, "Invalid file extension. only allows pdf, jpg, jpeg, png, doc, docx", HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND(4002, "File not found", HttpStatus.NOT_FOUND),
    FILE_SIZE_EXCEEDED(4003, "File size exceeded", HttpStatus.PAYLOAD_TOO_LARGE),
    MISSING_FILE(4004, "Missing file", HttpStatus.BAD_REQUEST),
    CREATE_DIRECTORY_FAILED(4005, "Create directory failed", HttpStatus.INTERNAL_SERVER_ERROR),
    ;
    int code;
    String message;
    HttpStatus httpStatus;
}
