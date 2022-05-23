package com.example.taserfan.API;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */

public class Result<T>{
    public static class Success<T> extends Result{

        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static class Error extends Result {

        private String message;
        private int code;

        public Error( int code, String message) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}

