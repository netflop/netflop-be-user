package com.netflop.be.user.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "netflop-user-dev")
public class User {
    @DynamoDBHashKey(attributeName = "user_id")
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute(attributeName = "email")
    private String email;
    @DynamoDBAttribute(attributeName = "first_name")
    private String first_name;
    @DynamoDBAttribute(attributeName = "last_name")
    private String last_name;
    @DynamoDBAttribute(attributeName = "phone_number")
    private String phone_number;
    @DynamoDBAttribute(attributeName = "status")
    private String status;
    @DynamoDBAttribute(attributeName = "type")
    private String type;
    @DynamoDBAttribute(attributeName = "created_by")
    private String created_by;
    @DynamoDBAttribute(attributeName = "created_at")
    private String created_at;
    @DynamoDBAttribute(attributeName = "updated_by")
    private String updated_by;
    @DynamoDBAttribute(attributeName = "updated_at")
    private String updated_at;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "is_deleted")
    private Boolean is_deleted = false;
}