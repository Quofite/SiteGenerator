package org.barbaris.generator.controllers;

import org.barbaris.generator.models.PageModel;
import org.barbaris.generator.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    private final UserService service = new UserService();

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if(login != null && !login.isEmpty()) {
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if(login != null && !login.isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("is_hidden", "true");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if(login != null && !login.isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("is_hidden", "true");
            return "register";
        }
    }






    @PostMapping("/register")
    public String postRegister(@RequestParam String login, @RequestParam String email, @RequestParam String pass, Model model) {
        if(com.barbaris.site.controllers.HelpingMethodsController.isValidLogin(login)) {
            if(login.length() > 3) {
                if(pass.length() > 8) {
                    if(com.barbaris.site.controllers.HelpingMethodsController.isEmail(email)) {
                        User user = new User();
                        user.setLogin(login);
                        user.setPassword(pass);
                        user.setEmail(email);

                        if(service.registration(user)) {
                            return "redirect:/";
                        } else {
                            model.addAttribute("error_details", "Аккаунт с таким логином или адрессом электронной почты уже существует.");
                            model.addAttribute("is_hidden", "false");
                            return "register";
                        }
                    } else {
                        model.addAttribute("error_details", "Введённый email не подходит.");
                        model.addAttribute("is_hidden", "false");
                        return "register";
                    }
                } else {
                    model.addAttribute("error_details", "Пароль должен содержать больше 8 знаков..");
                    model.addAttribute("is_hidden", "false");
                    return "register";
                }
            } else {
                model.addAttribute("error_details", "Логин должен содержать больше 3 знаков.");
                model.addAttribute("is_hidden", "false");
                return "register";
            }
        } else {
            model.addAttribute("error_details", "Логин содержит запрещенные слова или словосочетания.");
            model.addAttribute("is_hidden", "false");
            return "register";
        }
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String login, @RequestParam String pass, HttpServletRequest request, HttpServletResponse response, Model model) {
        User userToFind = new User();
        userToFind.setLogin(login);
        userToFind.setPassword(pass);

        if(service.getUser(userToFind).getLogin().equals("login_error")) {
            model.addAttribute("error_details", "Нет аккаунта с таким логином.");
            model.addAttribute("is_hidden", "false");

            return "login";
        } else if(service.getUser(userToFind).getLogin().equals("pass_error")) {
            model.addAttribute("error_details", "Введен неправильный пароль.");
            model.addAttribute("is_hidden", "false");

            return "login";
        } else {
            HttpSession session = request.getSession();
            String sessionLogin = (String) session.getAttribute("login");

            if(sessionLogin == null) {
                session.setAttribute("login", login);
            } else {
                session.removeAttribute("login");

                model.addAttribute("error_details", "Произошла непредвиденная ошибка.");
                model.addAttribute("is_hidden", "false");

                return "login";
            }

            return "redirect:/";
        }
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("login");
        return "redirect:/";
    }

    @PostMapping("/generate")
    public String generation(@RequestBody PageModel page) {
        


        return "redirect:/index";
    }
}
