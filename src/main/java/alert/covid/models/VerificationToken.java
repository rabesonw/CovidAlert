package alert.covid.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

/**
 * Class VerificationToken
 */
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

    /**
     * Getter for static attribute EXPIRATION
     * @return int EXPIRATION
     */
    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    /**
     * Getter for token
     * @return String token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter for token
     * @param token String to set
     */
    public void setToken(String token) {
        this.token = token;
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
     * @param username String to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for expiry_date
     * @return Date expiry_date
     */
    public Date getExpiryDate() {
        return expiry_date;
    }

    /**
     * Setter for expiry_date
     * @param expiryDate Date to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiry_date = expiryDate;
    }

}
