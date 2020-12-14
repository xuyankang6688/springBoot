package top.javaguo.biz.system.dao.companyUser;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import top.javaguo.biz.system.bean.CompanyUser;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 车主或者货主公司表
 * @author 
 * @date 2019-12-07
 */
@Mapper
public interface CompanyUserDao extends BaseDao<CompanyUser> {

	/**根据条件查询所有**/
	@SelectProvider(type=CompanyUserMapper.class,method = "selectAll")
	public List<CompanyUser> selectAll(@Param("bean") CompanyUser bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=CompanyUserMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") CompanyUser bean);

	/**添加**/
	@InsertProvider(type=CompanyUserMapper.class,method = "insert")
	public int insert(@Param("bean") CompanyUser bean);

	/**通过id删除**/
	@DeleteProvider(type=CompanyUserMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=CompanyUserMapper.class,method = "updateById")
	public int updateById(@Param("bean") CompanyUser bean);

	/**通过id查询**/
	@SelectProvider(type=CompanyUserMapper.class,method = "selectById")
	public CompanyUser selectById(@Param("bean") CompanyUser bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=CompanyUserMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=CompanyUserMapper.class,method = "selectAllForRelation")
	public List<CompanyUser> selectAllForRelation(@Param("bean") CompanyUser bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=CompanyUserMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") CompanyUser bean);

	/**根据关联id查询**/
	@SelectProvider(type=CompanyUserMapper.class,method = "selectByIdForRelation")
	public CompanyUser selectByIdForRelation(@Param("id") String id);

}
