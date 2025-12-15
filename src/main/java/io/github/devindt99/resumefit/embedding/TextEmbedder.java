package io.github.devindt99.resumefit.embedding;

import java.util.List;
/**
 * Converts text inputs into numerical vector embeddings.
 *
 * Implementations may use local models, remote APIs, or mock logic.
 * Failures are reported via EmbeddingException and must be handled by callers.
 */

public interface TextEmbedder {
    /**
     * Converts input texts into numerical vector embeddings.
     *
     * @param texts input strings to embed
     * @return list of float vectors, one per input string
     * @throws EmbeddingException if embedding fails
     */
    List<float[]> embed(List<String> texts) throws EmbeddingException;
}
