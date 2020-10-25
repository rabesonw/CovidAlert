package alert.covid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    public long getAlert_id() { return alert_id; }
    public void setAlert_id(long alert_id) { this.alert_id = alert_id; }

    public String getType_alert() { return type_alert; }
    public void setType_alert(String type_alert) { this.type_alert = type_alert; }

    public Date getAlert_date() { return date_alert; }
    public void setAlert_date(Date alert_date) { this.date_alert = alert_date; }

    public Alert(long alert_id, String type_alert, Date date_alert) {
        this.alert_id = alert_id;
        this.type_alert = type_alert;
        this.date_alert = date_alert;
    }
    public Alert() { }


}
