package utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import models.Course;
import models.Grade;
import models.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        //populateMongoWithSampleData();
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


    //region Student operations
    public boolean indexExists(int index) {
         if (datastore.find(Student.class).field("index").equal(index).countAll() != 0)
            return true;
         else
            return false;
    }

    public List<Student> getAllStudents() {
        return MongoQueries.allStudents(datastore).asList();
    }

    public List<Student> getAllStudentsWithFilter(MultivaluedMap<String, String> params) {
        return MongoQueries.allStudents(params, datastore).asList();
    }

    public Response getStudentGradesForCourse(int index, String courseId) {
        if (indexExists(index)) {
            List<Grade> grades = MongoQueries.allGrades(datastore).asList();
            List<Grade> gradesForIndex = grades.stream().filter(g -> g.getStudent().getIndex() == index && g.getCourse().getId().equals(new ObjectId(courseId))).collect(Collectors.toList());
            return Response.status(Response.Status.OK).entity(gradesForIndex).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getGradesWithFilter(int index, MultivaluedMap<String, String> params) {
        if (indexExists(index)) {
            List<Grade> grades = MongoQueries.allGrades(datastore).asList();
            List<Grade> gradesForIndex = grades.stream().filter(g -> g.getStudent().getIndex() == index).collect(Collectors.toList());
            if (params.containsKey("lt")) {
                gradesForIndex = gradesForIndex.stream().filter(g -> g.getValue() < Double.parseDouble(params.get("lt").get(0))).collect(Collectors.toList());
            }
            if (params.containsKey("gt")) {
                gradesForIndex = gradesForIndex.stream().filter(g -> g.getValue() > Double.parseDouble(params.get("gt").get(0))).collect(Collectors.toList());
            }
            return Response.status(Response.Status.OK).entity(gradesForIndex).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getStudent(int index) {
        if (indexExists(index)) {
            return Response.status(Response.Status.OK).entity(MongoQueries.studentWithIndex(index, datastore).asList().get(0)).build();
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
        if (indexExists(student.getIndex())) {
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
            List<Grade> grades = MongoQueries.allGrades(datastore).asList();
            List<Grade> gradesForIndex = grades.stream().filter(g -> g.getStudent().getIndex() == index).collect(Collectors.toList());

            for (Grade grade : gradesForIndex) {
                Query<Grade> qr = datastore.createQuery(Grade.class).field("_id").equal(grade.getId());
                datastore.delete(qr);
            }

            final Query<Student> query = MongoQueries.studentWithIndex(index, datastore);
            datastore.delete(query);


            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    //endregion

    //region Courses operations
    public boolean courseIdExists(String id) {
        if (id.length() == 24) {
            if (datastore.find(Course.class).field("_id").equal(new ObjectId(id)).limit(1).countAll() != 0)
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    public List<Course> getAllCourses() {
        return MongoQueries.allCourses(datastore).asList();
    }

    public Response getCourse(String id) {
        if (courseIdExists(id)) {
            return Response.status(Response.Status.OK).entity(MongoQueries.courseWithId(id, datastore).asList()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public List<Course> getCoursesWithFilter(MultivaluedMap<String, String> params) {
        return MongoQueries.allCoursesForLecturer(params, datastore).asList();
    }

    public Response addCourse(Course course) {
        if (!courseIdExists(course.getId().toString())) {
            datastore.save(course);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    public Response modifyCourse(String id, Course course) {
        if (courseIdExists(id)) {
            final Query<Course> query = MongoQueries.courseWithId(id, datastore);
            final UpdateOperations<Course> updateOperations = datastore.createUpdateOperations(Course.class)
                    .set("name", course.getName())
                    .set("lecturer", course.getLecturer());
            final UpdateResults results = datastore.update(query, updateOperations);
            System.out.println(results);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response deleteCourse(String id) {
        if (courseIdExists(id)) {

            List<Grade> grades = MongoQueries.allGrades(datastore).asList();
            List<Grade> gradesForIndex = grades.stream().filter(g -> g.getCourse().equals(new ObjectId(id))).collect(Collectors.toList());

            for (Grade grade : gradesForIndex) {
                Query<Grade> qr = datastore.createQuery(Grade.class).field("_id").equal(grade.getId());
                datastore.delete(qr);
            }

            final Query<Course> query = MongoQueries.courseWithId(id, datastore);
            datastore.delete(query);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    //endregion

    //region Grades operations
    public boolean gradeIdExists(String id) {
        if (id.length() == 24) {
            if (datastore.find(Grade.class).field("_id").equal(new ObjectId(id)).asList().size() != 0)
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    public Response getGrades(String courseId) {
        if (courseIdExists(courseId)) {
            List<Grade> grades = MongoQueries.allGradesForCourse(courseId, datastore).asList();
            List<Grade> filtered = grades.stream().filter(g -> g.getCourse().getId().equals(new ObjectId(courseId))).collect(Collectors.toList());
            return Response.status(Response.Status.OK).entity(filtered).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getGrade(String courseId, String gradeId) {
        if (courseIdExists(courseId) && gradeIdExists(gradeId)) {
            List<Grade> grades = MongoQueries.allGradesForCourse(courseId, datastore).asList();
            List<Grade> filtered = grades.stream().filter(g -> g.getCourse().getId().equals(new ObjectId(courseId)) && g.getId().equals(new ObjectId(gradeId))).collect(Collectors.toList());
            return Response.status(Response.Status.OK).entity(filtered).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    public Response addGrade(String courseId, Grade grade) {
        if (courseIdExists(courseId)) {
            grade.setId(new ObjectId());
            grade.setCourse(MongoQueries.courseWithId(courseId, datastore).asList().get(0));
            grade.setStudent(MongoQueries.studentWithIndex(grade.getStudent().getIndex(), datastore).asList().get(0));
            datastore.save(grade);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response modifyGrade(String courseId, String gradeId, Grade grade) {
        if (courseIdExists(courseId) && gradeIdExists(gradeId)) {
            grade.setStudent(MongoQueries.studentWithIndex(grade.getStudent().getIndex(), datastore).asList().get(0));
            List<Grade> grades = MongoQueries.allGradesForCourse(courseId, datastore).asList();
            Grade retreived = grades.stream().filter(g -> g.getCourse().getId().equals(new ObjectId(courseId)) && g.getId().equals(new ObjectId(gradeId))).collect(Collectors.toList()).get(0);
            final UpdateOperations<Grade> updateOperations = datastore.createUpdateOperations(Grade.class)
                    .set("value", grade.getValue())
                    .set("date", grade.getDate())
                    .set("student", grade.getStudent());
            final UpdateResults results = datastore.update(retreived, updateOperations);
            System.out.println(results);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response deleteGrade(String courseId, String gradeId) {
        if (courseIdExists(courseId) && gradeIdExists(gradeId)) {
            List<Grade> grades = MongoQueries.allGradesForCourse(courseId, datastore).asList();
            Grade grade = grades.stream().filter(g -> g.getCourse().getId().equals(new ObjectId(courseId)) && g.getId().equals(new ObjectId(gradeId))).collect(Collectors.toList()).get(0);
            Query<Grade> qr = datastore.createQuery(Grade.class).field("_id").equal(grade.getId());
            datastore.delete(qr);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    //endregion
}
