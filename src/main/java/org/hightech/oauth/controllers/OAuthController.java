package org.hightech.oauth.controllers;

import org.hightech.oauth.entities.User;
import org.hightech.oauth.services.RoleService;
import org.hightech.oauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

/*
* Oauth controller
*/
@Controller
@RequestMapping(value = "/oauth")
public class OAuthController {

    /*
    * Inject instances
    */
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "")
    public String getHomePage() {
        return "index";
    }

    //--------------------------- User Crud ---------------------------
    /*
     * Users list controller endpoint
     */
    @GetMapping(value = "/users")
    public String sendHome(@RequestParam(value = "search", required = false) String searchQuery, Model model) {
        // Fetch users from database
        Collection<User> users = this.userService.getUsers();
        // Check search query
        if (searchQuery != null && !searchQuery.isEmpty()) {
            users = this.userService.getUsers(searchQuery);
        } else {
            users = this.userService.getUsers();
        }
        // Set attributes in the model
        model.addAttribute("users", users);
        return "home";
    }

    /*
     * User form controller endpoint
     * Save user
     * Check if user existed to provide him in the view for editing
     */
    @GetMapping(value = "/users/save")
    public String sendUserForm(Model model) {
        // Create user object
        User user = new User();
        // Put data in the model
        model.addAttribute("user", user);
        // Provide the view
        return "user-form";
    }

    /*
     * User form controller endpoint
     * Save user
     * Check if user existed to provide him in the view for editing
     */
    @GetMapping(value = "/users/save/{username}")
    public String sendFilledUserForm(@PathVariable(name = "username", required = true) String username, RedirectAttributes redirectAttributes, Model model) {
        // Create user object
        User user = new User();
        // Check user
        if (username != null && !username.isEmpty()) {
            user = this.userService.getUser(username);
            // Check if user exists or not
            if (user == null) {
                // Redirect user with a message
                redirectAttributes.addFlashAttribute("isWarning", true);
                redirectAttributes.addFlashAttribute("message", "No user has been found with username " + username);
                return "redirect:/oauth/users/save";
            }
        }
        // Put data in the model
        model.addAttribute("user", user);
        // Provide the view
        return "user-form";
    }

    /*
     * Save user endpoint controller
     */
    @PostMapping(value = "/users/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, Errors errors, RedirectAttributes redirectAttributes, Model model) {
        // Check form data validation
        if (errors.hasErrors()) {
            // Put data in the model
            model.addAttribute("user", user);
            // Provide the view
            return "user-form";
        }
        /*
         * Save user in the database
         * Set temprory password for the user
         */
        user.setPassword("toortoor");
        user = this.userService.saveUser(user);
        // Set success message
        redirectAttributes.addFlashAttribute("isSuccess", true);
        redirectAttributes.addFlashAttribute("message", "User has been saved successfully");
        // Redirect user
        return "redirect:/oauth/users";
    }

    /*
     * elete user endpoint controller
     */
    @PostMapping(value = "/users/delete")
    public String deleteUser(@RequestParam(name = "username", required = false) String username, RedirectAttributes redirectAttributes) {
        // Check existing of the user for message purpose only before delete
        User user = this.userService.getUser(username);
        if (user != null) {
            // Delete the user and set success message
            this.userService.deleteUser(username);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("message", "User has been deleted successfully");

        } else {
            // Message for not found user
            redirectAttributes.addFlashAttribute("isError", "No user has been with username " + username);
        }
        // Redirect the user
        return "redirect:/oauth/users";
    }

}
