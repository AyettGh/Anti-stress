package org.example.e_commerce_ai.controller;
import org.example.e_commerce_ai.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt", defaultValue = "Tell me about stress") String prompt) {
       // return chatService.getChatResponse(prompt);
        // Ajout du message au prompt
        String promptWithNotice = prompt +  " (Note: This chatbot specializes in answering questions about our available anti-stress products: smart bottle, smart water bottle, smart watch, wraps, and bandanas. " +
                "If you ask about a product we don't currently offer, we will try to add it to our store.)";

        return chatService.getChatResponse(promptWithNotice);

    }
}