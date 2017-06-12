package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.*;
import utils.ObjectIdJaxbAdapter;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 * Created by krzysztof on 07/04/2017.
 */

@Entity("students")
@Indexes(@Index(value = "index", fields = @Field("index")))
@XmlRootElement
public class Student {
    @InjectLinks({
            @InjectLink(value = "students", rel = "parent"),
            @InjectLink(value = "students/{index}", rel = "self")
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    @XmlTransient
    @Id
    //@XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    private ObjectId id;
    private int index;
    private String name;
    private String surname;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date birthdate;

    public Student() {
    }

    public Student(int index, String name, String surname, Date birthdate) {
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    @XmlTransient
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
