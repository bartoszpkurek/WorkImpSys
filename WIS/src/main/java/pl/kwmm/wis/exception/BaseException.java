package pl.kwmm.wis.exception;

import javax.ejb.ApplicationException;

@ApplicationException
abstract public class BaseException extends Exception{
    
    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
