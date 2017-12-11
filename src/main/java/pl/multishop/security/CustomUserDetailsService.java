package pl.multishop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.model.User;
import pl.multishop.model.UserProfile;
import pl.multishop.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * This class belongs to the configuration of Spring Security.
 * @author Michal Stawarski
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * This method loads user.
     * @param ssoId
     * @return
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.findBySSO(ssoId);

        if(user == null){

            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getsSoId(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }

    /**
     * This method gets authorites.
     * @param user
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserProfile userProfile : user.getUserProfiles()){

            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }

        return authorities;
    }

}
