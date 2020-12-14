package top.javaguo.biz.system.dao.perResource;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import top.javaguo.biz.system.bean.PerResource;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 权限-资源
 * @author javaGuo
 * @date 2018-03-20
 */
@Mapper
public interface PerResourceDao extends BaseDao<PerResource> {

    /** 根据条件查询所有 **/
    @SelectProvider(type = PerResourceMapper.class, method = "selectAll")
    public List<PerResource> selectAll(@Param("bean") PerResource bean);

    /** 根据条件查询所有的个数 **/
    @SelectProvider(type = PerResourceMapper.class, method = "selectTotal")
    public int selectTotal(@Param("bean") PerResource bean);

    /** 添加 **/
    @InsertProvider(type = PerResourceMapper.class, method = "insert")
    public int insert(@Param("bean") PerResource bean);

    /** 通过id删除 **/
    @DeleteProvider(type = PerResourceMapper.class, method = "deleteById")
    public int deleteById(@Param("id") String id);

    /** 通过id修改 **/
    @UpdateProvider(type = PerResourceMapper.class, method = "updateById")
    public int updateById(@Param("bean") PerResource bean);

    /** 通过id查询 **/
    @SelectProvider(type = PerResourceMapper.class, method = "selectById")
    public PerResource selectById(@Param("bean") PerResource bean);

    /** 通过ids集合删除 **/
    @DeleteProvider(type = PerResourceMapper.class, method = "deleteByIds")
    public int deleteByIds(@Param("ids") String ids);

    /**
     * @Example 批量增加例子
     * 批量增加
     * **/
    @InsertProvider(type = PerResourceMapper.class, method = "insertBatch")
    public int insertBatch(@Param("list") List<PerResource> list);

    /** @Example: mybatis映射一对多例子所需单元 **/
    @Select("SELECT\n" + "	pr.* \n" + "FROM\n" + "	per_resource pr \n" + "WHERE\n"
            + "	id IN ( SELECT prr.per_resource_id FROM per_role_resource prr WHERE prr.per_role_id = #{per_role_id} )")
    public List<PerResource> selectAllForOneToMany(@Param("perRoleId") String perRoleId);

}
