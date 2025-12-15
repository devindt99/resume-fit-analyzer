package io.github.devindt99.resumefit.scoring;

import io.github.devindt99.resumefit.embedding.EmbeddingException;
import io.github.devindt99.resumefit.embedding.MockTextEmbedder;
import io.github.devindt99.resumefit.embedding.TextEmbedder;

import java.util.List;

public class FitScorer {

    private final TextEmbedder primaryEmbedder;
    private final TextEmbedder fallbackEmbedder;

    public FitScorer(TextEmbedder primaryEmbedder) {
        this.primaryEmbedder = primaryEmbedder;
        this.fallbackEmbedder = new MockTextEmbedder();
    }

    public double score(String resumeText, String jobDescriptionText) {
        List<String> inputs = List.of(resumeText, jobDescriptionText);

        try {
            return scoreWithEmbedder(primaryEmbedder, inputs);
        } catch (EmbeddingException primaryFailure) {
            try {
                return scoreWithEmbedder(fallbackEmbedder, inputs);
            } catch (EmbeddingException fallbackFailure) {
                throw new IllegalStateException(
                    "Both primary and fallback embedders failed",
                    fallbackFailure
                );
            }
        }
    }

    private double scoreWithEmbedder(TextEmbedder embedder, List<String> inputs)
            throws EmbeddingException {

        List<float[]> embeddings = embedder.embed(inputs);
        return cosineSimilarity(embeddings.get(0), embeddings.get(1));
    }


    private double cosineSimilarity(float[] a, float[] b) {
        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
