package top.javaguo.core.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.javaguo.core.biz.dao.BaseDao;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

/**
 * 控制层基础类
 *
 * @date 2018/03/16
 * @author javaGuo
 */
@SuppressWarnings("all")
public class BaseService<T> {

    /**
     * 根据执行方法名获取结果
     *
     * @param methodName
     *            方法名
     * @param conditionMap
     *            条件集合
     * @return
     */
    protected RespBean<Map<String, Object>> getResult(BaseDao dao, String methodName, Object param) {
        RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>(new HashMap<>());
        Map<String, Object> data = respBean.getData();
        try {
            if ("selectAll".equals(methodName)) {
                data.put("list", dao.selectAll((T) param));
                data.put("total", dao.selectTotal((T) param));
                data.put("data", param);
            } else if ("selectAllForLayUI".equals(methodName)) {
                data.put("code", "0");
                data.put("msg", "执行成功");
                data.put("count", dao.selectTotal((T) param));
                data.put("data", dao.selectAll((T) param));
            }  else if ("selectAllForRelation".equals(methodName)) {
                data.put("code", "0");
                data.put("msg", "执行成功");
                data.put("count", dao.selectTotalForRelation((T) param));
                data.put("data", dao.selectAllForRelation((T) param));
            } else if ("insert".equals(methodName)) {
                dao.insert((T) param);
                data.put("data", (T) param);
            } else if ("deleteById".equals(methodName)) {
                dao.deleteById((String) param);
            } else if ("updateById".equals(methodName)) {
                dao.updateById((T) param);
            } else if ("selectById".equals(methodName)) {
                data.put("data", dao.selectById((T) param));
            } else if ("deleteByIds".equals(methodName)) {
                dao.deleteByIds((String) param);
            } else if ("insertBatch".equals(methodName)) {
                dao.insertBatch((List<T>) param);
                data.put("data", (List<T>) param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
        }
        return respBean;
    }

    /** 清空多对象缓存集合 **/
    public void clearBeanManyCache() {
    }

}