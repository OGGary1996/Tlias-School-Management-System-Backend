package com.kezhang.tliasbackend.common.context;

public class LoginContext {
    private static final ThreadLocal<String> nameThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Integer> idThreadLocal = new ThreadLocal<>();

    public static void setName(String name) {
        nameThreadLocal.set(name);
    }
    public static void setId(Integer id) {
        idThreadLocal.set(id);
    }
    public static String getName() {
        return nameThreadLocal.get();
    }
    public static Integer getId() {
        return idThreadLocal.get();
    }
    public static void clearName() {
        nameThreadLocal.remove();
    }
    public static void clearId() {
        idThreadLocal.remove();
    }
}
