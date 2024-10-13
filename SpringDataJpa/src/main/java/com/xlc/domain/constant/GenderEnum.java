package com.xlc.domain.constant;

public enum GenderEnum {
    MEN("男", "MEN", 1),
    WOMAN( "女", "WOMAN", 2);
    private String nameZh;
    private String nameCn;
    private Integer value;

    GenderEnum(String nameZh, String nameCn, Integer value) {
        this.nameZh = nameZh;
        this.nameCn = nameCn;
        this.value = value;
    }

    public String getNameZh() {
        return nameZh;
    }

    public String getNameCn() {
        return nameCn;
    }

    public Integer getValue() {
        return value;
    }
}
