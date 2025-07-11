package com.kezhang.tliasbackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum FilterWhitelistEnum {
    LOGIN("login","/login"),
    LOGOUT("logout","/logout"),
    REGISTER("register","/register"),

    ;
    private final String name;
    private final String uri;

    // 获取URI的方法
    /*
    * 流程：
    *  1. 获取FilterWhitelist的所有枚举值，为对象数组结构
    *  2. 使用Arrays.stream()将数组转换为Stream流,以供后续操作
    *  3. 使用map()方法将每个枚举值转换为其uri
    *  4. 使用toList()方法将收集到的URI转换为List<String>类型
    * */
    public static List<String> getURIs(){
        FilterWhitelistEnum[] values = FilterWhitelistEnum.values();

        return Arrays.stream(values)
                .map(item -> item.getUri())
                .toList();
    }
}
