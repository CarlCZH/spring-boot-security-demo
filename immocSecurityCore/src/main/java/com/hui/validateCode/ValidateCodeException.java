package com.hui.validateCode;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 19:41 2019\2\10 0010
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
