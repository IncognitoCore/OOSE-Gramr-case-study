package oose.gramr.RestConfig;

import com.google.inject.Guice;
import oose.gramr.Guice.AppBinding;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

/**
 * Points to packages where the REST services are located
 *
 * This class covers the following requirement:
 * De applicatie maakt gebruik van de volgende APIs en frameworks:

     JSP
     Servlet v3.0
     JAX-RS v2.0 (REST, JSON)
     Jersey v2.17 (REST)
     Guice v4.0 (Dependency Injection)
     JDBC
     JDBC driver v5.1.34 voor MySQL

 */
@ApplicationPath("/rest")
class RestResourceConfig extends ResourceConfig {
    public static final String JSON_SERIALIZER = "jersey.config.server.provider.packages";
    public static final String JACKSON_JSON_SERIALIZER = "com.fasterxml.jackson.jaxrs.json;service";

    @Inject
    public RestResourceConfig(ServiceLocator serviceLocator) {
        packages(true, "oose.gramr.Photo.Services");
        packages(true, "oose.gramr.Set.Services");
        property(JSON_SERIALIZER, JACKSON_JSON_SERIALIZER);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(Guice.createInjector(new AppBinding()));
    }
}