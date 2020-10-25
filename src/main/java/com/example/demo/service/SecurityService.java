package com.example.demo.service;

public interface SecurityService
{
    /**
     * Получить логин текущего пользователя
     * @return логин пользователя
     */
    String findLoggedInUsername();

    /**
     * Залогинить текущего пользователя по переданному логину и паролю
     * @param username логин
     * @param password пароль
     */
    void autoLogin(String username, String password);

    /**
     * Залогинить текущего пользователя без пароля
     */
    void autoLoginWithoutPassword(String newUsername);

    /**
     * Разлогинить текущего пользователя
     */
    void logout();
}
