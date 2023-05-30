package gg.alex.spring.dao;

import gg.alex.spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO      {

    private int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }


    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
           ResultSet resultSet = statement.executeQuery(SQL);

           while (resultSet.next()){
               Person person = new Person();

               person.setId(resultSet.getInt("id"));
               person.setAge(resultSet.getInt("age"));
               person.setName(resultSet.getString("pname"));
               person.setEmail(resultSet.getString("email"));

               people.add(person);
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

       public Person show(int id){

        Person person = null;
           try {
               PreparedStatement preparedStatement =
                       connection.prepareStatement("SELECT * FROM Person WHERE id=?");
               preparedStatement.setInt(1, id);

               ResultSet resultSet = preparedStatement.executeQuery();
               resultSet.next();

               person = new Person();

               person.setId(resultSet.getInt("id"));
               person.setAge(resultSet.getInt("age"));
               person.setName(resultSet.getString("pname"));
               person.setEmail(resultSet.getString("email"));



           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

           return person;
       }

    public void save(Person person){

        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person (pname, age, email) VALUES (?, ?, ?)");

                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.setInt(2, person.getAge());

                preparedStatement.executeUpdate();
/*
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'"+ person.getName()+"',"+person.getAge()+",'"+person.getEmail()+"')" ;
            statement.executeUpdate(SQL);

*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void update(int id, Person updatePerson) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET  pname=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setInt(4, id);

        preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void delete(int id) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
