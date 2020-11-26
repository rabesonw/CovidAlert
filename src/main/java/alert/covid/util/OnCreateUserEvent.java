package alert.covid.util;
import alert.covid.models.User;
import org.springframework.context.ApplicationEvent;

public class OnCreateUserEvent extends ApplicationEvent {

    private String appUrl;
    private User user;

    public OnCreateUserEvent(String appUrl, User user) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public User getUser() {
        return user;
    }

}
