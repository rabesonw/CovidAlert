package alert.covid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Class CovidTest
 */
@Entity(name="covid_tests")
@Access(AccessType.FIELD)
public class CovidTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long covid_test_id;
    private Date result_date;
    private boolean result;

    @ManyToMany(mappedBy = "covidTests")
    @JsonIgnore
    private List<User> users;

    /**
     * Setter for covid_test_id
     * @param test_id long test_id to set
     */
    public void setTest_id(long test_id) { this.covid_test_id = test_id; }

    /**
     * Getter for covid_test_id
     * @return long covid_test_id
     */
    public long getTest_id() { return covid_test_id; }

    /**
     * Setter for result_date
     * @param result_date Date result_date to set
     */
    public void setResult_date(Date result_date) { this.result_date = result_date; }

    /**
     * Getter for result_date
     * @return Date result_date
     */
    public Date getResult_date() { return result_date; }

    /**
     * Getter for result
     * @return boolean result
     */
    public boolean getResult() { return result; }

    /**
     * Setter for result
     * @param result boolean result to set
     */
    public void setResult(boolean result) { this.result = result; }

    /**
     * Setter for list of users
     * @param users List of users to set
     */
    public void setUsers(List<User> users) { this.users = users; }

    /**
     * Getter for list of users
     * @return List of users
     */
    public List<User> getUsers() { return users; }

}
