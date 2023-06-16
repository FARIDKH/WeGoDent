package com.company.WeGoDent.dto;


import com.company.WeGoDent.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupRoleDTO {

    private long id;

    @NotNull
    @NotBlank(message = "Code role is mandatory")
    @Size(min = 1, max = 50, message = "The code role '${validatedValue}' must be between {min} and {max} characters long")
    private UserType code;

    private String description;

    public UserType getCode() {
        return code;
    }

    public void setCode(UserType code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
