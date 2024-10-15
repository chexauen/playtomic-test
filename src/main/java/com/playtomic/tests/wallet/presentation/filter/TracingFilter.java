package com.playtomic.tests.wallet.presentation.filter;

import com.playtomic.tests.wallet.conf.Constants;
import com.playtomic.tests.wallet.util.TracingUtil;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Scope;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TracingFilter extends OncePerRequestFilter {

    @Autowired
    private JaegerTracer tracer;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        JaegerSpan span = TracingUtil.startSpan(tracer, request.getContextPath(), request.getHeader(Constants.TRACE_ID_HEADER));
        try (Scope scope = tracer.scopeManager().activate(span)) {
            filterChain.doFilter(request, response);
        } finally {
            span.finish();
        }
    }
}