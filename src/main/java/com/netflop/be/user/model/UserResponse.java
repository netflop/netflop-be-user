package com.netflop.be.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String status;
    private String type;
    private String created_by;
    private String created_at;
    private String updated_by;
    private String updated_at;
    private Boolean is_deleted;
}
