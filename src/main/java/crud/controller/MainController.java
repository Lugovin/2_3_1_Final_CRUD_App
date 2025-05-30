package crud.controller;

import crud.form.UserForm;
import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    public UserService userService;

    private String errorMessage = "Запоните поля с данными человека.";
    User editUser;




    @RequestMapping(value = {"/usersList", "/"}, method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("users", userService.allU());
        return "usersList";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "addUser";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("userForm") UserForm userForm) {
        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String email = userForm.getEmail();
        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0
                && email != null && email.length() > 0) {
            User newUser = new User(firstName, lastName, email);
            userService.addU(newUser);
            return "redirect:/usersList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addUser";
    }


    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String showDeleteUserPage(@PathVariable("id") Long id) {
        User user = userService.getByIdU(id);
        userService.deleteU(user);
        return "redirect:/usersList";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserForm(Model model, @PathVariable("id") Long id) {
        editUser = userService.getByIdU(id);
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "editUser";
    }

    @RequestMapping(value = {"/editUser"}, method = RequestMethod.POST)
    public String editPerson(@ModelAttribute("userForm") UserForm userForm) {

        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String email = userForm.getEmail();

        if (firstName != null && firstName.length() > 0) {
            editUser.setName(firstName);
        }
        if (lastName != null && lastName.length() > 0) {
            editUser.setLastName(lastName);
        }
        if (email != null && email.length() > 0) {
            editUser.setEmail(email);
        }
        userService.editU(editUser);

        return "redirect:/usersList";
    }
}
