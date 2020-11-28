package alert.covid.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Location
 */
@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private double latitude;
    private double longitude;
    private LocalDateTime location_date;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {
    }

    @ManyToMany
    @JoinTable(
            name="was_at",
            joinColumns = @JoinColumn(name="location_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    ) private List<User> users;

    /**
     * Getter for location_id
     * @return long location_id
     */
    public long getLocation_id() {
        return location_id;
    }

    /**
     * Setter for location_id
     * @param location_id long location_id to set
     */
    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    /**
     * Getter for latitude
     * @return double latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter for latitude
     * @param latitude double latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter for longitude
     * @return double longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter for longitude
     * @param longitude double longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter for location_date
     * @return LocalDateTime location_date
     */
    public LocalDateTime getLocation_date() {
        return location_date;
    }

    /**
     * Setter for location_date
     * @param location_date LocalDateTime location_date to set
     */
    public void setLocation_date(LocalDateTime location_date) {
        this.location_date = location_date;
    }

    /**
     * Getter for list of users
     * @return List of User
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Setter for list of users
     * @param users List of users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * To String method
     * prints :
     *  Location {latitude=latitude, longitude=longitude}
     * @return String
     */
    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
