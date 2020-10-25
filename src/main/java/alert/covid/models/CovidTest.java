package alert.covid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

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

    public void setTest_id(long test_id) { this.covid_test_id = test_id; }
    public long getTest_id() { return covid_test_id; }


    public void setResult_date(Date result_date) { this.result_date = result_date; }
    public Date getResult_date() { return result_date; }

    public boolean getResult() { return result; }
    public void setResult(boolean result) { this.result = result; }

    public void setUsers(List<User> users) { this.users = users; }
    public List<User> getUsers() { return users; }

    public CovidTest(long test_id, BigDecimal latitude, Date result_date, boolean result, List<User> users) {
        this.covid_test_id = test_id;
        this.result_date = result_date;
        this.result = result;
        this.users = users;
    }

    public CovidTest() { }

}
