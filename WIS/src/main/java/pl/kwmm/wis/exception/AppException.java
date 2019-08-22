package pl.kwmm.wis.exception;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.ApplicationException;

@ApplicationException
public class AppException extends BaseException {

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    static final public String NO_HASH_ALGORITHM = "Wrong algorithm.";
    static final public String RESOURCE_LACK = "There is no such resource.";

    static public AppException hashPasswordException(NoSuchAlgorithmException cause) {
        AppException ae = new AppException(NO_HASH_ALGORITHM, cause);
        return ae;
    }

    static public AppException resourceLackException(IOException cause) {
        AppException ae = new AppException(RESOURCE_LACK, cause);
        return ae;
    }

}
