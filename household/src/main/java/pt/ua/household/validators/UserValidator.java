package pt.ua.household.validators;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.apache.commons.validator.routines.EmailValidator;

import pt.ua.household.forms.RegisterForm;

@Component
public class UserValidator {

    public Model validate(RegisterForm user, Model model, Object getter_user) {
        model.addAttribute("valid", true);

        if (user.getFirstName().equals("")) {
            model.addAttribute("error_firstName", "Required Field First Name!");
            model.addAttribute("valid", false);
        } else if (user.getFirstName().length() < 5 || user.getFirstName().length() > 32) {
            model.addAttribute("error_firstName", "Size invalid!");
            model.addAttribute("valid", false);
        }

        if (user.getLastName().equals("")) {
            model.addAttribute("error_lastName", "Required Field Last Name!");
            model.addAttribute("valid", false);
        } else if (user.getLastName().length() < 5 || user.getLastName().length() > 32) {
            model.addAttribute("error_lastName", "Size invalid!");
            model.addAttribute("valid", false);
        }

        EmailValidator emailValidator = EmailValidator.getInstance();
        if (user.getEmail().equals("")) {
            model.addAttribute("error_email", "Required Field Email!");
            model.addAttribute("valid", false);
        } else if (!emailValidator.isValid(user.getEmail())) {
            model.addAttribute("error_email", "Invalid email!");
            model.addAttribute("valid", false);
        } else if (getter_user != null) {
            model.addAttribute("error_email", "Email in use!");
            model.addAttribute("valid", false);
        }

        if (user.getPassword().equals("")) {
            model.addAttribute("error_password", "Required Field Password!");
            model.addAttribute("valid", false);
        } else if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            model.addAttribute("error_password", "Invalid password size!");
            model.addAttribute("valid", false);
        }

        if (user.getMatchingPassword().equals("")) {
            model.addAttribute("error_matchingPassword", "Required Field Confirm Password!");
            model.addAttribute("valid", false);
        } else if (!user.getMatchingPassword().equals(user.getPassword())) {
            model.addAttribute("error_matchingPassword", "Password not match!");
            model.addAttribute("valid", false);
        }
        return model;
    }

}
