package com.playtomic.tests.wallet.conf;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.MDCScopeManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfiguration {

    @Bean
    public MDCScopeManager mDCscopeManager(){
        return new MDCScopeManager.Builder().build();
    }

    @Bean
    public JaegerTracer jaegerTracer(MDCScopeManager mdcScopeManager){
        return  new JaegerTracer.Builder("car-pooling").withScopeManager(mdcScopeManager).build();
    }
}
