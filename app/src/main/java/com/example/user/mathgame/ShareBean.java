package com.example.user.mathgame;

public class ShareBean {
    /**    //三个参数  参数类型 int type    boolean checked     string name
     * type : 6
     * checked : false
     * name : tom
     */

    private int type;
    private boolean checked;
    private String name;

    public ShareBean(int type, boolean checked, String name) {
        this.type = type;
        this.checked = checked;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
