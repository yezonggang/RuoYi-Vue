package com.ruoyi.test.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.test.mapper.YzgTestMapper;
import com.ruoyi.test.domain.YzgTest;
import com.ruoyi.test.service.IYzgTestService;

/**
 * 测试表aService业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class YzgTestServiceImpl implements IYzgTestService 
{
    @Autowired
    private YzgTestMapper yzgTestMapper;

    /**
     * 查询测试表a
     * 
     * @param a 测试表a主键
     * @return 测试表a
     */
    @Override
    public YzgTest selectYzgTestByA(Long a)
    {
        return yzgTestMapper.selectYzgTestByA(a);
    }

    /**
     * 查询测试表a列表
     * 
     * @param yzgTest 测试表a
     * @return 测试表a
     */
    @Override
    public List<YzgTest> selectYzgTestList(YzgTest yzgTest)
    {
        return yzgTestMapper.selectYzgTestList(yzgTest);
    }

    /**
     * 新增测试表a
     * 
     * @param yzgTest 测试表a
     * @return 结果
     */
    @Override
    public int insertYzgTest(YzgTest yzgTest)
    {
        return yzgTestMapper.insertYzgTest(yzgTest);
    }

    /**
     * 修改测试表a
     * 
     * @param yzgTest 测试表a
     * @return 结果
     */
    @Override
    public int updateYzgTest(YzgTest yzgTest)
    {
        return yzgTestMapper.updateYzgTest(yzgTest);
    }

    /**
     * 批量删除测试表a
     * 
     * @param as 需要删除的测试表a主键
     * @return 结果
     */
    @Override
    public int deleteYzgTestByAs(Long[] as)
    {
        return yzgTestMapper.deleteYzgTestByAs(as);
    }

    /**
     * 删除测试表a信息
     * 
     * @param a 测试表a主键
     * @return 结果
     */
    @Override
    public int deleteYzgTestByA(Long a)
    {
        return yzgTestMapper.deleteYzgTestByA(a);
    }
}
