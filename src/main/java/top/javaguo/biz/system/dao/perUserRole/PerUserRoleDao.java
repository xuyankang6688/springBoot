package top.javaguo.biz.system.dao.perUserRole;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import top.javaguo.biz.system.bean.PerUserRole;

/**
 * @describe 权限-用户角色
 * @author javaGuo
 * @date 2018-03-28
 */
@Mapper
public interface PerUserRoleDao {

	/** 根据条件查询所有 **/
	@SelectProvider(type = PerUserRoleMapper.class, method = "selectAll")
	public List<PerUserRole> selectAll(@Param("bean") PerUserRole bean);

	/** 根据条件查询所有的个数 **/
	@SelectProvider(type = PerUserRoleMapper.class, method = "selectTotal")
	public int selectTotal(@Param("bean") PerUserRole bean);

	/** 添加 **/
	@InsertProvider(type = PerUserRoleMapper.class, method = "insert")
	public int insert(@Param("bean") PerUserRole bean);

	/** 通过id删除 **/
	@DeleteProvider(type = PerUserRoleMapper.class, method = "deleteById")
	public int deleteById(@Param("id") String id);

	/** 通过id修改 **/
	@UpdateProvider(type = PerUserRoleMapper.class, method = "updateById")
	public int updateById(@Param("bean") PerUserRole bean);

	/** 通过id查询 **/
	@SelectProvider(type = PerUserRoleMapper.class, method = "selectById")
	public PerUserRole selectById(@Param("bean") PerUserRole bean);

	/** 通过ids集合删除 **/
	@DeleteProvider(type = PerUserRoleMapper.class, method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/** 批量增加 **/
	@InsertProvider(type = PerUserRoleMapper.class, method = "insertBatch")
	public int insertBatch(@Param("list") List<PerUserRole> list);

	/** 通过用户编号删除 **/
	@DeleteProvider(type = PerUserRoleMapper.class, method = "deleteByUserId")
	public int deleteByUserId(@Param("id") String id);

}
