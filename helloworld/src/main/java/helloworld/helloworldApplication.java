package helloworld;

import helloworld.health.TemplateHealthCheck;
import helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class helloworldApplication extends Application<helloworldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new helloworldApplication().run(args);
    }

    @Override
    public String getName() {
        return "helloworld";
    }

    @Override
    public void initialize(final Bootstrap<helloworldConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final helloworldConfiguration configuration,
                    final Environment environment) {
        HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
