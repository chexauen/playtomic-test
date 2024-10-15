package com.playtomic.tests.wallet.util;

import com.playtomic.tests.wallet.conf.Constants;
import org.slf4j.MDC;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class PlaytomicRestTemplate extends RestTemplate {

    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType)
            throws RestClientException {
        requestEntity.getHeaders().add(Constants.TRACE_ID_HEADER, Optional.ofNullable(MDC.get(Constants.TRACE_ID_MDC_KEY)).orElse(""));
        return super.exchange(requestEntity, responseType);
    }
}
