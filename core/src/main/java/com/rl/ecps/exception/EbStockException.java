package com.rl.ecps.exception;

/**
 * Created by ozc on 2017/10/18.
 */
public class EbStockException extends RuntimeException {
    public EbStockException() {
    }

    public EbStockException(String message) {
        super(message);
    }

    public EbStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbStockException(Throwable cause) {
        super(cause);
    }

}
