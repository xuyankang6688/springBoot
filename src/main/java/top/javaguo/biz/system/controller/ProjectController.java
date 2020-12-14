package top.javaguo.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javaguo.biz.system.bean.Project;
import top.javaguo.biz.system.service.ProjectService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;

import java.util.Map;

/**
 * @author javaGuo
 * @describe 项目
 * @date 2019-01-18
 */
@RestController
@RequestMapping("/system/project")
public class ProjectController extends BaseController<Project> {

    /**
     * 项目
     **/
    @Autowired
    private ProjectService projectService;

    /**
     * 根据条件查询所有
     **/
    @GetMapping("/selectAll")
    public RespBean<Map<String, Object>> selectAll(Project bean) {
        return projectService.selectAll(bean);
    }

    /**
     * 通过id删除
     *
     * @非空参数：项目id
     */
    @PostMapping("/deleteById")
    public RespBean<Map<String, Object>> deleteById(String id) {
        if (GuoStringUtil.isEmpty(id)) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return returnIntercept(projectService.deleteById(id), projectService);
    }

    /**
     * 通过id查询
     *
     * @非空参数：项目id
     */
    @GetMapping("/selectById")
    public RespBean<Map<String, Object>> selectById(Project bean) {
        if (GuoStringUtil.isEmpty(bean.getId())) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return projectService.selectById(bean);
    }

    /**
     * 通过ids集合删除
     *
     * @非空参数：项目ids
     */
    @PostMapping("/deleteByIds")
    public RespBean<Map<String, Object>> deleteByIds(String ids) {
        if (GuoStringUtil.isEmpty(ids)) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return returnIntercept(projectService.deleteByIds(ids), projectService);
    }

    /**
     * 添加
     *
     * @非空参数：项目id
     */
    @PostMapping("/insert")
    public RespBean<Map<String, Object>> insert(Project bean) {
        if (GuoStringUtil.isEmpty(new String[]{bean.getName()})) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
        return returnIntercept(projectService.insert(bean), projectService);
    }

    /**
     * 通过id修改
     *
     * @非空参数：项目id
     */
    @PostMapping("/updateById")
    public RespBean<Map<String, Object>> updateById(Project bean) {
        if (GuoStringUtil.isEmpty(new String[]{bean.getId(), bean.getName()})) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return returnIntercept(projectService.updateById(bean), projectService);
    }

    /**
     * LayUI根据条件查询所有
     **/
    @GetMapping("/selectAllForLayUI")
    public Map<String, Object> selectAllForLayUI(Project bean) {
        return projectService.selectAllForLayUI(bean);
    }


    /**
     * 根据用户Id查询
     **/
    @GetMapping("/selectByUserId")
    public RespBean<Map<String, Object>> selectByUserId(String userId) {
    	if (GuoStringUtil.isEmpty(userId)) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return projectService.selectByUserId(userId);
    }

}
