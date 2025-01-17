package gr.hua.dit.ds.rental_management.Controllers;

import gr.hua.dit.ds.rental_management.Entities.Roles;
import gr.hua.dit.ds.rental_management.Entities.User;
import gr.hua.dit.ds.rental_management.Repositories.RoleRepository;
import gr.hua.dit.ds.rental_management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class UserController {

    private final UserService userService;

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserController(RoleRepository roleRepository, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }



    // Φόρμα για δημιουργία νέου χρήστη
    @GetMapping("/register")
    public String newUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "basic/register";// Επιστρέφει την φόρμα εγγραφής του χρήστη
    }

    @PostMapping("/saveNewUser")
    public String saveNewUser(@ModelAttribute User user, Model model){

        // Print roles for debugging
        System.out.println("Roles: " + user.getRoles());

        // Encode the password before saving the user
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);  // Encode password
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);  // Log encoded password
        user.setPassword(encodedPassword);  // Set the encoded password

        // Save the user and get the ID
        Integer id = userService.saveUser(user);

        // Provide a success message
        String message = "New user with '" + id + "' just saved successfully!";
        model.addAttribute("msg", message);

        // Redirect to the index page (or any page you'd like)
        return "index";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "user/users";
    }

    @GetMapping("/user/{user_id}")
    public String showSpecificUser(@PathVariable Long user_id, Model model){
        model.addAttribute("user", userService.getUser(user_id));
        return "user/user";
    }

    @PostMapping("/user/{user_id}")
    public String saveNewUser(@PathVariable Long user_id, @ModelAttribute("user") User user, Model model) {
        User new_user = (User) userService.getUser(user_id);
        new_user.setFirst_name(user.getFirst_name());
        new_user.setLast_name(user.getLast_name());
        new_user.setUsername(user.getUsername());
        userService.updateUser(new_user);
        model.addAttribute("users", userService.getUsers());
        return "user/users"; }


    @GetMapping("/user/role/delete/{user_id}/{role_id}")
    public String deleteRolefromUser(@PathVariable Long user_id, @PathVariable Integer role_id, Model model){
        User user = (User) userService.getUser(user_id);
        Roles role = roleRepository.findById(role_id).get();
        user.getRoles().remove(role);
        System.out.println("Roles: "+user.getRoles());
        userService.updateUser(user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "users/users";

    }
    @GetMapping("/user/role/add/{user_id}/{role_id}")
    public String addNewRole(@PathVariable Long user_id, @PathVariable Integer role_id, Model model){
        User user = (User) userService.getUser(user_id);
        Roles role = roleRepository.findById(role_id).get();
        user.getRoles().add(role);
        System.out.println("Roles: "+user.getRoles());
        userService.updateUser(user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "users/users";

    }












}
