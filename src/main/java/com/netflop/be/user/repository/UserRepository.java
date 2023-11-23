package com.netflop.be.user.repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.netflop.be.user.helper.AppMapper;
import com.netflop.be.user.helper.Helper;
import com.netflop.be.user.model.User;
import com.netflop.be.user.model.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import static com.netflop.be.user.helper.Constants.*;

@Repository
@Slf4j
public class UserRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private Helper helper;
    public UserResponse save(User user) {
        Date now = new Date();
        user.setCreatedBy(CREATED_BY);
        user.setCreatedAt(helper.DatetimeFormatUTC(now));
        user.setUpdatedBy(CREATED_BY);
        user.setUpdatedAt(helper.DatetimeFormatUTC(now));
        user.setType(USER);
        user.setStatus(ACTIVE);
        dynamoDBMapper.save(user);
        log.info("User added success:" + user);
        return appMapper.ConvertToUserResponse(user);
    }
    public UserResponse findByUserId(String userId) {
        log.info("UserId = "+ userId+ " is finding...");
        User user = dynamoDBMapper.load(User.class, userId);
        log.info("User = "+ user);

        return appMapper.ConvertToUserResponse(user);
    }

    public String deleteByUserId(String userId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(User.class, userId));
        log.info("User Id:" +userId + " deleted");
        return "User Id:" +userId + " deleted";
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
        log.info("User Id:" +userId + " updated");
        return "User Id:" +userId + " updated";
    }
}