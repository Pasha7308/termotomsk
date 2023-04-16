package termotomsk.api.yandex.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class YandexServerApiConfig {
    @Bean
    public ClientHeaderInterceptor clientHeaderInterceptor() {
        return new ClientHeaderInterceptor();
    }
}
