package alert.covid.models;

import javax.persistence.*;

/**
 * Class Authority to manage user roles
 */
@Entity(name="authorities")
@Access(AccessType.FIELD)
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authority_id;
    private String username;
    private String authority="ROLE_USER";

    /**
     * Getter for authority_id
     * @return long authority_id
     */
    public long getAuthority_id() {
        return authority_id;
    }

    /**
     * Setter for authority_id
     * @param authority_id long authority_id to set
     */
    public void setAuthority_id(long authority_id) {
        this.authority_id = authority_id;
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
     * Getter for authority (role)
     * @return String authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Setter for authority (role)
     * @param authority String authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
