package io.github.devindt99.resumefit.embedding;

import java.util.ArrayList;
import java.util.List;

/**
 * Deterministic TextEmbedder implementation for testing and development.
 *
 * Produces stable vector outputs based solely on input text content.
 * No external systems are used.
 */
public class MockTextEmbedder implements TextEmbedder {

    private static final int VECTOR_SIZE = 8;

    @Override
    public List<float[]> embed(List<String> texts) throws EmbeddingException {
        List<float[]> embeddings = new ArrayList<>();

        for (String text : texts) {
            embeddings.add(embedSingle(text));
        }

        return embeddings;
    }

    private float[] embedSingle(String text) {
        float[] vector = new float[VECTOR_SIZE];
        int hash = text.hashCode();

        for (int i = 0; i < VECTOR_SIZE; i++) {
            vector[i] = ((hash >> i) & 1) == 0 ? 0.1f : 0.9f;
        }

        return vector;
    }
}
