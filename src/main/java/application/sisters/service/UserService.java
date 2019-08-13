package application.sisters.service;

import application.sisters.domain.User;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserService extends GeneralService<User> {

    User findUserByEmail(String email);
}
