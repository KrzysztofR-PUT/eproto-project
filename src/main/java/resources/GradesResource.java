package resources;

import models.Grade;
import utils.LocalData;
import utils.MongoUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by krzysztof on 27/04/2017.
 */

@Path("/courses/{courseId}/grades")
public class GradesResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGrades(@Context UriInfo info, @PathParam("courseId") String courseId) {
        MultivaluedMap<String, String> params = info.getQueryParameters();
        if (params.size() == 0)
            return MongoUtils.getInstance().getGrades(courseId);
        else
            return MongoUtils.getInstance().getAllGradesWithFilter(courseId, params);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGrade(@PathParam("courseId") String courseId, @PathParam("id") String id) {
        return MongoUtils.getInstance().getGrade(courseId, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade(@PathParam("courseId") String courseId, Grade grade) {
        return MongoUtils.getInstance().addGrade(courseId, grade);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyGrade(@PathParam("courseId") String courseId, @PathParam("id") String id, Grade grade) {
        return MongoUtils.getInstance().modifyGrade(courseId, id, grade);
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteGrade(@PathParam("courseId") String courseId, @PathParam("id") String id) {
        return MongoUtils.getInstance().deleteGrade(courseId, id);
    }


//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getGrades(@PathParam("courseId") int courseId) {
//        return Response.status(Response.Status.OK).entity(LocalData.getInstance().getGrades(courseId)).build();
//    }
//
//
////    @GET
////    @Path("{id}")
////    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
////    public Grade getGrade(@PathParam("courseId") int courseId, @PathParam("id") int id) {
////        return LocalData.getInstance().getGradeById(id, courseId);
////    }
//
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getGrade(@PathParam("courseId") int courseId, @PathParam("id") int id) {
//        //return LocalData.getInstance().getGradeById(id, courseId);
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfGradeIsValid(id, courseId)) {
//            return Response.status(Response.Status.OK).entity(data.getGradeById(id, courseId)).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addGrade(@PathParam("courseId") int courseId, Grade grade) {
//        LocalData data = LocalData.getInstance();
//        if (courseId == grade.getCourseId() && !data.checkIfGradeIdExists(grade.getId()) && data.checkIfGradeIsValid(grade)) {
//            data.addGrade(grade);
//            return Response.status(Response.Status.CREATED).location(URI.create("courses/" + String.valueOf(courseId) + "/grades/" + String.valueOf(grade.getId()))).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response modifyGrade(@PathParam("courseId") int courseId, @PathParam("id") int id, Grade grade) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfGradeIsValid(id, courseId)) {
//            //data.updateGrade(grade);
//            data.updateGrade(id, grade);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response deleteGrade(@PathParam("courseId") int courseId, @PathParam("id") int id) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfGradeIdExists(id)) {    //TODO: check if is in courseID
//            data.deleteGrade(id);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}

