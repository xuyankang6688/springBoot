package top.javaguo.biz.system.dao.sysUser;

import org.apache.ibatis.annotations.*;
import top.javaguo.biz.system.bean.SysUser;
import top.javaguo.core.biz.dao.BaseDao;

import java.util.List;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-08
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {

	/** 根据条件查询所有 **/
	@SelectProvider(type = SysUserMapper.class, method = "selectAll")
	public List<SysUser> selectAll(@Param("bean") SysUser bean);

	/** 根据条件查询所有的个数 **/
	@SelectProvider(type = SysUserMapper.class, method = "selectTotal")
	public int selectTotal(@Param("bean") SysUser bean);

	/** 添加 **/
	@InsertProvider(type = SysUserMapper.class, method = "insert")
	public int insert(@Param("bean") SysUser bean);

	/** 通过id删除 **/
	@DeleteProvider(type = SysUserMapper.class, method = "deleteById")
	public int deleteById(@Param("id") String id);

	/** 通过id修改 **/
	@UpdateProvider(type = SysUserMapper.class, method = "updateById")
	public int updateById(@Param("bean") SysUser bean);

	/** 通过id查询 **/
	@SelectProvider(type = SysUserMapper.class, method = "selectById")
	public SysUser selectById(@Param("bean") SysUser bean);

	/** 通过ids集合删除 **/
	@DeleteProvider(type = SysUserMapper.class, method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/** @Example: mybatis映射一对一例子 **/
	@Select("select * from sys_user")
	@Results({
			@Result(property = "department", column = "department_id", one = @One(select = "top.javaguo.biz.system.dao.department.DepartmentDao.selectById")) })
	public List<SysUser> selectAllForOneToOne();

}
