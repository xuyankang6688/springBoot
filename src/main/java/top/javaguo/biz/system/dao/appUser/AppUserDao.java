package top.javaguo.biz.system.dao.appUser;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.AppUser;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 用户表
 * @author admin
 * @date 2019-08-13
 */
@Mapper
public interface AppUserDao extends BaseDao<AppUser> {

	/**根据条件查询所有**/
	@SelectProvider(type=AppUserMapper.class,method = "selectAll")
	public List<AppUser> selectAll(@Param("bean")AppUser bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=AppUserMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean")AppUser bean);

	/**添加**/
	@InsertProvider(type=AppUserMapper.class,method = "insert")
	public int insert(@Param("bean")AppUser bean);

	/**通过id删除**/
	@DeleteProvider(type=AppUserMapper.class,method = "deleteById")
	public int deleteById(@Param("id")String id);

	/**通过id修改**/
	@UpdateProvider(type=AppUserMapper.class,method = "updateById")
	public int updateById(@Param("bean")AppUser bean);

	/**通过id查询**/
	@SelectProvider(type=AppUserMapper.class,method = "selectById")
	public AppUser selectById(@Param("bean")AppUser bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=AppUserMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids")String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=AppUserMapper.class,method = "selectAllForRelation")
	public List<AppUser> selectAllForRelation(@Param("bean")AppUser bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=AppUserMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean")AppUser bean);

	/**根据关联id查询**/
	@SelectProvider(type=AppUserMapper.class,method = "selectByIdForRelation")
	public AppUser selectByIdForRelation(@Param("id")String id);

}
