package cz.jdrabek.fitcalc.exception;

/**
 * Runtime exception for Fit Calc
 *
 * @author jdrabek
 */
public class FitCalcRuntimeException extends RuntimeException {

    public FitCalcRuntimeException() {
    }

    public FitCalcRuntimeException(String message) {
        super(message);
    }

    public FitCalcRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FitCalcRuntimeException(Throwable cause) {
        super(cause);
    }

}