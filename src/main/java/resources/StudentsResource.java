package resources;

import models.Grade;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.server.Uri;
import utils.LocalData;
import models.Student;
import utils.MongoUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by krzysztof on 27/04/2017.
 */

@Path("/students")
public class StudentsResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Student> getStudents() {
        return MongoUtils.getInstance().getAllStudents();
    }

    @GET
    @Path("{index}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStudent(@PathParam("index") int index) {
        return MongoUtils.getInstance().getStudent(index);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        return MongoUtils.getInstance().addStudent(student);
    }

    @PUT
    @Path("{index}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyStudent(@PathParam("index") int index, Student student) {
        return MongoUtils.getInstance().modifyStudent(index, student);
    }

    @DELETE
    @Path("{index}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("index") int index) {
        return MongoUtils.getInstance().deleteStudent(index);
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getStudents() {
//        return Response.status(Response.Status.OK).entity(LocalData.getInstance().getStudents()).build();
//
//    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Student> getStudents() {
//        return LocalData.getInstance().getStudents();
//    }
//
//
//    @GET
//    @Path("{index}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getStudent(@PathParam("index") int index) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfIndexExists(index)) {
//            return Response.status(Response.Status.OK).entity(data.getStudentByIndex(index)).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//
////    @GET
////    @Path("{index}")
////    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
////    public Student getStudent(@PathParam("index") int index) {
////        return LocalData.getInstance().getStudentByIndex(index);
////    }
//
//    @GET
//    @Path("{index}/grades")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Grade> getGradesForIndex(@PathParam("index") int index) {
//        return LocalData.getInstance().getGradesByIndex(index);
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addStudent(Student student) {
//        LocalData data = LocalData.getInstance();
//        if (!data.checkIfIndexExists(student.getIndex())) {
//            data.addStudent(student);
//            return Response.status(Response.Status.CREATED).location(URI.create("students/" + String.valueOf(student.getIndex()))).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//
//    @PUT
//    @Path("{index}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response modifyStudent(@PathParam("index") int index, Student student) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfIndexExists(index)) {
//            data.updateStudent(index, student);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @DELETE
//    @Path("{index}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response deleteStudent(@PathParam("index") int index) {
//        LocalData data = LocalData.getInstance();
//        if (data.checkIfIndexExists(index)) {
//            data.deleteStudent(index);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}
