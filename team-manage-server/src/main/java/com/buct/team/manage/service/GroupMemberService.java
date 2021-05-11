package com.buct.team.manage.service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 21:53
 */
public interface GroupMemberService {
    List<String> getUserIdByGroupId(Long id);
}
