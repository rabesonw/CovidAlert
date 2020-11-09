package alert.covid.models;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;


@Entity(name="verif_tokens")
@Access(AccessType.FIELD)

public class VerificationToken {
    public static final int EXPIRATION = 60 * 24;
    @Id
    private String token;
    private String username ;
    private Date expiry_date ;
    public Date calculateExpiryDate (int expiryTimeInMinutes ) {
        Calendar cal = Calendar.getInstance () ;
        cal.add(Calendar.MINUTE, expiryTimeInMinutes) ;
        return cal.getTime();
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpiryDate() {
        return expiry_date;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiry_date = expiryDate;
    }

}
