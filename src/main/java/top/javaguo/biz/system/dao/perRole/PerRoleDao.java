package top.javaguo.biz.system.dao.perRole;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import top.javaguo.biz.system.bean.PerRole;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 权限-角色
 * @author javaGuo
 * @date 2018-03-22
 */
@Mapper
public interface PerRoleDao extends BaseDao<PerRole> {

	/** 根据条件查询所有 **/
	@SelectProvider(type = PerRoleMapper.class, method = "selectAll")
	public List<PerRole> selectAll(@Param("bean") PerRole bean);

	/** 根据条件查询所有的个数 **/
	@SelectProvider(type = PerRoleMapper.class, method = "selectTotal")
	public int selectTotal(@Param("bean") PerRole bean);

	/** 添加 **/
	@InsertProvider(type = PerRoleMapper.class, method = "insert")
	public int insert(@Param("bean") PerRole bean);

	/** 通过id删除 **/
	@DeleteProvider(type = PerRoleMapper.class, method = "deleteById")
	public int deleteById(@Param("id") String id);

	/** 通过id修改 **/
	@UpdateProvider(type = PerRoleMapper.class, method = "updateById")
	public int updateById(@Param("bean") PerRole bean);

	/** 通过id查询 **/
	@SelectProvider(type = PerRoleMapper.class, method = "selectById")
	public PerRole selectById(@Param("bean") PerRole bean);

	/** 通过ids集合删除 **/
	@DeleteProvider(type = PerRoleMapper.class, method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/** @Example: mybatis映射一对多例子 **/
	@Select("select * from per_role")
	@Results({
			@Result(property = "perResourceList", column = "id", javaType = List.class, many = @Many(select = "top.javaguo.biz.system.dao.perResource.PerResourceDao.selectAllForOneToMany")) })
	public List<PerRole> selectAllForOneToMany();

}
