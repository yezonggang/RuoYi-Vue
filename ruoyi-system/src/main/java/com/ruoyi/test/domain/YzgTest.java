package com.ruoyi.test.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试表a对象 yzg_test
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public class YzgTest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** commenta */
    @Excel(name = "commenta")
    private Long a;

    /** commentb */
    @Excel(name = "commentb")
    private String b;

    public void setA(Long a) 
    {
        this.a = a;
    }

    public Long getA() 
    {
        return a;
    }
    public void setB(String b) 
    {
        this.b = b;
    }

    public String getB() 
    {
        return b;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("a", getA())
            .append("b", getB())
            .toString();
    }
}
