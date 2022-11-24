package guru.springframework.commands;

import org.slf4j.Logger;

import javax.validation.constraints.Min;

public class UserCommand {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserCommand.class);
    @Min(2)
    private int limit;

    public UserCommand(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
