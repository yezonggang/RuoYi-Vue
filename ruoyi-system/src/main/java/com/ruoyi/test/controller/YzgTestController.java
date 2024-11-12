package com.ruoyi.test.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.test.domain.YzgTest;
import com.ruoyi.test.service.IYzgTestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试表aController
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/testyzg/testa")
public class YzgTestController extends BaseController
{
    @Autowired
    private IYzgTestService yzgTestService;

    /**
     * 查询测试表a列表
     */
    @PreAuthorize("@ss.hasPermi('testyzg:testa:list')")
    @GetMapping("/list")
    public TableDataInfo list(YzgTest yzgTest)
    {
        startPage();
        List<YzgTest> list = yzgTestService.selectYzgTestList(yzgTest);
        return getDataTable(list);
    }

    /**
     * 导出测试表a列表
     */
    @PreAuthorize("@ss.hasPermi('testyzg:testa:export')")
    @Log(title = "测试表a", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YzgTest yzgTest)
    {
        List<YzgTest> list = yzgTestService.selectYzgTestList(yzgTest);
        ExcelUtil<YzgTest> util = new ExcelUtil<YzgTest>(YzgTest.class);
        util.exportExcel(response, list, "测试表a数据");
    }

    /**
     * 获取测试表a详细信息
     */
    @PreAuthorize("@ss.hasPermi('testyzg:testa:query')")
    @GetMapping(value = "/{a}")
    public AjaxResult getInfo(@PathVariable("a") Long a)
    {
        return success(yzgTestService.selectYzgTestByA(a));
    }

    /**
     * 新增测试表a
     */
    @PreAuthorize("@ss.hasPermi('testyzg:testa:add')")
    @Log(title = "测试表a", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YzgTest yzgTest)
    {
        return toAjax(yzgTestService.insertYzgTest(yzgTest));
    }

    /**
     * 修改测试表a
     */
    @PreAuthorize("@ss.hasPermi('testyzg:testa:edit')")
    @Log(title = "测试表a", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YzgTest yzgTest)
    {
        return toAjax(yzgTestService.updateYzgTest(yzgTest));
    }

    /**
     * 删除测试表a
     */
    @PreAuthorize("@ss.hasPermi('testyzg:testa:remove')")
    @Log(title = "测试表a", businessType = BusinessType.DELETE)
	@DeleteMapping("/{as}")
    public AjaxResult remove(@PathVariable Long[] as)
    {
        return toAjax(yzgTestService.deleteYzgTestByAs(as));
    }
}
