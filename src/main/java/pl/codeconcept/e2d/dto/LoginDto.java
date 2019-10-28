package pl.codeconcept.e2d.dto;


import lombok.Data;
import org.springframework.stereotype.Repository;
@Data
public class LoginDto {

    private String username;
    private String password;

}
