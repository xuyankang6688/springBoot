package top.javaguo.biz.system.dao.goods;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.Goods;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 货物表
 * @author 
 * @date 2019-12-09
 */
@Mapper
public interface GoodsDao extends BaseDao<Goods> {

	/**根据条件查询所有**/
	@SelectProvider(type=GoodsMapper.class,method = "selectAll")
	public List<Goods> selectAll(@Param("bean") Goods bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=GoodsMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") Goods bean);

	/**添加**/
	@InsertProvider(type=GoodsMapper.class,method = "insert")
	public int insert(@Param("bean") Goods bean);

	/**通过id删除**/
	@DeleteProvider(type=GoodsMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=GoodsMapper.class,method = "updateById")
	public int updateById(@Param("bean") Goods bean);

	/**通过id查询**/
	@SelectProvider(type=GoodsMapper.class,method = "selectById")
	public Goods selectById(@Param("bean") Goods bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=GoodsMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=GoodsMapper.class,method = "selectAllForRelation")
	public List<Goods> selectAllForRelation(@Param("bean") Goods bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=GoodsMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") Goods bean);

	/**根据关联id查询**/
	@SelectProvider(type=GoodsMapper.class,method = "selectByIdForRelation")
	public Goods selectByIdForRelation(@Param("id") String id);

}
