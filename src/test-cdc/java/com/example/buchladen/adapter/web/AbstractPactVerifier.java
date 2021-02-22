package com.example.buchladen.adapter.web;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.server.LocalServerPort;

@IgnoreNoPactsToVerify
@Slf4j
public abstract class AbstractPactVerifier {

    abstract protected void handleContextImlp(PactVerificationContext context);

    @BeforeEach
    protected final void before(final PactVerificationContext context, @LocalServerPort final int port) {
        if(context == null){
            log.warn("There were no pacts detected!");
            return;
        }
        context.setTarget(new HttpTestTarget(PactConstants.TARGET_HOST, port));
        log.info("Initializing test target: '{}'", context.getTarget().toString());

        // prepare response
        handleContextImlp(context);
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerification(final PactVerificationContext context){
        if(context == null){ // is NULL, if no pact has been defined
            return;
        }

        log.info("Running test of consumer named '{}': '{}'",
                context.getConsumer().getName(),
                context.getInteraction().getDescription()
        );
        context.verifyInteraction();
    }
}
