package com.ecom.app.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserRequest {
        private Integer id;
        private String name;
        private String username;
        private String password;
        private int age;
        private long phone;
        @JsonIgnore
        private String role;
}
