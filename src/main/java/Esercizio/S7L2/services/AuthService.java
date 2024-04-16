package Esercizio.S7L2.services;
import org.springframework.security.crypto.password.PasswordEncoder;
import Esercizio.S7L2.entities.User;
import Esercizio.S7L2.exceptions.UnauthorizedException;
import Esercizio.S7L2.payloads.UserLoginDTO;
import Esercizio.S7L2.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){

        User user = this.usersService.findByEmail(payload.email());

        if (bcrypt.matches(payload.password(), user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}