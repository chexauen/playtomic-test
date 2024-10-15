package com.playtomic.tests.wallet.conf;

import com.playtomic.tests.wallet.service.stripe.StripeRestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StripeConfiguration {

    @Bean
    public RestTemplate defaultRestTemplate() {
        return new RestTemplateBuilder()
                        .errorHandler(new StripeRestTemplateResponseErrorHandler())
                        .build();
    }
}
