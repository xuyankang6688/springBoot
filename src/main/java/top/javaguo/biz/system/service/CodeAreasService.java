package top.javaguo.biz.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.CodeAreas;
import top.javaguo.biz.system.dao.codeAreas.CodeAreasDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;

/**
 * @describe 城市表
 * @author 
 * @date 2019-10-23
 */
@Service
public class CodeAreasService extends BaseService<CodeAreas>{

	/**城市表**/
	@Autowired
	private CodeAreasDao codeAreasDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(CodeAreas bean) { return getResult(codeAreasDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(CodeAreas bean) { return getResult(codeAreasDao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	public RespBean<Map<String, Object>> insert(CodeAreas bean) { return getResult(codeAreasDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(codeAreasDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(CodeAreas bean) { return getResult(codeAreasDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(CodeAreas bean) { return getResult(codeAreasDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(codeAreasDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}


}

