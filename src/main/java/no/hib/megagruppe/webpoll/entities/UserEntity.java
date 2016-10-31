package no.hib.megagruppe.webpoll.entities;

import javax.persistence.*;

@Entity(name = "user")
@Table(schema = "webpoll", name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "fname")
    private String firstName;
    @Column(name = "lname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    
    @Override
    public String toString(){
    	return firstName + " " + lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
