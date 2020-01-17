package framework;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int userStatus;
    int status;
}