package io.github.devindt99.resumefit.embedding;

/**
 * Signals a failure during text embedding.
 *
 * This exception represents errors originating from external systems
 * or embedding implementations and is treated as a recoverable failure.
 */
public class EmbeddingException extends Exception {

    public EmbeddingException(String message) {
        super(message);
    }

    public EmbeddingException(String message, Throwable cause) {
        super(message, cause);
    }
}
