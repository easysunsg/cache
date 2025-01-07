package com.example.cache.service;

import com.example.cache.entity.User;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        System.out.println("从数据库中获取用户信息");
        return userRepository.findById(id);
    }

    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        System.out.println("更新缓存中的用户信息");
        return userRepository.update(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        System.out.println("从缓存中删除用户信息");
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void clearAllCache() {
        System.out.println("清除所有缓存");
    }

    public User saveUser(User user) {
        System.out.println("保存用户信息到数据库");
        return userRepository.save(user);
    }
}

