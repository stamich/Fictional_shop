package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.multishop.model.User;
import pl.multishop.model.UserProfile;
import pl.multishop.service.UserProfileService;
import pl.multishop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * This class of presentation layer provides user controller.
 * @author Michal Stawarski
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("roles")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    /**
     * This method lists all existing users (REST - GET request).
     */
    @RequestMapping(value = { "/login", "/listUsers"}, method = RequestMethod.GET)
    public String listUsers(ModelMap modelMap){
        List<User> users = userService.findAllUsers();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "usersList";
    }

    /**
     * This method provides the medium to add a new user (REST - GET request).
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if(!userService.isUserSSOUnique(user.getUserId(), user.getsSoId())){
            FieldError ssoError = new FieldError("user","sSoId",messageSource.getMessage("non.unique.sSoId", new String[]{user.getsSoId()}, Locale.getDefault()));
            bindingResult.addError(ssoError);
            return "registration";
        }

        modelMap.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        modelMap.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationSuccess";
    }

    /**
     * This method provides the medium to update an existing user (REST - GET request).
     */
    @RequestMapping(value = { "/edit-user-{sSoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String sSoId, ModelMap modelMap) {
        User user = userService.findBySSO(sSoId);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{sSoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap, @PathVariable String sSoId) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

		//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		/*if(!userService.isUserSSOUnique(user.getUserId(), user.getsSoId())){
			FieldError sSoError =new FieldError("user","sSoId",messageSource.getMessage("non.unique.sSoId", new String[]{user.getsSoId()}, Locale.getDefault()));
		    bindingResult.addError(sSoError);
			return "registration";
		}*/

        userService.updateUser(user);

        modelMap.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "registrationSuccess";
    }

    /**
     * This method deletes an user by it's SSOID value (REST - GET request).
     */
    @RequestMapping(value = { "/delete-user-{sSoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String sSoId) {
        userService.deleteUserBySSO(sSoId);
        return "redirect:/listUsers";
    }


    /**
     * This method provides UserProfile list to views.
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect (REST - GET request).
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap modelMap) {
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/listUsers";
        }
    }

    /**
     * This method handles logout requests (REST - GET request).
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
