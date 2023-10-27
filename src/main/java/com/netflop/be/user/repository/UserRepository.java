package com.netflop.be.user.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.netflop.be.user.helper.Helper;
import com.netflop.be.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Slf4j
public class UserRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private Helper helper;

    @Value("${netflop.user.createdBy}")
    private String createdBy;

    @Value("${netflop.user.typeUser}")
    private String typeUser;

    @Value("${netflop.user.statusActive}")
    private String statusActive;

    public User save(User user) {
        Date now = new Date();
        user.setCreated_by(createdBy);
        user.setCreated_at(helper.DatetimeFormatUTC(now));
        user.setUpdated_by(createdBy);
        user.setUpdated_at(helper.DatetimeFormatUTC(now));
        user.setType(typeUser);
        user.setStatus(statusActive);
        user.setIs_deleted(false);
        dynamoDBMapper.save(user);
        return user;
    }
    public User findByUserId(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }

    public String deleteByUserId(String userId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(User.class, userId));
        return "User Id:"+userId + " deleted";
    }

    public String updateUser(String userId,User user) {
        user.setId(userId);
        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression().withExpectedEntry("user_id",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(userId)
                        )
                )
        );
        return "User Id:" +userId + " updated";
    }
}