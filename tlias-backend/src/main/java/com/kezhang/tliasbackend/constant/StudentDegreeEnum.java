package com.kezhang.tliasbackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentDegreeEnum {
    UNDERGRADUATE(0, "Undergraduate"),
    BACHELOR(1, "Bachelor's Degree"),
    MASTER(2, "Master's Degree"),
    DOCTORATE(3, "Doctorate Degree"),
    ;

    private final Integer code;
    private final String label;

    public static String getLabelByCode(Integer code){
        for (StudentDegreeEnum degree : StudentDegreeEnum.values()){
            if (degree.getCode().equals(code)){
                return degree.getLabel();
            }
        }
        return "Unknown Degree";
    }
}
