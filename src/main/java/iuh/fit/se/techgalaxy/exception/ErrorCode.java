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
    NO_RESOURCE_FOUND(4000, "No resource found", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus httpStatus;
}
