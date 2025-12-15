package io.github.devindt99.resumefit.embedding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * TextEmbedder implementation backed by the Hugging Face Inference API.
 *
 * This implementation uses the OpenAI-compatible /v1/embeddings endpoint,
 * which is the currently supported and forward-compatible way to obtain
 * vector embeddings from Hugging Face models.
 *
 * The selected model is a sentence-transformer optimized for semantic
 * similarity tasks.
 */
public class HuggingFaceTextEmbedder implements TextEmbedder {

    private static final String EMBEDDINGS_URL =
        "https://api-inference.huggingface.co/v1/embeddings";

    private static final String MODEL_ID =
        "sentence-transformers/all-MiniLM-L6-v2";

    private final String apiToken;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HuggingFaceTextEmbedder(String apiToken) {
        if (apiToken == null || apiToken.isBlank()) {
            throw new IllegalArgumentException("Hugging Face API token must be provided");
        }
        this.apiToken = apiToken;
    }

    @Override
    public List<float[]> embed(List<String> texts) throws EmbeddingException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(EMBEDDINGS_URL);
            request.setHeader("Authorization", "Bearer " + apiToken);
            request.setHeader("Content-Type", "application/json");

            ObjectNode body = objectMapper.createObjectNode();
            body.put("model", MODEL_ID);

            ArrayNode inputArray = body.putArray("input");
            for (String text : texts) {
                inputArray.add(text);
            }

            request.setEntity(
                new StringEntity(body.toString(), StandardCharsets.UTF_8)
            );

            return client.execute(request, response -> {
                int status = response.getCode();
                if (status != 200) {
                    throw new RuntimeException(
                        "Hugging Face API returned status " + status
                    );
                }

                JsonNode root = objectMapper.readTree(
                    response.getEntity().getContent()
                );

                ArrayNode data = (ArrayNode) root.get("data");
                List<float[]> embeddings = new ArrayList<>();

                for (JsonNode item : data) {
                    ArrayNode vectorNode = (ArrayNode) item.get("embedding");
                    float[] vector = new float[vectorNode.size()];
                    for (int i = 0; i < vectorNode.size(); i++) {
                        vector[i] = vectorNode.get(i).floatValue();
                    }
                    embeddings.add(vector);
                }

                return embeddings;
            });

        } catch (Exception e) {
            throw new EmbeddingException(
                "Failed to embed text via Hugging Face",
                e
            );
        }
    }
}
