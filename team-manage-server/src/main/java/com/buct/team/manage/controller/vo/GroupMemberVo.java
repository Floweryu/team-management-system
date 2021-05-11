package com.buct.team.manage.controller.vo;

import com.buct.team.manage.entity.Group;
import com.buct.team.manage.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 22:12
 */
@Data
public class GroupMemberVo {

    private Group group;

    private List<User> userList;
}
