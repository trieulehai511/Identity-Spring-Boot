package com.haynes.identifly.service.dto.request;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min =5, message ="USER_NAME_INVALID")
     String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
     String password;
     String firstName;
     String lastName;
     LocalDate dcb;
}
