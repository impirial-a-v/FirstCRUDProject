package gg.alex.spring.dao;

import gg.alex.spring.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setPerson_id(resultSet.getInt("person_id"));
        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("pname"));
        person.setEmail(resultSet.getString("email"));

        return  person;
    }
}
