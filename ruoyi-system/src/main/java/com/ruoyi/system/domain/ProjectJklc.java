package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 监控量测对象 project_jklc
 * 
 * @author ruoyi
 * @date 2023-08-22
 */
public class ProjectJklc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 拱顶下沉 */
    @Excel(name = "拱顶下沉")
    private Long gdxc;

    /** 周边收敛 */
    @Excel(name = "周边收敛")
    private Long zbsl;

    /** 拱脚下沉 */
    @Excel(name = "拱脚下沉")
    private Long gjxc;

    /** 渗水压力 */
    @Excel(name = "渗水压力")
    private Long scyl;

    /** 层间压力 */
    @Excel(name = "层间压力")
    private Long cjyl;

    /** 供架内力 */
    @Excel(name = "供架内力")
    private Long gjnl;

    /** 砼内力 */
    @Excel(name = "砼内力")
    private Long tnl;

    /** 锚杆轴力 */
    @Excel(name = "锚杆轴力")
    private Long mgzl;

    /** 围岩内移 */
    @Excel(name = "围岩内移")
    private Long wynbwy;

    /** 文档地址 */
    private String wddz;

    /** 相关建议 */
    @Excel(name = "相关建议")
    private String xgjy;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGdxc(Long gdxc) 
    {
        this.gdxc = gdxc;
    }

    public Long getGdxc() 
    {
        return gdxc;
    }
    public void setZbsl(Long zbsl) 
    {
        this.zbsl = zbsl;
    }

    public Long getZbsl() 
    {
        return zbsl;
    }
    public void setGjxc(Long gjxc) 
    {
        this.gjxc = gjxc;
    }

    public Long getGjxc() 
    {
        return gjxc;
    }
    public void setScyl(Long scyl) 
    {
        this.scyl = scyl;
    }

    public Long getScyl() 
    {
        return scyl;
    }
    public void setCjyl(Long cjyl) 
    {
        this.cjyl = cjyl;
    }

    public Long getCjyl() 
    {
        return cjyl;
    }
    public void setGjnl(Long gjnl) 
    {
        this.gjnl = gjnl;
    }

    public Long getGjnl() 
    {
        return gjnl;
    }
    public void setTnl(Long tnl) 
    {
        this.tnl = tnl;
    }

    public Long getTnl() 
    {
        return tnl;
    }
    public void setMgzl(Long mgzl) 
    {
        this.mgzl = mgzl;
    }

    public Long getMgzl() 
    {
        return mgzl;
    }
    public void setWynbwy(Long wynbwy) 
    {
        this.wynbwy = wynbwy;
    }

    public Long getWynbwy() 
    {
        return wynbwy;
    }
    public void setWddz(String wddz) 
    {
        this.wddz = wddz;
    }

    public String getWddz() 
    {
        return wddz;
    }
    public void setXgjy(String xgjy) 
    {
        this.xgjy = xgjy;
    }

    public String getXgjy() 
    {
        return xgjy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gdxc", getGdxc())
            .append("zbsl", getZbsl())
            .append("gjxc", getGjxc())
            .append("scyl", getScyl())
            .append("cjyl", getCjyl())
            .append("gjnl", getGjnl())
            .append("tnl", getTnl())
            .append("mgzl", getMgzl())
            .append("wynbwy", getWynbwy())
            .append("wddz", getWddz())
            .append("xgjy", getXgjy())
            .toString();
    }
}
