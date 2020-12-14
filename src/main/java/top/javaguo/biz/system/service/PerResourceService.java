package top.javaguo.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import top.javaguo.biz.system.bean.PerResource;
import top.javaguo.biz.system.bean.PerRoleResource;
import top.javaguo.biz.system.dao.perResource.PerResourceDao;
import top.javaguo.biz.system.dao.perRoleResource.PerRoleResourceDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;
import top.javaguo.core.resp.enums.RespMsgEnum;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author javaGuo
 * @describe 权限-资源
 * @date 2018-03-20
 */
@Service
public class PerResourceService extends BaseService<PerResource> {

    /**
     * 权限资源
     **/
    @Autowired
    private PerResourceDao perResourceDao;

    /**
     * 权限角色资源
     **/
    @Autowired
    private PerRoleResourceDao perRoleResourceDao;

    /**
     * 根据条件查询所有
     **/
    public RespBean<Map<String, Object>> selectAll(PerResource perResource) {
        return getResult(perResourceDao, "selectAll", perResource);
    }

    /**
     * @Example 批量增加例子
     * 增加
     * **/
    public RespBean<Map<String, Object>> insert(PerResource perResource) {
        String id = SnowflakeIdWorkerUtil.SIWU.nextId();
        if (!"1".equals(perResource.getMenuLevel())) {
            perResource.setId(id);
            return getResult(perResourceDao, "insert", perResource);
        } else {
            List<PerResource> list = new ArrayList<PerResource>();
            // 资源名
            String resNameTemp = perResource.getResName();
            // 资源介绍
            String resIntroduceTemp = perResource.getResIntroduce();
            // 资源地址
            String tempResUrl = perResource.getResUrl().substring(
                    0,
                    perResource.getResUrl().lastIndexOf("/") > 0 ?
                    perResource.getResUrl().lastIndexOf("/") : 0
            );
            // 实体类名
            String beanName = perResource.getResUrl().substring(
                    perResource.getResUrl().indexOf("/"),
                    perResource.getResUrl().lastIndexOf("/") + 1
            );
            // 一级对象查
            perResource.setId(id);
            perResource.setMenuLevel("1");
            perResource.setApiUrl(beanName + "select");
            list.add(perResource);
            // 二级对象增
            list.add(
                    new PerResource(
                            SnowflakeIdWorkerUtil.SIWU.nextId(),
                            id,
                            resNameTemp + "新增",
                            resIntroduceTemp + "新增",
                            "0",
                            tempResUrl + "/edit.html",
                            "2",
                            beanName + "insert"
                    )
            );
            // 二级对象删
            list.add(
                    new PerResource(
                            SnowflakeIdWorkerUtil.SIWU.nextId(),
                            id,
                            resNameTemp + "删除",
                            resIntroduceTemp + "删除",
                            "1",
                            "",
                            "2",
                            beanName + "delete"
                    )
            );
            // 二级对象改
            list.add(
                    new PerResource(
                            SnowflakeIdWorkerUtil.SIWU.nextId(),
                            id,
                            resNameTemp + "编辑",
                            resIntroduceTemp + "编辑",
                            "2",
                            tempResUrl + "/edit.html",
                            "2",
                            beanName + "update"
                    )
            );
            return getResult(perResourceDao, "insertBatch", list);
        }
    }

    /**
     * 通过id删除
     **/
    public RespBean<Map<String, Object>> deleteById(String id) {
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
        try {
            PerRoleResource perRoleResource = new PerRoleResource();
            perRoleResource.setPerResourceId(id + "");
            int count = perRoleResourceDao.selectTotal(perRoleResource);
            if (count > 0) {
                respBean.setCode(RespMsgEnum._0000008.getCode());
                respBean.setMsg(RespMsgEnum._0000008.getMsg());
            } else {
                deleteDownLevel(id);
                int result = perResourceDao.deleteById(id);
                if (result > 0)
                    respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
            }
        } catch (Exception e) {
            respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
        }
        return respBean;
    }

