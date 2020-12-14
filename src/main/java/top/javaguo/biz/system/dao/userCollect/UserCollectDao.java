package top.javaguo.biz.system.dao.userCollect;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.UserCollect;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 收藏表
 * @author admin
 * @date 2019-08-13
 */
@Mapper
public interface UserCollectDao extends BaseDao<UserCollect> {

	/**根据条件查询所有**/
	@SelectProvider(type=UserCollectMapper.class,method = "selectAll")
	public List<UserCollect> selectAll(@Param("bean")UserCollect bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=UserCollectMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean")UserCollect bean);

	/**添加**/
	@InsertProvider(type=UserCollectMapper.class,method = "insert")
	public int insert(@Param("bean")UserCollect bean);

	/**通过id删除**/
	@DeleteProvider(type=UserCollectMapper.class,method = "deleteById")
	public int deleteById(@Param("id")String id);

	/**通过id修改**/
	@UpdateProvider(type=UserCollectMapper.class,method = "updateById")
	public int updateById(@Param("bean")UserCollect bean);

	/**通过id查询**/
	@SelectProvider(type=UserCollectMapper.class,method = "selectById")
	public UserCollect selectById(@Param("bean")UserCollect bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=UserCollectMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids")String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=UserCollectMapper.class,method = "selectAllForRelation")
	public List<UserCollect> selectAllForRelation(@Param("bean")UserCollect bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=UserCollectMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean")UserCollect bean);

	/**根据关联id查询**/
	@SelectProvider(type=UserCollectMapper.class,method = "selectByIdForRelation")
	public UserCollect selectByIdForRelation(@Param("id")String id);

}
