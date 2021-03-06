package com.tress.app.init.loggin;


import com.tress.app.init.config.CustomUserDetails;
import com.tress.app.init.entities.role.Role;
import com.tress.app.init.entities.user.User;
import com.tress.app.init.entities.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createNewUser(@Valid User user, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            result.put("status", 0);
        } else {
            user.setRoles(Arrays.asList(new Role("USER"), new Role("ACTUATOR")));
            userService.saveUser(user);
            result.put("successMessage", "User has been registered successfully");
            result.put("status", 1);

        }
        return result;
    }

    @GetMapping("/getUsername")
    public CustomUserDetails getCurrentName(){
        return  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
