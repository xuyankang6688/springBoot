package top.javaguo.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javaguo.biz.system.bean.CreateCode;
import top.javaguo.biz.system.service.CreateCodeService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 超级管理员
 * @describe 代码生成
 * @date 2019-01-18
 */
@RestController
@RequestMapping("/system/createCode")
public class CreateCodeController extends BaseController<CreateCode> {

    /**
     * 代码生成
     **/
    @Autowired
    private CreateCodeService createCodeService;

    /**
     * 根据条件查询所有
     **/
    @GetMapping("/selectAll")
    public RespBean<Map<String, Object>> selectAll(CreateCode bean) {
        bean.setOrderBy("create_time");
        return createCodeService.selectAll(bean);
    }

    /**
     * 通过id删除
     *
     * @非空参数：代码生成id
     */
    @PostMapping("/deleteById")
    public RespBean<Map<String, Object>> deleteById(String id) {
        if (GuoStringUtil.isEmpty(id)) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return returnIntercept(createCodeService.deleteById(id), createCodeService);
    }

    /**
     * 通过id查询
     *
     * @非空参数：代码生成id
     */
    @GetMapping("/selectById")
    public RespBean<Map<String, Object>> selectById(CreateCode bean) {
        if (GuoStringUtil.isEmpty(bean.getId())) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return createCodeService.selectById(bean);
    }

    /**
     * 通过ids集合删除
     *
     * @非空参数：代码生成ids
     */
    @PostMapping("/deleteByIds")
    public RespBean<Map<String, Object>> deleteByIds(String ids) {
        if (GuoStringUtil.isEmpty(ids)) {
            return GuoRespBeanUtil.initParamNotNullRespBean();
        }
        return returnIntercept(createCodeService.deleteByIds(ids), createCodeService);
    }

    /**
     * 添加
     *
     * @非空参数：代码生成id
     */
    @PostMapping("/insert")
    public RespBean<Map<String, Object>> insert(CreateCode bean) {
        if (GuoStringUtil.isEmpty(new String[]{
                bean.getAuthor(),
                bean.getDescribes(),
                bean.getTableName(),
                bean.getTableKeyStr(),
                bean.getValueStr()
        })) return GuoRespBeanUtil.initParamNotNullRespBean();
        bean.setClassName(GuoStringUtil.replaceUnderlineToUpper(bean.getTableName(), true));
        bean.setKeyStr(GuoStringUtil.replaceUnderlineToUpper(bean.getTableKeyStr(), false));
        bean.setClassNameLower(bean.getClassName().substring(0, 1).toLowerCase() + bean.getClassName().substring(1));
        bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
        return returnIntercept(createCodeService.insert(bean), createCodeService);
    }

    /**
     * 通过id修改
     *
     * @非空参数：代码生成id
     */
    @PostMapping("/updateById")
    public RespBean<Map<String, Object>> updateById(CreateCode bean) {
        if (GuoStringUtil.isEmpty(new String[]{
                bean.getAuthor(),
                bean.getDescribes(),
                bean.getTableName(),
                bean.getTableKeyStr(),
                bean.getValueStr()
        })) return GuoRespBeanUtil.initParamNotNullRespBean();
        bean.setClassName(GuoStringUtil.replaceUnderlineToUpper(bean.getTableName(), true));
        bean.setKeyStr(GuoStringUtil.replaceUnderlineToUpper(bean.getTableKeyStr(), false));
        bean.setClassNameLower(bean.getClassName().substring(0, 1).toLowerCase() + bean.getClassName().substring(1));
        return returnIntercept(createCodeService.updateById(bean), createCodeService);
    }

    /**
     * LayUI根据条件查询所有
     **/
    @GetMapping("/selectAllForLayUI")
    public Map<String, Object> selectAllForLayUI(CreateCode bean) {
        return createCodeService.selectAllForLayUI(bean);
    }

    /**
     * 生成压缩文件zip
     **/
    @GetMapping("/createCodeZip")
    public RespBean<Map<String, Object>> createCodeZip(HttpServletRequest request, HttpServletResponse response, CreateCode bean) throws Exception {
        if (GuoStringUtil.isEmpty(bean.getId())) return GuoRespBeanUtil.initParamNotNullRespBean();
        return createCodeService.createZip(request, response, bean);
    }

}
