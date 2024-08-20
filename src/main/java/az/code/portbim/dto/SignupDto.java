package az.code.portbim.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
