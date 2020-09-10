package TeamTask.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import TeamTask.models.dto.LoginRequest;
import TeamTask.models.dto.LoginResponse;
import TeamTask.models.dto.MyLoginDetails;
import TeamTask.models.Role;
import TeamTask.service.UserService;
import TeamTask.util.JwtUtil;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.stream.Collectors;
@Controller
//@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserService myuserService;


    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, UserService myuserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myuserService = myuserService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = null;

        //check if user is logged in
        //UUID uuid = UUID.randomUUID();
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {

            throw new Exception("Password doesn't match username", e.getCause());

        } catch (EntityNotFoundException ew){
            ew.printStackTrace();

        } catch (Exception ef){

            throw new Exception("Username does not exist in the database.", ef.getCause());
        }
        final UserDetails userDetails = myuserService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        MyLoginDetails myLoginDetails = (MyLoginDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new LoginResponse(myLoginDetails.getId(), jwt, myLoginDetails.getUsername(), myLoginDetails.getUserFirstName(),
                myLoginDetails.getId_image(), myLoginDetails.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()), myLoginDetails.getTeams().getId_team()));

    }

}
