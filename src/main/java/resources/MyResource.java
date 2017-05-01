package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by krzysztof on 07/04/2017.
 */

@Path("test")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Testowy...";
    }
}