    /**
     * 删除当前id下所有下级资源
     **/
    public void deleteDownLevel(String id) {
        // 删除本级前先删除下级
        PerResource perResource = new PerResource();
        perResource.setParentId(id + "");
        List<PerResource> list = perResourceDao.selectAll(perResource);
        String tempIds = "";
        if (list.size() > 0) {
            String ids = "";
            for (int i = 0; i < list.size(); i++) {
                ids = list.get(i).getId();
                tempIds += ids;
                if (i != list.size() - 1)
                    tempIds += ",";
                deleteDownLevel(ids);
            }
            perResourceDao.deleteByIds(tempIds);
        }
    }

    /**
     * 通过id修改
     **/
    public RespBean<Map<String, Object>> updateById(PerResource perResource) {
        return getResult(perResourceDao, "updateById", perResource);
    }

    /**
     * 通过id查询
     **/
    public RespBean<Map<String, Object>> selectById(PerResource perResource) {
        return getResult(perResourceDao, "selectById", perResource);
    }

    /**
     * 通过ids集合删除
     **/
    public RespBean<Map<String, Object>> deleteByIds(String ids) {
        PerResource perResource = new PerResource();
        perResource.setId(ids);
        return getResult(perResourceDao, "deleteByIds", ids);
    }

    /**
     * 获取对应角色的资源ids
     *
     * @非空参数：角色编号roleId
     */
    @GetMapping("/selectResourceIdsByRoleId")
    public RespBean<Map<String, Object>> selectResourceIdsByRoleId(String roleId) {
        if (GuoStringUtil.isEmpty(roleId))
            return GuoRespBeanUtil.initParamNotNullRespBean();
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
        try {
            // 查询所有角色资源表中某roleId的信息
            PerRoleResource perRoleResource = new PerRoleResource();
            perRoleResource.setPerRoleId(roleId + "");
            perRoleResource.setLimit(9999);
            List<PerRoleResource> perRoleResourceList = perRoleResourceDao.selectAll(perRoleResource);
            // 角色对应资源id
            String perResourceIds = "";
            // 判断当前资源是否在属于某角色
            for (int i = 0; i < perRoleResourceList.size(); i++) {
                perResourceIds += perRoleResourceList.get(i).getPerResourceId();
                if (i != perRoleResourceList.size() - 1)
                    perResourceIds += ",";
            }
            respBean.getData().put("perResourceIds", perResourceIds);
            respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
        } catch (Exception e) {
            respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
        }
        return respBean;
    }

    /**
     * 根据权限层级封装到新的集合中
     **/
    private List<PerResource> getList(List<PerResource> list, List<PerResource> tempList, String parentId) {
        for (int i = 0; i < tempList.size(); i++) {
            if (parentId.equals(tempList.get(i).getParentId())) {
                list.add(tempList.get(i));
                list = getList(list, tempList, tempList.get(i).getId());
            }
        }
        return list;
    }

    /**
     * LayUI根据条件查询所有
     **/
    @SuppressWarnings("unchecked")
    public Object selectAllForLayUI(PerResource perResource) {
        Map<String, Object> map = getResult(perResourceDao, "selectAllForLayUI", perResource).getData();
        map.put("data", getList(new ArrayList<PerResource>(), (List<PerResource>) map.get("data"), "0"));
        return map;
    }

    /**
     * 权限-资源编辑界面获取一级二级资源
     **/
    @SuppressWarnings("unchecked")
    @GetMapping("/selectAllForPerResourceEdit")
    public RespBean<Map<String, Object>> selectAllForPerResourceEdit() {
        RespBean<Map<String, Object>> respBean = getResult(perResourceDao, "selectAll", new PerResource());
        respBean.getData().put("list",
                getList(new ArrayList<PerResource>(), (List<PerResource>) respBean.getData().get("list"), "0"));
        return respBean;
    }

}
