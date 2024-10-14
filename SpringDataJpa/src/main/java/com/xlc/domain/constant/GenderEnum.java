package com.xlc.domain.constant;

public enum GenderEnum {
    MEN("男", "MEN", 1),
    WOMAN( "女", "WOMAN", 2);
    private String nameCn;
    private String nameEn;
    private Integer value;

    GenderEnum(String nameCn, String nameEn, Integer value) {
        this.nameCn = nameCn;
        this.nameEn = nameEn;
        this.value = value;
    }

    public String getNameCn() {
        return nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + "nameZh=" + nameCn + ", nameCn=" + nameEn + ", value=" + value + "}";
    }
}
