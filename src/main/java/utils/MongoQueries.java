package utils;

import models.Course;
import models.Grade;
import models.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;

import javax.ws.rs.core.MultivaluedMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by krzysztof on 03/05/2017.
 */
public class MongoQueries {
    static Query<Student> allStudents(Datastore ds) {
        return ds.createQuery(Student.class);
    }
    static Query<Student> allStudents(MultivaluedMap<String, String> params, Datastore ds) {
//        System.out.println(params.get("name").get(0));
//        if (params.containsKey("name") && params.containsKey("surname"))
//            return ds.createQuery(Student.class)
//                    .field("name").equal(params.get("name").get(0))
//                    .field("surname").equal(params.get("surname").get(0));
//        else if (!params.containsKey("surname") && params.containsKey("name"))
//            return ds.createQuery(Student.class)
//                    .field("name").equal(params.get("name").get(0));
//        else
//            return ds.createQuery(Student.class)
//                    .field("surname").equal(params.get("surname").get(0));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Query<Student> query = ds.createQuery(Student.class);
        if (params.containsKey("name"))
            query.field("name").containsIgnoreCase(params.get("name").get(0));
        if (params.containsKey("surname"))
            query.field("surname").containsIgnoreCase(params.get("surname").get(0));
        if (params.containsKey("from")) {
            Date from = new Date();
            try {
                from = df.parse(params.get("from").get(0));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(from);
            query.field("birthdate").greaterThan(from);
        }
        if (params.containsKey("to")) {
            Date to = new Date();
            try {
                to = df.parse(params.get("to").get(0));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            query.field("birthdate").lessThan(to);
        }

        return query;
    }
    static Query<Student> studentWithIndex(int index, Datastore ds) {
        return ds.createQuery(Student.class).field("index").equal(index);
    }

    static Query<Course> allCourses(Datastore ds) {
        return ds.createQuery(Course.class);
    }
    static Query<Course> allCoursesForLecturer(MultivaluedMap<String, String> params, Datastore ds) {
        Query<Course> query = ds.createQuery(Course.class);
        if (params.containsKey("lecturer"))
            query.field("lecturer").containsIgnoreCase(params.get("lecturer").get(0));
        return query;
    }
    static Query<Course> courseWithId(String id, Datastore ds) {
        return ds.createQuery(Course.class).field("_id").equal(new ObjectId(id));
    }
    static Query<Grade> allGrades(Datastore ds) {
        System.out.println("Trying");
        return ds.createQuery(Grade.class);
    }
    static Query<Grade> allGradesForCourse(String id, Datastore ds) {
        System.out.println(id);
        return ds.createQuery(Grade.class);
    }
}
