package com.tuxt.mytest.service.impl;

import com.tuxt.mytest.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${userIds}")
    private String userIds;

    @Override
    public boolean checkUserInWhiteList(Long userId) {
        if (userIds == null || "".equals(userIds)) {
            return false;
        }

        List<Long> userIdList = Stream.of(userIds.split(",")).map(s -> Long.valueOf(s.trim())).collect(
                Collectors.toList());
        if (userIdList == null || userIdList.size() == 0) {
            return false;
        }
        return userIdList.contains(userId);
    }
}
