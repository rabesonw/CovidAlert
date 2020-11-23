package alert.covid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime location_date;

    public Location(long location_id, BigDecimal latitude, LocalDateTime location_date) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.location_date = location_date;
    }

    public Location() {
    }

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> users;

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getLocation_date() {
        return location_date;
    }

    public void setLocation_date(LocalDateTime location_date) {
        this.location_date = location_date;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", location_date=" + location_date +
                '}';
    }
}
