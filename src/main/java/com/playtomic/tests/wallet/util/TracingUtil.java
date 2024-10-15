package com.playtomic.tests.wallet.util;

import io.jaegertracing.internal.JaegerObjectFactory;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerSpanContext;
import io.jaegertracing.internal.JaegerTracer;

import java.math.BigInteger;
import java.util.HashMap;

public final class TracingUtil {


    public static JaegerSpan startSpan(JaegerTracer tracer, String spanName, String traceId){
        JaegerTracer.SpanBuilder spanBuilder = tracer.buildSpan(spanName);
        if (traceId != null) {
            long traceIdAsLong = new BigInteger(traceId, 16).longValue();//works with negative and positive values
            JaegerSpanContext sc = new JaegerObjectFactory().createSpanContext(0, traceIdAsLong, traceIdAsLong, 0, (byte) 0, new HashMap<>(), null);
            spanBuilder.asChildOf(sc);
        }
        return spanBuilder.start();
    }
}
