package org.app;

public class NoSubscriptionException extends RuntimeException {
    public NoSubscriptionException(final String message) {
        super(message);
    }
}
