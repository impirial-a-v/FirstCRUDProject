package gg.alex.spring.dao;

import gg.alex.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO      {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }
//              подключение без использования Spring JDBC
    /*  ---------------------------------------------------------------------------------
    private int PEOPLE_COUNT;

    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    ---------------------------------------------------------------------------------------------
*/

    public Optional<Person> show(String email){                                        // метод для валидации  email
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[] {email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }



    public List<Person> index(){
return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());

//              подключение без использования Spring JDBC
        /*---------------------------------------------------------------------------------------------
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
        ---------------------------------------------------------------------------------------------
        */
    }

       public Person show(int person_id){

        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{person_id}, new PersonMapper())
                .stream().findAny().orElse(null);
//              подключение без использования Spring JDBC
/* ---------------------------------------------------------------------------------------------
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
 -------------------------------------------------------------------------------------------------------
           */
       }

    public void save(Person person){

       jdbcTemplate.update("INSERT INTO Person (pname, age, email, address) VALUES (?, ?, ?, ?)", person.getName(),
                person.getAge(), person.getEmail(), person.getAddress());

        //              подключение без использования Spring JDBC
/* ---------------------------------------------------------------------------------------------
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person (pname, age, email) VALUES (?, ?, ?)");

                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.setInt(2, person.getAge());

                preparedStatement.executeUpdate();

//            Statement statement = connection.createStatement();
//           String SQL = "INSERT INTO Person VALUES(" + 1 + ",'"+ person.getName()+"',"+person.getAge()+",'"+person.getEmail()+"')" ;
//           statement.executeUpdate(SQL);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        -------------------------------------------------------------------------------------------------------
                */
    }

    public void update(int person_id, Person updatePerson) {


        jdbcTemplate.update("UPDATE Person SET  pname=?, age=?, email=?, address=? WHERE person_id=?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), updatePerson.getAddress(), person_id);

        //              подключение без использования Spring JDBC
/* ---------------------------------------------------------------------------------------------
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

  -------------------------------------------------------------------------------------------------------
                */
    }

    public void delete(int person_id) {

        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);


        //              подключение без использования Spring JDBC
/* ---------------------------------------------------------------------------------------------
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE person_id=?");
            preparedStatement.setInt(1, person_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

  -------------------------------------------------------------------------------------------------------
                */
    }





    /*
    public void testMultipleUpdate() {

        List<Person> people = create1000People();
        long before = System.currentTimeMillis();

        for (Person person: people){
            jdbcTemplate.update("INSERT INTO Person (pname, age, email) VALUES (?, ?, ?)", person.getName(),
                    person.getAge(), person.getEmail());
        }
        long after = System.currentTimeMillis();

        System.out.println("Time: "+(after- before));



    }




    public void testBatchUpdate() {

        List<Person> people = create1000People();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person (pname, age, email) VALUES (?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

                ps.setString(1, people.get(i).getName());
                ps.setString(3, people.get(i).getEmail());
                ps.setInt(2, people.get(i).getAge());

            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });


        long after = System.currentTimeMillis();

        System.out.println("Time: "+(after- before));

    }

    private List<Person> create1000People() {

        List<Person> people = new ArrayList<>();

        for (int i = 0; i<1000; i++){

            people.add(new Person(i,"Name"+i, 12, "test"+i+"@gmail.com"));

        }
    return people;
    }
*/
}
