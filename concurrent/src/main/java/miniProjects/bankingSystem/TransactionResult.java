package miniProjects.bankingSystem;

public class TransactionResult {
    private final boolean success;
    private final String message;
    private final String errorCode;

    public TransactionResult(boolean success, String message, String errorCode) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "TransactionResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}

