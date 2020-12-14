package top.javaguo.biz.system.dao.perRoleResource;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import top.javaguo.biz.system.bean.PerRoleResource;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 权限-角色资源
 * @author javaGuo
 * @date 2018-03-27
 */
@Mapper
public interface PerRoleResourceDao extends BaseDao<PerRoleResource> {

    /** 根据条件查询所有 **/
    @SelectProvider(type = PerRoleResourceMapper.class, method = "selectAll")
    public List<PerRoleResource> selectAll(@Param("bean") PerRoleResource bean);

    /** 根据条件查询所有的个数 **/
    @SelectProvider(type = PerRoleResourceMapper.class, method = "selectTotal")
    public int selectTotal(@Param("bean") PerRoleResource bean);

    /** 添加 **/
    @InsertProvider(type = PerRoleResourceMapper.class, method = "insert")
    public int insert(@Param("bean") PerRoleResource bean);

    /** 通过id删除 **/
    @DeleteProvider(type = PerRoleResourceMapper.class, method = "deleteById")
    public int deleteById(@Param("id") String id);

    /** 通过id修改 **/
    @UpdateProvider(type = PerRoleResourceMapper.class, method = "updateById")
    public int updateById(@Param("bean") PerRoleResource bean);

    /** 通过id查询 **/
    @SelectProvider(type = PerRoleResourceMapper.class, method = "selectById")
    public PerRoleResource selectById(@Param("bean") PerRoleResource bean);

    /** 通过ids集合删除 **/
    @DeleteProvider(type = PerRoleResourceMapper.class, method = "deleteByIds")
    public int deleteByIds(@Param("ids") String ids);

    /** 批量增加 **/
    @InsertProvider(type = PerRoleResourceMapper.class, method = "insertBatch")
    public int insertBatch(@Param("list") List<PerRoleResource> list);

    /** 通过角色编号删除 **/
    @DeleteProvider(type = PerRoleResourceMapper.class, method = "deleteByPerRoleId")
    public int deleteByPerRoleId(@Param("id") String id);
}
