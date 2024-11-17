package my.code.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeNameView {
    @Value("#{target.id + ' ' + target.firstName}")
    String getFirstNameAndId();

    String getLastName();
}
