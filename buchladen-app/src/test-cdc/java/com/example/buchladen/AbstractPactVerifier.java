package com.example.buchladen;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;

@Provider("buchladen")
@PactFolder("../pacts")
@IgnoreNoPactsToVerify
public abstract class AbstractPactVerifier {

    private static final String TARGET_HOST = "localhost";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @BeforeEach
    protected final void before(final PactVerificationContext context, @LocalServerPort final int port) {
        if(context == null) { // is NULL, if no pact has been defined
            return;
        }
        log.debug("Initialisiere Test-Target: 'http://{}:{}'", TARGET_HOST, port);
        context.setTarget(new HttpTestTarget(TARGET_HOST, port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerification(final PactVerificationContext context) {
        if(context == null) { // is NULL, if no pact has been defined
            return;
        }
        log.info("Verifiziere CDC-Test für folgenden Consumer '{}': '{}'", context.getConsumer().getName(), context.getInteraction().getDescription());
        context.verifyInteraction();
    }
}
