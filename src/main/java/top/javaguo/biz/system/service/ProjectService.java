package top.javaguo.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.Project;
import top.javaguo.biz.system.dao.project.ProjectDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.cacheNames.SpringCacheNames;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

import java.util.Map;

/**
 * @author javaGuo
 * @describe 项目
 * @date 2019-01-18
 */
@Service
@CacheConfig(cacheNames = SpringCacheNames.DEFAULTONE)
public class ProjectService extends BaseService<Project> {

    /**
     * 项目
     **/
    @Autowired
    private ProjectDao projectDao;

    @SuppressWarnings("unused")
	@Autowired
    private GuoRedisUtil guoRedisUtil;

    /**
     * 根据条件查询所有
     **/
    @Cacheable(cacheNames = SpringCacheNames.DEFAULTMANY, key = "#bean")
    public RespBean<Map<String, Object>> selectAll(Project bean) {
        System.out.println("****");
        return getResult(projectDao, "selectAll", bean);
    }

    /**
     * LayUI根据条件查询所有
     **/
    @Cacheable(cacheNames = SpringCacheNames.DEFAULTMANY, key = "#bean")
    public Map<String, Object> selectAllForLayUI(Project bean) {
        return getResult(projectDao, "selectAllForLayUI", bean).getData();
    }

    /**
     * 添加
     **/
    @CachePut(key = "#bean.id")
    public RespBean<Map<String, Object>> insert(Project bean) {
        return getResult(projectDao, "insert", bean);
    }

    /**
     * 通过id删除
     **/
    @CacheEvict(key = "#id")
    public RespBean<Map<String, Object>> deleteById(String id) {
        return getResult(projectDao, "deleteById", id);
    }

    /**
     * 通过id修改
     **/
    @CachePut(key = "#bean.id")
    public RespBean<Map<String, Object>> updateById(Project bean) {
        return getResult(projectDao, "updateById", bean);
    }

    /**
     * 通过id查询
     **/
    @Cacheable(key = "#bean.id")
    public RespBean<Map<String, Object>> selectById(Project bean) {
        return getResult(projectDao, "selectById", bean);
    }

    /**
     * 通过ids集合删除
     **/
    @CacheEvict(allEntries = true)
    public RespBean<Map<String, Object>> deleteByIds(String ids) {
        return getResult(projectDao, "deleteByIds", ids);
    }

    /**
     * 清空多对象缓存集合
     **/
    @CacheEvict(cacheNames = SpringCacheNames.DEFAULTMANY, allEntries = true)
    @Override
    public void clearBeanManyCache() {
    }

    /**
     * 通过用户编号查询
     **/
    public RespBean<Map<String, Object>> selectByUserId(String userId) {
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
        respBean.getData().put("list", projectDao.selectByUserId(Long.valueOf(userId)));
        respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
        return respBean;
    }

}