import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import resources.CoursesResource;
import resources.GradesResource;
import resources.MyResource;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import resources.StudentsResource;
import utils.MongoUtils;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by krzysztof on 07/04/2017.
 */
public class Server {
    public static void main(String[] args) {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8000).build();
        ResourceConfig config = new ResourceConfig(MyResource.class).packages("org.glassfish.jersey.examples.linking").register(DeclarativeLinkingFeature.class);
        config.register(StudentsResource.class);
        config.register(CoursesResource.class);
        config.register(GradesResource.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        MongoUtils.getInstance();
    }
}
