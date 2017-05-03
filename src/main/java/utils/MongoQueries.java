package utils;

import models.Student;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * Created by krzysztof on 03/05/2017.
 */
public class MongoQueries {
    static Query<Student> allStudents(Datastore ds) {
        return ds.createQuery(Student.class);
    }

    static Query<Student> studentWithIndex(int index, Datastore ds) {
        return ds.createQuery(Student.class).field("index").equal(index);
    }
}
