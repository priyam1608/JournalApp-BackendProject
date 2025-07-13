package net.sharma.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String email;
    private Boolean sentimentAnalysis;
}
