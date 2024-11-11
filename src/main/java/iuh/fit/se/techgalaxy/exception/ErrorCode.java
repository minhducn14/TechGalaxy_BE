package iuh.fit.se.techgalaxy.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    INVALID_KEY(9998, "Invalid key"),
    JWT_INVALID(9997, "JWT invalid"),
    UNCATEGORIZED_ERROR(9999, "Uncategorized error"),
    PRODUCT_NOTFOUND(1000, "Product not found"),
    ACCOUNT_NOTFOUND(1001, "Account not found"),
    PRODUCT_NAME_INVALID(2002, "Product name min length is 1 and max length is 10");
    int code;
    String message;
}
