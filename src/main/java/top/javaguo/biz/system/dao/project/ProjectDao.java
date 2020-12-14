package top.javaguo.biz.system.dao.project;

import org.apache.ibatis.annotations.*;
import top.javaguo.biz.system.bean.Project;
import top.javaguo.core.biz.dao.BaseDao;

import java.util.List;

/**
 * @describe 项目
 * @author javaGuo
 * @date 2019-01-18
 */
@Mapper
public interface ProjectDao extends BaseDao<Project> {

	/**根据条件查询所有**/
	@SelectProvider(type= ProjectMapper.class,method = "selectAll")
	public List<Project> selectAll(@Param("bean") Project bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type= ProjectMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") Project bean);

	/**添加**/
	@InsertProvider(type= ProjectMapper.class,method = "insert")
	public int insert(@Param("bean") Project bean);

	/**通过id删除**/
	@DeleteProvider(type= ProjectMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type= ProjectMapper.class,method = "updateById")
	public int updateById(@Param("bean") Project bean);

	/**通过id查询**/
	@SelectProvider(type= ProjectMapper.class,method = "selectById")
	public Project selectById(@Param("bean") Project bean);

	/**通过ids集合删除**/
	@DeleteProvider(type= ProjectMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type= ProjectMapper.class,method = "selectAllForRelation")
	public List<Project> selectAllForRelation(@Param("bean") Project bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type= ProjectMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") Project bean);

	/**根据关联id查询**/
	@SelectProvider(type= ProjectMapper.class,method = "selectByIdForRelation")
	public Project selectByIdForRelation(@Param("id") String id);

    /**通过用户编号查询**/
    @Select("select distinct p.* from project p left join user_project up on up.project_id = p.id where up.user_id = #{userId}")
    public List<Project> selectByUserId(@Param("userId") Long userId);

}
