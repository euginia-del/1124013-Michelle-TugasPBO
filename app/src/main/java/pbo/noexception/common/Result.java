package pbo.noexception.common;

public class Result<T> {

    private final boolean status;
    private final String errorMessage;
    private final T data;

    private Result(boolean status, String errorMessage, T data) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, null, data);
    }

    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(false, errorMessage, null);
    }

    public boolean isSuccess() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getData() {
        return data;
    }
}
