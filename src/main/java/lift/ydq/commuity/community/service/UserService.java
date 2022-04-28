package lift.ydq.commuity.community.service;

import lift.ydq.commuity.community.dto.GithubUser;
import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.dto.UserDTO;
import lift.ydq.commuity.community.mapper.*;
import lift.ydq.commuity.community.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YDQ
 * @create 2022-04-07 16:16
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private FollowMapper followMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }

    public UserDTO selectByAccountId(String accountId) {
        Long userId = userExtMapper.selectByAccountId(accountId);
        User users = userMapper.selectByPrimaryKey(userId);
        Integer questionCount = questionExtMapper.selectQuestionCount(userId);
        Integer commentCount = commentExtMapper.selectCountByCOMMENTATOR(userId);
        FollowExample followExample = new FollowExample();
        followExample.createCriteria()
                .andRequesterEqualTo(userId)
                .andTypeEqualTo(1);
        List<Follow> follows = followMapper.selectByExample(followExample);
        Integer followerCount = follows.size();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setName(users.getName());
        userDTO.setAvatarUrl(users.getAvatarUrl());
        userDTO.setAccountId(users.getAccountId());
        userDTO.setQuestionCount(questionCount);
        userDTO.setCommentCount(commentCount);
        userDTO.setFollowerCount(followerCount);
        return userDTO;
    }

    public void createFollow(Follow follow) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria()
                .andFollowersEqualTo(follow.getFollowers())
                .andRequesterEqualTo(follow.getRequester())
                .andTypeEqualTo(follow.getType());
        List<Follow> follows = followMapper.selectByExample(followExample);
        if (follows.size() == 0){
            followMapper.insert(follow);
        }else {
            followMapper.deleteByExample(followExample);
        }

    }
}
