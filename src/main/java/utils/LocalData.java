package utils;

import models.Course;
import models.Grade;
import models.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by krzysztof on 27/04/2017.
 */
public class LocalData {

//    private ArrayList<Student> students = new ArrayList<>();
//    private List<Course> courses = new ArrayList<>();
//    private List<Grade> grades = new ArrayList<>();
//    private static LocalData ourInstance = new LocalData();
//
//
//    private LocalData() {
//        loadExampleData();
//    }
//
//
//    public static LocalData getInstance() {
//        return ourInstance;
//    }
//
//    public ArrayList<Student> getStudents() {
//        return students;
//    }
//    public Student getStudentByIndex(int index) {
//        for (Student student : students) {
//            if (index == student.getIndex()) {
//                return student;
//            }
//        }
//        return null;
//    }
//    public List<Grade> getGradesByIndex(int index) {
//        return grades.stream().filter(grade -> grade.getIndex() == index).collect(Collectors.toList());
//    }
//    public void addStudent(Student student) {
//        students.add(student);
//    }
//    public void updateStudent(int index, Student student) {
//        students.forEach(studentOld -> {
//            if (studentOld.getIndex() == index) {
//                studentOld.setName(student.getName());
//                studentOld.setSurname(student.getSurname());
//                studentOld.setBirthdate(student.getBirthdate());
//            }
//        });
//    }
//    public void deleteStudent(int index) {
//        students.removeIf(student -> student.getIndex() == index);
//        grades.removeIf(grade -> grade.getIndex() == index);
//    }
//    public boolean checkIfIndexExists(int index) {
//        for (Student student : students) {
//            if (index == student.getIndex()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//    public Course getCourseById(int id) {
//        for (Course course : courses) {
//            if (id == course.getId()) {
//                return course;
//            }
//        }
//        return null;
//    }
//    public void addCourse(Course course) {
//        courses.add(course);
//    }
//    public void updateCourse(Course course) {
//        courses.forEach(courseOld -> {
//            if (courseOld.getId() == course.getId()) {
//                courseOld.setName(course.getName());
//                courseOld.setLecturer(course.getLecturer());
//            }
//        });
//    }
//    public void updateCourse(int id, Course course) {
//        courses.forEach(courseOld -> {
//            if (courseOld.getId() == id) {
//                courseOld.setName(course.getName());
//                courseOld.setLecturer(course.getLecturer());
//            }
//        });
//    }
//    public void deleteCourse(int id) {
//        courses.removeIf(course -> course.getId() == id);
//        grades.removeIf(grade -> grade.getCourseId() == id);
//    }
//    public boolean checkIfCourseIdExists(int id) {
//        for (Course course : courses) {
//            if (id == course.getId()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public List<Grade> getGrades(int courseId) {
//        return grades.stream().filter(grade -> grade.getCourseId() == courseId).collect(Collectors.toList());
//    }
//    public Grade getGradeById(int id, int courseId) {
//        for (Grade grade : getGrades(courseId)) {
//            if (id == grade.getId()) {
//                return grade;
//            }
//        }
//        return null;
//    }
//    public void addGrade(Grade grade) {
//        grades.add(grade);
//    }
//    public void updateGrade(Grade grade) {
//        grades.forEach(gradeOld -> {
//            if (gradeOld.getId() == grade.getId()) {
//                gradeOld.setCourseId(grade.getCourseId());
//                gradeOld.setIndex(grade.getIndex());
//                gradeOld.setValue(grade.getValue());
//                gradeOld.setDate(grade.getDate());
//            }
//        });
//    }
//    public void updateGrade(int id, Grade grade) {
//        grades.forEach(gradeOld -> {    //TODO: nulls
//            if (gradeOld.getId() == id) {
//                gradeOld.setCourseId(grade.getCourseId());
//                gradeOld.setIndex(grade.getIndex());
//                gradeOld.setValue(grade.getValue());
//                gradeOld.setDate(grade.getDate());
//            }
//        });
//    }
//    public void deleteGrade(int id) {
//        grades.removeIf(grade -> grade.getId() == id);
//    }
//    public boolean checkIfGradeIdExists(int id) {
//        for (Grade grade : grades) {
//            if (id == grade.getId()) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean checkIfGradeIsValid(int id, int courseId) {
//        return(grades.stream().anyMatch(g -> g.getId() == id && g.getCourseId() == courseId));
//    }
//    public boolean checkIfGradeIsValid(Grade grade) {
//        return (/*students.stream().anyMatch(s -> s.getIndex() == grade.getIndex()) &&*/ courses.stream().anyMatch(c -> c.getId() == grade.getCourseId()) ? true : false);
//    }
//
//    private void loadExampleData() {
//        students.add(new Student(111111, "Jan", "Kochanowski", new Date()));
//        students.add(new Student(111221, "Adam", "Mickiewicz", new Date()));
//
//        courses.add(new Course(1, "Programming", "Nowak"));
//        courses.add(new Course(2, "Art", "Nowaczek"));
//
//        grades.add(new Grade(1, 1, 111111, 4.5, new Date()));
//        grades.add(new Grade(2, 1, 111111, 4, new Date()));
//        grades.add(new Grade(3, 2, 111221, 3, new Date()));
//    }
}
