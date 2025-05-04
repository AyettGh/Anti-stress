package org.example.e_commerce_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final RestTemplate restTemplate;

    // URL du microservice produit (modifie selon ton cas)
    private static final String PRODUCT_API_URL = "http://localhost:8089/ecommerce/allproduct";

    public ChatService(ChatClient.Builder builder, RestTemplate restTemplate) {
        this.chatClient = builder.build();
        this.restTemplate = restTemplate;
    }

    public String getChatResponse(String prompt) {
        String finalPrompt;

        try {
            // Récupère une liste d'objets Product depuis le microservice
            List<?> products = restTemplate.getForObject(PRODUCT_API_URL, List.class);

            if (products == null || products.isEmpty()) {
                throw new RuntimeException("Empty or null product list.");
            }

            // Extraction des noms
            List<String> productNames = products.stream()
                    .map(product -> ((Map<?, ?>) product).get("name"))
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .toList();

            String productList = String.join(", ", productNames);
            finalPrompt = prompt + " (Note: Our store offers these anti-stress products: " + productList + ")";

        } catch (RuntimeException e) {
            finalPrompt = prompt + " (Note: This chatbot specializes in answering questions about our available anti-stress products: smart bottle, smart water bottle, smart watch, wraps, and bandanas. " +
                    "If you ask about a product we don't currently offer, we will try to add it to our store.)";
        }

        return chatClient.prompt(finalPrompt).call().content();
    }

}
