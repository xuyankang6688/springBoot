package top.javaguo.biz.system.dao.appActive;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.AppActive;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 首页活动展示
 * @author 
 * @date 2019-11-25
 */
@Mapper
public interface AppActiveDao extends BaseDao<AppActive> {

	/**根据条件查询所有**/
	@SelectProvider(type=AppActiveMapper.class,method = "selectAll")
	public List<AppActive> selectAll(@Param("bean") AppActive bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=AppActiveMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") AppActive bean);

	/**添加**/
	@InsertProvider(type=AppActiveMapper.class,method = "insert")
	public int insert(@Param("bean") AppActive bean);

	/**通过id删除**/
	@DeleteProvider(type=AppActiveMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=AppActiveMapper.class,method = "updateById")
	public int updateById(@Param("bean") AppActive bean);

	/**通过id查询**/
	@SelectProvider(type=AppActiveMapper.class,method = "selectById")
	public AppActive selectById(@Param("bean") AppActive bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=AppActiveMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=AppActiveMapper.class,method = "selectAllForRelation")
	public List<AppActive> selectAllForRelation(@Param("bean") AppActive bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=AppActiveMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") AppActive bean);

	/**根据关联id查询**/
	@SelectProvider(type=AppActiveMapper.class,method = "selectByIdForRelation")
	public AppActive selectByIdForRelation(@Param("id") String id);

}
