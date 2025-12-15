package io.github.devindt99.resumefit.scoring;

/**
 * Utility class for computing cosine similarity between vectors.
 */
public final class CosineSimilarity {

    private CosineSimilarity() {
        // utility class
    }

    public static double compute(float[] a, float[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Vectors must not be null");
        }
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        if (a.length == 0) {
            throw new IllegalArgumentException("Vectors must not be empty");
        }

        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        // Compute dot product and vector magnitudes
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }

        if (normA == 0.0 || normB == 0.0) {
            throw new IllegalArgumentException("Vector norm must not be zero");
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
