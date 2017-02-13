package com.mybatis.mapper;

import java.util.List;

import com.mybatis.Paging;
import com.vo.User;

public interface UserMapper {
    
    public void insertUser(User user);
    
    public List<User> selectUserPaging(Paging<User> paging);
    
    public User findByName(User user);
    
    public void deleteUser(User user);
    
    public void updateUser(User user);

}
