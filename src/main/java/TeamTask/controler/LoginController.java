package TeamTask.controler;

import TeamTask.models.dto.FaceOrAppleLoginRequest;
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


/*

apple id credential:
<ASAuthorizationAppleIDCredential: 0x600001910c60
{ userIdentifier: 001441.4950c57b4cec45a4bd5fd8eb0dc87d98.0637, authorizedScopes: (
)

reserve email
001441.4950c57b4cec45a4bd5fd8eb0dc87d98.0637
001441.4950c57b4cec45a4bd5fd8eb0dc87d98.0637
001441.4950c57b4cec45a4bd5fd8eb0dc87d98.0637
001441.4950c57b4cec45a4bd5fd8eb0dc87d98.0637


idTokenString
eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRhbW5qYW4uVGVhbVRhc2tIZXJva3UiLCJleHAiOjE2MDA0NTUzMzQsImlhdCI6MTYwMDM2ODkzNCwic3ViIjoiMDAxNDQxLjQ5NTBjNTdiNGNlYzQ1YTRiZDVmZDhlYjBkYzg3ZDk4LjA2MzciLCJub25jZSI6I/////jA5NTcwMzk4ZGNjNTg4OTRkYTc3ODljNDBmMTgxMGEwNGIzNTM4MzkwNGZmZjdlNWFjNTgzN2ZjYjFhNjNkMGIiLCJjX2hhc2giOiJkbkZWNEN0QTJ3SjFpREFXVnh4ellBIiwiZW1haWwiOiJkYW1uamFuLm1hcmtvdmljQHlhaG9vLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTYwMDM2ODkzNCwibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.EtwPl-l_jPJjEp0qgThbzYjstRF3LT4kb3M1ESau6gX5wQ8mVuw1KXCQ3q4nWzXsjLWUfEXFeNfUNPW6vDpDexIaROdqwU4Ek_Cwgu3KXU2tmQziUHRZXE7HvABvO6AMKm-mAmom7zkbeeyh14kBEy4eQu1LyWmU9KyzcK70okwINCsGN5oAqM1aj-gBQE6lIkHh2lKuaxK-OLq1NtlNs8i3h3XD8Fd2b_3ycwnnagvZcTfENK5Q2lt2asmYSchOOPpurTqamfr-P14UmWCAlwxgBt2H1EnEPmtFDGBHvb8pz39egJy_vAREqQUwJyysC4T08t9mbtD42gUpZYlHgw
eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRhbW5qYW4uVGVhbVRhc2tIZXJva3UiLCJleHAiOjE2MDA0NTU1NDIsImlhdCI6MTYwMDM2OTE0Miwic3ViIjoiMDAxNDQxLjQ5NTBjNTdiNGNlYzQ1YTRiZDVmZDhlYjBkYzg3ZDk4LjA2MzciLCJub25jZSI6I/////mQ1NDA3ZTdmOWQ2Njk1MzZmODAyZjA5MWRkMGY4MzYyMmYzMjE4MzNhMTc0ODA0MDgxMzY2Yjc4OWM4YTE0OTAiLCJjX2hhc2giOiJyMkZmRTNtMzdMX2FFMEZpNnBka3p3IiwiZW1haWwiOiJkYW1uamFuLm1hcmtvdmljQHlhaG9vLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTYwMDM2OTE0Miwibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.Ru4QqldxknQ85mawUUGZCr1w4pdZXWSz3x5dqe0FNahwnlLKY8R-iqRvCE9jRthzzTQt4giiwfyiN9j8CO8JWGQaO30ZRzP1jZox4FFpxHVhhkqKKVTlomFV-sRdSvwFLYO5j8PNUQcQ3CWeX5KAcIJiouojPWehSKoLF9CpCMPwRRBoUpcLd1nd9yLjZbiomh_Rbp23ADKwNg2dLEKMyjrg9W3x4P4oErYCKyEWZBCsTBSpypz7J7MtuxixLYNcc2q11xDtB3GPmEa8pQ3E-gdBfo4lMMQCcFP-0L2sa-vXbX_sl0ZNfbzrVOysc_B_o8YFCy034Js4ohoNXLGl_w
eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRhbW5qYW4uVGVhbVRhc2tIZXJva3UiLCJleHAiOjE2MDA0NjA2NTYsImlhdCI6MTYwMDM3NDI1Niwic3ViIjoiMDAxNDQxLjQ5NTBjNTdiNGNlYzQ1YTRiZDVmZDhlYjBkYzg3ZDk4LjA2MzciLCJub25jZSI6I/////jk3NDgwMDQ1NjBhYTQxZWU4ZGFjNzU0Y2MxMGZlNjY4MDI3NjZkZWYxOWRmOGY1MGFlNjlhZTJhYjEyOWQ2ZDAiLCJjX2hhc2giOiJDSkU3bVdPQ2U5MDMzVkxkY0hVQlhnIiwiZW1haWwiOiJkYW1uamFuLm1hcmtvdmljQHlhaG9vLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTYwMDM3NDI1Niwibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.OWTAP-C0zpfwBGuk2wqlz4BzhkFd-U__Ap5X9Tku1yWXD6PgMudyjxqleVQRibkK9FLc-rCJwRqNYSUQPj6oRcZw38FmrURxiWhGL5SU1qp4jQe0jQU8fQIHZNk0jb1LG_EU3Orpk3bD8lkTC5ftE2k3Io9b0GJ--KKsQgPyqZIYDGoya1gLEX2GOo-M6oYBcgV9CGgfcsa0vS19DXbBMy2Camg1c6SWrkeVLUOgu4llBNnxCb5qRnEuZE5-6V92wZYQMpfvOyCi-rd2y_YTNMjp3uHvThrm-pV5wKnmtBeU549hZShJANX7mxu2TfoUeT5yMo0Pc3Wf3p8Q0MPoqQ
eyJraWQiOiJlWGF1bm1MIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmRhbW5qYW4uVGVhbVRhc2tIZXJva3UiLCJleHAiOjE2MDA0NjA4NzgsImlhdCI6MTYwMDM3NDQ3OCwic3ViIjoiMDAxNDQxLjQ5NTBjNTdiNGNlYzQ1YTRiZDVmZDhlYjBkYzg3ZDk4LjA2MzciLCJub25jZSI6IjVjODFlYjE0NjgyMTYxNTdmZDA1Mjg5OWJmMTE0MjNhYzAyODQ3YTJmYTFiNGYxNDc1YmMwN2Q2NTRhODY5MmUiLCJjX2hhc2giOiJGZHB2RlIzbTk5czRKQWRfSllYS3F3IiwiZW1haWwiOiJkYW1uamFuLm1hcmtvdmljQHlhaG9vLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTYwMDM3NDQ3OCwibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.sSGQUaKgtUpzdSPJ35qyGm-nS4uMarXD-t6qhquiSFbeBIZFHqDSYgrXveUyC1tYSyYeryntIjIo9C5JoIL4gJJgxLeuMWpNwtrxCC5rKvAtKkxBJ63nhmNMkhX_Au-3YVreEpGQ6KZ7YRft38M_wS9z_B8YkCyaaVyezc9Xj_p7dD4Zs8Qsy2Vs1i9OIpZD2Tfq-qWFBs_3efd6GR2CfGSGVPJOPkbrCexEPJLVmNEVVVi5J7ROOIXk4TRZrJGKFx1u701kP72u9Q4z07TChQWD7srB-SzEAZcoMv5mpPZcXKct8lPiWpKUrmG52LVtIVptpkKDhDQcWFMfnMVQfg


Useridentiferfinal:
ccfe133f2ec1a4cdf8e0cd4af77fc2669.0.mruur.7l6h7YwLodg3ZiULMXbLoQ
cdfbdc62dc7dc41879e79f11662cd2854.0.mruur.QvUMXZrEfYZwoJR8zdiBkQ


 */
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;


    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
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
        final UserDetails userDetails = userService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        MyLoginDetails myLoginDetails = (MyLoginDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new LoginResponse(myLoginDetails.getId(), jwt, myLoginDetails.getUsername(), myLoginDetails.getUserFirstName(),
                myLoginDetails.getImages().getId_image(), myLoginDetails.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()), myLoginDetails.getTeams().getId_team(), myLoginDetails.getTeams().getName_team()));

    }

    @RequestMapping(value = "/loginFacebookOrAppleUser", method = RequestMethod.POST)
    public ResponseEntity<?> loginFacebookOrAppleUser(@Valid @RequestBody FaceOrAppleLoginRequest faceOrAppleLoginRequest) throws Exception {

        Authentication authentication = null;
        LoginResponse loginResponse = userService.getLoggedInUser(new String(String.valueOf(faceOrAppleLoginRequest.getUser_id())));
        final UserDetails userDetails = userService
                .loadUserByUsername(faceOrAppleLoginRequest.getUserName());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        loginResponse.setJwt(jwt);
        return ResponseEntity.ok(loginResponse);

    }

}
