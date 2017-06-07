package resources;

import models.Course;
import models.Student;
import org.bson.types.ObjectId;
import utils.LocalData;
import utils.MongoUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * Created by krzysztof on 27/04/2017.
 */

@Path("/courses")
public class CoursesResource {

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Course> getCourses() {
//        return MongoUtils.getInstance().getAllCourses();
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Course> getCourses(@Context UriInfo info) {
        MultivaluedMap<String, String> params = info.getQueryParameters();
        if (params.size() != 0) {
            return MongoUtils.getInstance().getCoursesWithFilter(params);
        } else {
            return MongoUtils.getInstance().getAllCourses();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCourse(@PathParam("id") String id) {
        return MongoUtils.getInstance().getCourse(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) {  //TODO: nie dziala
        course.setId(new ObjectId());
        return MongoUtils.getInstance().addCourse(course);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyCourse(@PathParam("id") String id, Course course) {   //TODO: nie dziala
        return MongoUtils.getInstance().modifyCourse(id, course);
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCourse(@PathParam("id") String id) {
        return MongoUtils.getInstance().deleteCourse(id);
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getCourses() {
//        //return LocalData.getInstance().getCourses();
//        return Response.status(Response.Status.OK).entity(LocalData.getInstance().getCourses()).build();
//    }
//
////    @GET
////    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
////    public List<Course> getCourses() {
////        return LocalData.getInstance().getCourses();
////    }
//
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getCourse(@PathParam("id") int id) {
//        //return LocalData.getInstance().getCourseById(id);
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfCourseIdExists(id)) {
//            return Response.status(Response.Status.OK).entity(data.getCourseById(id)).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
////    @GET
////    @Path("{id}")
////    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
////    public Course getCourse(@PathParam("id") int id) {
////        return LocalData.getInstance().getCourseById(id);
////    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addCourse(Course course) {
//        LocalData data = LocalData.getInstance();
//        if (!data.checkIfCourseIdExists(course.getId())) {
//            data.addCourse(course);
//            return Response.status(Response.Status.CREATED).location(URI.create("courses/" + String.valueOf(course.getId()))).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response modifyCourse(@PathParam("id") int id, Course course) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfCourseIdExists(id)) {
//            data.updateCourse(id, course);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response deleteCourse(@PathParam("id") int id) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfCourseIdExists(id)) {
//            data.deleteCourse(id);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}
