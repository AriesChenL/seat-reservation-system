package com.lynn.seatsystem.util;

import com.lynn.seatsystem.domain.entity.User;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午6:59
 */
public class UserContext {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUser(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static User getUser() {
        return USER_THREAD_LOCAL.get();
    }

    public static void clear() {
        USER_THREAD_LOCAL.remove();
    }
}
