package pl.edu.pjatk.zaj2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@ComponentScan
@Configuration
public class Config {
    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }
}
