package com.lynn.seatsystem.util;

import com.password4j.Hash;
import com.password4j.Password;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午6:15
 */
public class PasswordUtils {
    private static final String BCRYPT_ALGORITHM = "bcrypt";
    private static final String PBKDF2_ALGORITHM = "pbkdf2";
    private static final String ARGON2ID_ALGORITHM = "argon2id";
    private static final String SCRYPT_ALGORITHM = "scrypt";

    /**
     * 对密码进行哈希处理
     *
     * @param plainPassword 明文密码
     * @return 哈希后的密码
     */
    public static String hashPassword(String plainPassword) {
        Hash hash = Password.hash(plainPassword).withArgon2();
        return hash.getResult();
    }

    /**
     * 验证密码
     *
     * @param plainPassword  明文密码
     * @param hashedPassword 哈希后的密码
     * @return 密码是否匹配
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return Password.check(plainPassword, hashedPassword).withArgon2();
    }

    /**
     * 使用指定算法对密码进行哈希处理
     *
     * @param plainPassword 明文密码
     * @param algorithm     哈希算法 ("argon2id", "bcrypt", "pbkdf2", "scrypt")
     * @return 哈希后的密码
     */
    public static String hashPassword(String plainPassword, String algorithm) {
        Hash hash = switch (algorithm.toLowerCase()) {
            case BCRYPT_ALGORITHM -> Password.hash(plainPassword).withBcrypt();
            case PBKDF2_ALGORITHM -> Password.hash(plainPassword).withPBKDF2();
            case SCRYPT_ALGORITHM -> Password.hash(plainPassword).withScrypt();
            default -> Password.hash(plainPassword).withArgon2();
        };
        return hash.getResult();
    }

    /**
     * 使用指定算法验证密码
     *
     * @param plainPassword  明文密码
     * @param hashedPassword 哈希后的密码
     * @param algorithm      哈希算法 ("argon2id", "bcrypt", "pbkdf2", "scrypt")
     * @return 密码是否匹配
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword, String algorithm) {
        return switch (algorithm.toLowerCase()) {
            case BCRYPT_ALGORITHM -> Password.check(plainPassword, hashedPassword).withBcrypt();
            case PBKDF2_ALGORITHM -> Password.check(plainPassword, hashedPassword).withPBKDF2();
            case SCRYPT_ALGORITHM -> Password.check(plainPassword, hashedPassword).withScrypt();
            default -> Password.check(plainPassword, hashedPassword).withArgon2();
        };
    }
}
