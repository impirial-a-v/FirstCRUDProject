package gg.alex.spring.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int person_id;
    @NotEmpty(message = "Name not should be empty")
    @Size(min=2, max = 30, message="name should be between 2 and 30 characters")
    private String name;
@Min(value = 0, message = "age should be greater than 0")
    private int age;
@NotEmpty(message = "e-mail should not to be empty")
@Email(message = "E-mail should be valid")
    private String email;

    public Person(int person_id, String name, int age, String email) {
        this.person_id = person_id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(){}

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
