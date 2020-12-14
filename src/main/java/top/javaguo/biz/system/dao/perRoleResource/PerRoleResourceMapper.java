package top.javaguo.biz.system.dao.perRoleResource;

import org.apache.ibatis.annotations.Param;

import top.javaguo.biz.system.bean.PerRoleResource;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 权限-角色资源
 * @author javaGuo
 * @date 2018-05-31
 */
public class PerRoleResourceMapper extends BaseMapper<PerRoleResource> {

    /** 查询条件 **/
    @Override
    public String commonWhere(PerRoleResource bean) {
        return bean == null ? ""
                : " WHERE 1 = 1 " + whereAddFilter("t.id = ", bean.getId())
                + whereAddFilter("t.create_time = ", bean.getCreateTime())
                + whereAddFilter("t.update_time = ", bean.getUpdateTime())
                + whereAddFilter("t.per_role_id = ", bean.getPerRoleId())
                + whereAddFilter("t.per_resource_id = ", bean.getPerResourceId())
                + whereAddFilter("t.create_time >= ", bean.getQueryBeginDate())
                + whereAddFilter("t.create_time < ", bean.getQueryEndDate());
    }

    /** 通过角色编号删除 **/
    public String deleteByPerRoleId(@Param("id") String id) {
        return "delete from per_role_resource where per_role_id =" + id;
    }

}
