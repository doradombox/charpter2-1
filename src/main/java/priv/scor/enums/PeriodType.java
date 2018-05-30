package priv.scor.enums;

import priv.scor.annotation.FormText;

/**
 * 删除标志
 * @author admin
 *
 */
public enum PeriodType {
    By_year("按年"),
    By_quarter("按季度"),
    By_month("按月份");
    
    private PeriodType(String tString) {
        this.text = tString;
    }
    private String text;

    @FormText
    public String getText() {
        return text;
    }
}
