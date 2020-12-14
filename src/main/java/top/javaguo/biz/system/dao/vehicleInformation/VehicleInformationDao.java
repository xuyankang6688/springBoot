package top.javaguo.biz.system.dao.vehicleInformation;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.VehicleInformation;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 车辆信息
 * @author 
 * @date 2019-12-07
 */
@Mapper
public interface VehicleInformationDao extends BaseDao<VehicleInformation> {

	/**根据条件查询所有**/
	@SelectProvider(type=VehicleInformationMapper.class,method = "selectAll")
	public List<VehicleInformation> selectAll(@Param("bean") VehicleInformation bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=VehicleInformationMapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") VehicleInformation bean);

	/**添加**/
	@InsertProvider(type=VehicleInformationMapper.class,method = "insert")
	public int insert(@Param("bean") VehicleInformation bean);

	/**通过id删除**/
	@DeleteProvider(type=VehicleInformationMapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=VehicleInformationMapper.class,method = "updateById")
	public int updateById(@Param("bean") VehicleInformation bean);

	/**通过id查询**/
	@SelectProvider(type=VehicleInformationMapper.class,method = "selectById")
	public VehicleInformation selectById(@Param("bean") VehicleInformation bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=VehicleInformationMapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=VehicleInformationMapper.class,method = "selectAllForRelation")
	public List<VehicleInformation> selectAllForRelation(@Param("bean") VehicleInformation bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=VehicleInformationMapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") VehicleInformation bean);

	/**根据关联id查询**/
	@SelectProvider(type=VehicleInformationMapper.class,method = "selectByIdForRelation")
	public VehicleInformation selectByIdForRelation(@Param("id") String id);

}
