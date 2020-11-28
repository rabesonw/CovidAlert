package alert.covid.models;

import alert.covid.enums.StateCovid;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Class User
 */
@Entity(name="users")
@Access(AccessType.FIELD)
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String password;

    @Enumerated(EnumType.STRING)
    private StateCovid state_user = StateCovid.OK;
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name="was_at",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="location_id")
    ) private List<Location> locations;

    @ManyToMany
    @JoinTable(
            name="passed",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="test_id")
    ) private List<CovidTest> covidTests;

    @ManyToMany(mappedBy = "user_alerts")
    @JsonIgnore
    private List<Alert> alerts_user;

    /**
     * Getter for user_id
     * @return long user_id
     */
    public long getUser_id() {
        return user_id;
    }

    /**
     * Getter for first_name
     * @return String first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Getter for last_name
     * @return String last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Getter for email
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for phone_number
     * @return String phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * Getter for password
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for user_id
     * @param user_id long user_id to set
     */
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    /**
     * Setter first_name
     * @param first_name String to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Setter last_name
     * @param last_name String to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Setter for email
     * @param email String to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter for phone_number
     * @param phone_number String to set
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Setter for password
     * @param password String to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for state_user
     * @return StateCovid state_user
     * @see StateCovid
     */
    public StateCovid getState_user() {
        return state_user;
    }

    /**
     * Setter for state_user
     * @param state_user StateCovid to set
     * @see StateCovid
     */
    public void setState_user(StateCovid state_user) {
        this.state_user = state_user;
    }

    /**
     * Getter for list of locations
     * @return List of locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Setter for list of locations
     * @param locations List of locations to set
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    /**
     * Getter for username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username String username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Checks if user is enabled
     * @return boolean enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Setter for enabled
     * @param enabled boolean to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
