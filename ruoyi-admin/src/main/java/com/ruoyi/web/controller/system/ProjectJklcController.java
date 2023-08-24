package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.ProjectJklc;
import com.ruoyi.system.service.IProjectJklcService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 监控量测Controller
 * 
 * @author ruoyi
 * @date 2023-08-22
 */
@RestController
@RequestMapping("/system/jklc")
public class ProjectJklcController extends BaseController
{
    @Autowired
    private IProjectJklcService projectJklcService;

    /**
     * 查询监控量测列表
     */
    @PreAuthorize("@ss.hasPermi('system:jklc:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectJklc projectJklc)
    {
        startPage();
        List<ProjectJklc> list = projectJklcService.selectProjectJklcList(projectJklc);
        return getDataTable(list);
    }

    /**
     * 导出监控量测列表
     */
    @PreAuthorize("@ss.hasPermi('system:jklc:export')")
    @Log(title = "监控量测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectJklc projectJklc)
    {
        List<ProjectJklc> list = projectJklcService.selectProjectJklcList(projectJklc);
        ExcelUtil<ProjectJklc> util = new ExcelUtil<ProjectJklc>(ProjectJklc.class);
        util.exportExcel(response, list, "监控量测数据");
    }

    /**
     * 获取监控量测详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:jklc:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(projectJklcService.selectProjectJklcById(id));
    }

    /**
     * 新增监控量测
     */
    @PreAuthorize("@ss.hasPermi('system:jklc:add')")
    @Log(title = "监控量测", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectJklc projectJklc)

    {
        projectJklc.setDeptId(getDeptId());
        return toAjax(projectJklcService.insertProjectJklc(projectJklc));
    }


    /**
     * 修改监控量测
     */
    @PreAuthorize("@ss.hasPermi('system:jklc:edit')")
    @Log(title = "监控量测", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectJklc projectJklc)
    {
        return toAjax(projectJklcService.updateProjectJklc(projectJklc));
    }

    /**
     * 删除监控量测
     */
    @PreAuthorize("@ss.hasPermi('system:jklc:remove')")
    @Log(title = "监控量测", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectJklcService.deleteProjectJklcByIds(ids));
    }
}
