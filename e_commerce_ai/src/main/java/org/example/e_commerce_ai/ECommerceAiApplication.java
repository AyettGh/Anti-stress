package org.example.e_commerce_ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceAiApplication.class, args);
    }
/*@Bean
    CommandLineRunner commandLineRunner(ChatClient.Builder builder) {
return  args -> {
    var client = builder.build();
   String res= client.prompt("tell me a joke")
            .call()
            .content();
    System.out.println(res);
    };

}
*/
}
