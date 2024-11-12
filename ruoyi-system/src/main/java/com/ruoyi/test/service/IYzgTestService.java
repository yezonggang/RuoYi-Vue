package com.ruoyi.test.service;

import java.util.List;
import com.ruoyi.test.domain.YzgTest;

/**
 * 测试表aService接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface IYzgTestService 
{
    /**
     * 查询测试表a
     * 
     * @param a 测试表a主键
     * @return 测试表a
     */
    public YzgTest selectYzgTestByA(Long a);

    /**
     * 查询测试表a列表
     * 
     * @param yzgTest 测试表a
     * @return 测试表a集合
     */
    public List<YzgTest> selectYzgTestList(YzgTest yzgTest);

    /**
     * 新增测试表a
     * 
     * @param yzgTest 测试表a
     * @return 结果
     */
    public int insertYzgTest(YzgTest yzgTest);

    /**
     * 修改测试表a
     * 
     * @param yzgTest 测试表a
     * @return 结果
     */
    public int updateYzgTest(YzgTest yzgTest);

    /**
     * 批量删除测试表a
     * 
     * @param as 需要删除的测试表a主键集合
     * @return 结果
     */
    public int deleteYzgTestByAs(Long[] as);

    /**
     * 删除测试表a信息
     * 
     * @param a 测试表a主键
     * @return 结果
     */
    public int deleteYzgTestByA(Long a);
}
