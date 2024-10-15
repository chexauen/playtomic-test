package com.playtomic.tests.wallet.conf;

import com.playtomic.tests.wallet.service.stripe.StripeRestTemplateResponseErrorHandler;
import com.playtomic.tests.wallet.util.PlaytomicRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StripeConfiguration {

    @Bean
    public RestTemplate defaultRestTemplate() {
        RestTemplate restTemplate = new PlaytomicRestTemplate();
        restTemplate.setErrorHandler(new StripeRestTemplateResponseErrorHandler());
        return restTemplate;
    }
}
