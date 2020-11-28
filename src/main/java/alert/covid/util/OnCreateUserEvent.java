package alert.covid.util;

import alert.covid.models.User;
import org.springframework.context.ApplicationEvent;

/**
 * Class OnCreateUserEvent
 */
public class OnCreateUserEvent extends ApplicationEvent {

    private String appUrl;
    private User user;

    /**
     * Constructor for OnCreateUserEvent
     * @param appUrl String of the app url
     * @param user User to set
     */
    public OnCreateUserEvent(String appUrl, User user) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;
    }

    /**
     * Getter for appUrl
     * @return String
     */
    public String getAppUrl() {
        return appUrl;
    }

    /**
     * Getter for user
     * @return User
     */
    public User getUser() {
        return user;
    }

}
