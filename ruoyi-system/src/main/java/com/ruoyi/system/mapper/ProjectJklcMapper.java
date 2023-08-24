package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ProjectJklc;

/**
 * 监控量测Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-22
 */
public interface ProjectJklcMapper 
{
    /**
     * 查询监控量测
     * 
     * @param id 监控量测主键
     * @return 监控量测
     */
    public ProjectJklc selectProjectJklcById(Long id);

    /**
     * 查询监控量测列表
     * 
     * @param projectJklc 监控量测
     * @return 监控量测集合
     */
    public List<ProjectJklc> selectProjectJklcList(ProjectJklc projectJklc);

    /**
     * 新增监控量测
     * 
     * @param projectJklc 监控量测
     * @return 结果
     */
    public int insertProjectJklc(ProjectJklc projectJklc);

    /**
     * 修改监控量测
     * 
     * @param projectJklc 监控量测
     * @return 结果
     */
    public int updateProjectJklc(ProjectJklc projectJklc);

    /**
     * 删除监控量测
     * 
     * @param id 监控量测主键
     * @return 结果
     */
    public int deleteProjectJklcById(Long id);

    /**
     * 批量删除监控量测
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectJklcByIds(Long[] ids);
}
