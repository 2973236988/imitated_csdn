package cn.ithedge.csdn.service;

import cn.ithedge.csdn.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    User login(User user);
}
