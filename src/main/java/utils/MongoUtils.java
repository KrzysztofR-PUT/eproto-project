package utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import models.Course;
import models.Grade;
import models.Student;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by krzysztof on 01/05/2017.
 */
public class MongoUtils {
    private final Datastore datastore;
    private static MongoUtils ourInstance = new MongoUtils();


    private MongoUtils() {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:8004"));
        final Morphia morphia = new Morphia();

        morphia.map(Student.class, Course.class, Grade.class);
        datastore = morphia.createDatastore(mongoClient, "EPROTO");
//        populateMongoWithSampleData();
    }


    public static MongoUtils getInstance() {
        return ourInstance;
    }


    private void populateMongoWithSampleData() {
        SampleData sampleData = new SampleData();
        datastore.save(sampleData.getStudents());
        datastore.save(sampleData.getCourses());
        datastore.save(sampleData.getGrades());
    }

    public boolean indexExists(int index) {
         if (datastore.find(Student.class).field("index").equal(index).countAll() != 0)
            return true;
         else
            return false;
    }

    public List<Student> getAllStudents() {
        return MongoQueries.allStudents(datastore).asList();
    }

    public Response getStudent(int index) {
        if (indexExists(index)) {
            return Response.status(Response.Status.OK).entity(MongoQueries.studentWithIndex(index, datastore).asList()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response addStudent(Student student) {
        if (!indexExists(student.getIndex())) {
            datastore.save(student);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    public Response modifyStudent(int index, Student student) {
        if (!indexExists(student.getIndex())) {
            final Query<Student> studentQuery = MongoQueries.studentWithIndex(index, datastore);
            final UpdateOperations<Student> updateOperations = datastore.createUpdateOperations(Student.class)
                    .set("name", student.getName())
                    .set("surname", student.getSurname())
                    .set("birthdate", student.getBirthdate());
            final UpdateResults results = datastore.update(studentQuery, updateOperations);
            System.out.println(results);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response deleteStudent(int index) {  //TODO: Usuwanie ocen studenta
        if (indexExists(index)) {
            final Query<Student> query = MongoQueries.studentWithIndex(index, datastore);
            datastore.delete(query);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
