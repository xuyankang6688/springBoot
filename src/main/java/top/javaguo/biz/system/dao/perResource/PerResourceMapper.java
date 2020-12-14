package top.javaguo.biz.system.dao.perResource;

import top.javaguo.biz.system.bean.PerResource;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 权限-资源
 * @author javaGuo
 * @date 2018-05-31
 */
public class PerResourceMapper extends BaseMapper<PerResource> {

	/** 查询条件 **/
	@Override
	public String commonWhere(PerResource bean) {
		return bean == null ? ""
				: " WHERE 1 = 1 " + whereAddFilter("t.id = ", bean.getId())
						+ whereAddFilter("t.create_time = ", bean.getCreateTime())
						+ whereAddFilter("t.update_time = ", bean.getUpdateTime())
						+ whereAddFilter("t.parent_id = ", bean.getParentId())
						+ whereAddFilter("t.res_name = ", bean.getResName())
						+ whereAddFilter("t.res_url = ", bean.getResUrl())
						+ whereAddFilter("t.res_introduce = ", bean.getResIntroduce())
						+ whereAddFilter("t.res_order_num = ", bean.getResOrderNum())
						+ whereAddFilter("t.menu_level = ", bean.getMenuLevel())
						+ whereAddFilter("t.create_time >= ", bean.getQueryBeginDate())
						+ whereAddFilter("t.create_time < ", bean.getQueryEndDate());
	}

}
