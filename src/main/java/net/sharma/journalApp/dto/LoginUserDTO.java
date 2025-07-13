package net.sharma.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
