package se.johan.communitysitev2.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by Johan on 06-Feb-16.
 */

// This class registers the DelegatingFilterProxy to use the springSecurityFilterChain before any other registered Filter.
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
