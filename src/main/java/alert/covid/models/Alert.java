package alert.covid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Class Alert, entity of the table alerts in the database
 */
@Entity(name="alerts")
@Access(AccessType.FIELD)
public class Alert {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long alert_id;
        private String type_alert;
        private Date date_alert;

    @ManyToMany
    @JoinTable(
            name="sent_to",
            joinColumns = @JoinColumn(name="alert_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    ) private List<User> user_alerts;

    /**
     * Getter for alert_id
     * @return long alert_id
     */
    public long getAlert_id() { return alert_id; }

    /**
     * Setter for alert_id
     * @param alert_id long alert_id to set
     */
    public void setAlert_id(long alert_id) { this.alert_id = alert_id; }

    /**
     * Getter for type_alert
     * @return String of type_alert
     */
    public String getType_alert() { return type_alert; }

    /**
     * Setter for type_alert
     * @param type_alert String type_alert to set
     */
    public void setType_alert(String type_alert) { this.type_alert = type_alert; }

    /**
     * Getter for date_alert
     * @return Date date_alert
     */
    public Date getAlert_date() { return date_alert; }

    /**
     * Setter for date_alert
     * @param alert_date the Date date_alert to set
     */
    public void setAlert_date(Date alert_date) { this.date_alert = alert_date; }

}
