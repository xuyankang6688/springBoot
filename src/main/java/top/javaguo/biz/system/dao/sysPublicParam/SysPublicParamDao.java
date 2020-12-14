package top.javaguo.biz.system.dao.sysPublicParam;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.SysPublicParam;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 公共参数
 * @author javaGuo
 * @date 2019-02-26
 */
@Mapper
public interface SysPublicParamDao extends BaseDao<SysPublicParam> {

	/**根据条件查询所有**/
	@SelectProvider(type=SysPublicParamMapper.class,method = "selectAll")
	public List<SysPublicParam> selectAll(@Param("bean") SysPublicParam bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=SysPublicParamMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") SysPublicParam bean);

	/**添加**/
	@InsertProvider(type=SysPublicParamMapper.class,method = "insert")
	public int insert(@Param("bean") SysPublicParam bean);

	/**通过id删除**/
	@DeleteProvider(type=SysPublicParamMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=SysPublicParamMapper.class,method = "updateById")
	public int updateById(@Param("bean") SysPublicParam bean);

	/**通过id查询**/
	@SelectProvider(type=SysPublicParamMapper.class,method = "selectById")
	public SysPublicParam selectById(@Param("bean") SysPublicParam bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=SysPublicParamMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=SysPublicParamMapper.class,method = "selectAllForRelation")
	public List<SysPublicParam> selectAllForRelation(@Param("bean") SysPublicParam bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=SysPublicParamMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") SysPublicParam bean);

	/**根据关联id查询**/
	@SelectProvider(type=SysPublicParamMapper.class,method = "selectByIdForRelation")
	public SysPublicParam selectByIdForRelation(@Param("id") String id);

}
