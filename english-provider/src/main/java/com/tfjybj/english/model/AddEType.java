package com.tfjybj.english.model;

public enum AddEType {
    LEARN(0,"学单词", 1), CHECK(1,"单词检测", 1), STORECHECK(2,"归仓检测", 2);
    // 成员变量
    private int index;
    private String name;
    private int eNum;
    // 构造方法
    private AddEType(int index,String name, int eNum) {
        this.name = name;
        this.index = index;
        this.eNum = eNum;
    }

    public int getIndex() {
        return index;
    }

    public static int getENum(int index) {
        for (AddEType c : AddEType.values()) {
            if (c.getIndex() == index) {
                return c.eNum;
            }
        }
        return 0;
    }

}
