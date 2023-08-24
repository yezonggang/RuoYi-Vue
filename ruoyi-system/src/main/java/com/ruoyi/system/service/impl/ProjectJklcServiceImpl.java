package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProjectJklcMapper;
import com.ruoyi.system.domain.ProjectJklc;
import com.ruoyi.system.service.IProjectJklcService;

/**
 * 监控量测Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-22
 */
@Service
public class ProjectJklcServiceImpl implements IProjectJklcService 
{
    @Autowired
    private ProjectJklcMapper projectJklcMapper;

    /**
     * 查询监控量测
     * 
     * @param id 监控量测主键
     * @return 监控量测
     */
    @Override
    @DataScope(deptAlias = "project_jklc")
    public ProjectJklc selectProjectJklcById(Long id)
    {
        return projectJklcMapper.selectProjectJklcById(id);
    }

    /**
     * 查询监控量测列表
     * 
     * @param projectJklc 监控量测
     * @return 监控量测
     */
    @Override
    @DataScope(deptAlias = "project_jklc")
    public List<ProjectJklc> selectProjectJklcList(ProjectJklc projectJklc)
    {
        return projectJklcMapper.selectProjectJklcList(projectJklc);
    }

    /**
     * 新增监控量测
     * 
     * @param projectJklc 监控量测
     * @return 结果
     */
    @Override
    public int insertProjectJklc(ProjectJklc projectJklc)
    {
        return projectJklcMapper.insertProjectJklc(projectJklc);
    }

    /**
     * 修改监控量测
     * 
     * @param projectJklc 监控量测
     * @return 结果
     */
    @Override
    public int updateProjectJklc(ProjectJklc projectJklc)
    {
        return projectJklcMapper.updateProjectJklc(projectJklc);
    }

    /**
     * 批量删除监控量测
     * 
     * @param ids 需要删除的监控量测主键
     * @return 结果
     */
    @Override
    public int deleteProjectJklcByIds(Long[] ids)
    {
        return projectJklcMapper.deleteProjectJklcByIds(ids);
    }

    /**
     * 删除监控量测信息
     * 
     * @param id 监控量测主键
     * @return 结果
     */
    @Override
    public int deleteProjectJklcById(Long id)
    {
        return projectJklcMapper.deleteProjectJklcById(id);
    }
}
