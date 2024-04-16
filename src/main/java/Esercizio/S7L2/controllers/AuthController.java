package Esercizio.S7L2.controllers;
import Esercizio.S7L2.exceptions.BadRequestException;
import Esercizio.S7L2.payloads.NewUserDTO;
import Esercizio.S7L2.payloads.NewUserRespDTO;
import Esercizio.S7L2.payloads.UserLoginDTO;
import Esercizio.S7L2.payloads.UserLoginResponseDTO;
import Esercizio.S7L2.services.AuthService;
import Esercizio.S7L2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){

        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewUserRespDTO(this.usersService.save(body).getId());
    }

}