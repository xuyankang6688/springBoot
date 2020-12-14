package top.javaguo.biz.system.dao.codeAreas;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.CodeAreas;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 城市表
 * @author 
 * @date 2019-10-23
 */
@Mapper
public interface CodeAreasDao extends BaseDao<CodeAreas> {

	/**根据条件查询所有**/
	@SelectProvider(type=CodeAreasMapper.class,method = "selectAll")
	public List<CodeAreas> selectAll(@Param("bean") CodeAreas bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=CodeAreasMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") CodeAreas bean);

	/**添加**/
	@InsertProvider(type=CodeAreasMapper.class,method = "insert")
	public int insert(@Param("bean") CodeAreas bean);

	/**通过id删除**/
	@DeleteProvider(type=CodeAreasMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=CodeAreasMapper.class,method = "updateById")
	public int updateById(@Param("bean") CodeAreas bean);

	/**通过id查询**/
	@SelectProvider(type=CodeAreasMapper.class,method = "selectById")
	public CodeAreas selectById(@Param("bean") CodeAreas bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=CodeAreasMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=CodeAreasMapper.class,method = "selectAllForRelation")
	public List<CodeAreas> selectAllForRelation(@Param("bean") CodeAreas bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=CodeAreasMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") CodeAreas bean);

	/**根据关联id查询**/
	@SelectProvider(type=CodeAreasMapper.class,method = "selectByIdForRelation")
	public CodeAreas selectByIdForRelation(@Param("id") String id);

}
