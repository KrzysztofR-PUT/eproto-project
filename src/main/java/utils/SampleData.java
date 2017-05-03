package utils;

import models.Course;
import models.Grade;
import models.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by krzysztof on 03/05/2017.
 */
public class SampleData {
    List<Student> students = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<Grade> grades = new ArrayList<>();


    public SampleData() {
        students.add(new Student(111, "Jan", "Kochanowski", new Date()));
        students.add(new Student(112, "Adam", "Mickiewicz", new Date()));
        students.add(new Student(113, "Boleslaw", "Prus", new Date()));

        courses.add(new Course("Programming", "Nowak"));
        courses.add(new Course("Art", "Nowaczek"));

        grades.add(new Grade(courses.get(0), students.get(0), 4.5, new Date()));
        grades.add(new Grade(courses.get(0), students.get(1), 3.0, new Date()));
        grades.add(new Grade(courses.get(1), students.get(0), 5.0, new Date()));
        grades.add(new Grade(courses.get(1), students.get(2), 2.0, new Date()));
    }


    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}
