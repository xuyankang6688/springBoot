package top.javaguo.biz.system.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import top.javaguo.biz.system.bean.CreateCode;
import top.javaguo.biz.system.dao.createCode.CreateCodeDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.cacheNames.SpringCacheNames;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoDownloadUtil;
import top.javaguo.utils.createCodeUtil.CreateCodeFactory;
import top.javaguo.utils.createCodeUtil.CreateMain;

/**
 * @author 超级管理员
 * @describe 代码生成
 * @date 2019-01-18
 */
@Service
@CacheConfig(cacheNames = SpringCacheNames.DEFAULTONE)
public class CreateCodeService extends BaseService<CreateCode> {

    /**
     * 代码生成
     **/
    @Autowired
    private CreateCodeDao createCodeDao;

    /**
     * 根据条件查询所有
     **/
    @Cacheable(cacheNames = SpringCacheNames.DEFAULTMANY, key = "#bean")
    public RespBean<Map<String, Object>> selectAll(CreateCode bean) {
        return getResult(createCodeDao, "selectAll", bean);
    }

    /**
     * LayUI根据条件查询所有
     **/
    //@Cacheable(cacheNames = SpringCacheNames.DEFAULTMANY, key = "#bean")
    public Map<String, Object> selectAllForLayUI(CreateCode bean) {
        return getResult(createCodeDao, "selectAllForLayUI", bean).getData();
    }

    /**
     * 添加
     **/
    @CachePut(key = "#bean.id")
    public RespBean<Map<String, Object>> insert(CreateCode bean) {
        return getResult(createCodeDao, "insert", bean);
    }

    /**
     * 通过id删除
     **/
    @CacheEvict(key = "#id")
    public RespBean<Map<String, Object>> deleteById(String id) {
        return getResult(createCodeDao, "deleteById", id);
    }

    /**
     * 通过id修改
     **/
    @CachePut(key = "#bean.id")
    public RespBean<Map<String, Object>> updateById(CreateCode bean) {
        return getResult(createCodeDao, "updateById", bean);
    }

    /**
     * 通过id查询
     **/
    @Cacheable(key = "#bean.id")
    public RespBean<Map<String, Object>> selectById(CreateCode bean) {
        return getResult(createCodeDao, "selectById", bean);
    }

    /**
     * 通过ids集合删除
     **/
    @CacheEvict(allEntries = true)
    public RespBean<Map<String, Object>> deleteByIds(String ids) {
        return getResult(createCodeDao, "deleteByIds", ids);
    }

    /**
     * 清空多对象缓存集合
     **/
    @CacheEvict(cacheNames = SpringCacheNames.DEFAULTMANY, allEntries = true)
    @Override
    public void clearBeanManyCache() {
    }

    /**
     * 生成代码
     **/
    public RespBean<Map<String, Object>> createZip(HttpServletRequest request, HttpServletResponse response,
                                                   CreateCode bean) throws Exception {
        bean = createCodeDao.selectById(bean);
        CreateMain cm = new CreateMain();
//        String path = "http://39.108.122.222:8067/system/createCode/getCodeFileStr";
//        List<NameValuePair> parametersBody = new ArrayList();
//        parametersBody.add(new BasicNameValuePair("createCodeJsonStr", GuoJsonUtil.Object2Json(bean)));
//        List<NameValuePair> parametersHeader = new ArrayList();
//        String privateKey = "springbootsystem";
//        long publicKey = System.currentTimeMillis();
//        // 根据时间戳和私钥生成签名
//        String sign = GuoMD5Util.GetMD5Code(privateKey + publicKey);
//        parametersHeader.add(new BasicNameValuePair("publicKey", String.valueOf(publicKey)));
//        parametersHeader.add(new BasicNameValuePair("sign", sign));
//        String result = GuoHttpRequestUtil.postFormAddHeader(path, parametersBody, parametersHeader);
//        RespBean<Map<String, String>> respBean = GuoJsonUtil.json2Bean(result, RespBean.class);
//        String filePath = cm.create(respBean.getData(), bean.getClassName());
//        System.out.println(GuoDownloadUtil.downLoadFile(request, filePath, response, bean.getClassName(), "zip"));
        
        CreateCodeFactory createCodeFactory = new CreateCodeFactory();
        Map<String,String> map = createCodeFactory.createFileContent(bean);
       // System.out.println("map========================:"+map);
        String filePath = cm.create(map,bean.getClassName());
        //System.out.println(filePath);

        //System.out.println(GuoDownloadUtil.downLoadFile(request, filePath, response, bean.getClassName(), "zip"));
        return null;
    }

}
