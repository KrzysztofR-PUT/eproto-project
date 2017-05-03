package resources;

import models.Course;
import models.Student;
import utils.LocalData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by krzysztof on 27/04/2017.
 */

@Path("/courses")
public class CoursesResource {

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
