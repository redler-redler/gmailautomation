package model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class User {
    String username;
    String password;
}
