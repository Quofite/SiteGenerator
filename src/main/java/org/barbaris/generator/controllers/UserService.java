package org.barbaris.generator.controllers;

import org.barbaris.generator.models.IUserService;
import org.barbaris.generator.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {
    @Autowired
    public JdbcTemplate template;

    @Override
    public boolean registration(User user) {
        String check = String.format("SELECT * FROM users WHERE login='%s' or email='%s';", user.getLogin(), user.getEmail());
        List<Map<String, Object>> rows = template.queryForList(check);

        if(rows.isEmpty()) {

            String getIdReq = "SELECT * FROM users;";
            List<Map<String, Object>> idRows = template.queryForList(getIdReq);

            String set = String.format("INSERT INTO users (id, login, email, password) VALUES ('%d', '%s', '%s', '%s');",   idRows.size() + 1,
                    user.getLogin(),
                    user.getEmail(),
                    user.getPassword());
            template.execute(set);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(User user) {
        String findLogin = "SELECT * FROM users WHERE login='" + user.getLogin() + "';";
        List<Map<String, Object>> login_rows = template.queryForList(findLogin);

        if(login_rows.isEmpty()) {
            User errorUser = new User();
            errorUser.setLogin("login_error");
            errorUser.setPassword("no such user");
            return errorUser;
        } else {
            String checkPass = "SELECT * FROM users WHERE login='" + user.getLogin() + "' and password='" + user.getPassword() + "';";
            List<Map<String, Object>> pass_rows = template.queryForList(checkPass);

            if(pass_rows.isEmpty()) {
                User errorUser = new User();
                errorUser.setLogin("pass_error");
                errorUser.setPassword("password is incorrect");
                return errorUser;
            } else {
                User foundUser = new User();
                foundUser.setLogin(pass_rows.get(0).get("login").toString());
                foundUser.setPassword(pass_rows.get(0).get("password").toString());
                return foundUser;
            }
        }
    }
}
