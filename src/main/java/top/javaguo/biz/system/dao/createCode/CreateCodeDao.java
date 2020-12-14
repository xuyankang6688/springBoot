package top.javaguo.biz.system.dao.createCode;

import org.apache.ibatis.annotations.*;
import top.javaguo.biz.system.bean.CreateCode;
import top.javaguo.core.biz.dao.BaseDao;

import java.util.List;

/**
 * @describe 代码生成
 * @author 超级管理员
 * @date 2019-01-18
 */
@Mapper
public interface CreateCodeDao extends BaseDao<CreateCode> {

	/**根据条件查询所有**/
	@SelectProvider(type=CreateCodeMapper.class,method = "selectAll")
	public List<CreateCode> selectAll(@Param("bean") CreateCode bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=CreateCodeMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") CreateCode bean);

	/**添加**/
	@InsertProvider(type=CreateCodeMapper.class,method = "insert")
	public int insert(@Param("bean") CreateCode bean);

	/**通过id删除**/
	@DeleteProvider(type=CreateCodeMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=CreateCodeMapper.class,method = "updateById")
	public int updateById(@Param("bean") CreateCode bean);

	/**通过id查询**/
	@SelectProvider(type=CreateCodeMapper.class,method = "selectById")
	public CreateCode selectById(@Param("bean") CreateCode bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=CreateCodeMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=CreateCodeMapper.class,method = "selectAllForRelation")
	public List<CreateCode> selectAllForRelation(@Param("bean") CreateCode bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=CreateCodeMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") CreateCode bean);

	/**根据关联id查询**/
	@SelectProvider(type=CreateCodeMapper.class,method = "selectByIdForRelation")
	public CreateCode selectByIdForRelation(@Param("id") String id);

}
