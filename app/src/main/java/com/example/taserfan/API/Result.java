package com.example.taserfan.API;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {

    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Success<T> success = (Success<T>) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Error error = (Error) this;
            return "Error[exception=" + error.getError() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends Result {
        private T data;

        public Success() {
        }

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    // Error sub-class
    public final static class Error extends Result {

        private int code;
        private String error;

        public Error(int code, String error) {
            this.code = code;
            this.error = error;
        }

        public Error() {
        }

        public void setError(String error) {
            this.error = error;
        }
        public String getError() {
            return this.error;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
    }
}
