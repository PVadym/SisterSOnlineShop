package application.sisters.rest;

import lombok.Data;

@Data
public class AuthBody {

    private String email;
    private String password;
}
