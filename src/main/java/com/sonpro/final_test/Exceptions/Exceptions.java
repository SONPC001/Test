package com.sonpro.final_test.Exceptions;

public class Exceptions {
    public static class ExistsException extends RuntimeException {
        public ExistsException(String message) {
            super(message);
        }
    }
}
