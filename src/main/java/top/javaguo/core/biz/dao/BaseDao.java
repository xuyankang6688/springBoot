package top.javaguo.core.biz.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @describe 基础Dao
 * @author javaGuo
 * @date 2018-05-25
 */
@Mapper
public interface BaseDao<T> /*extends BaseMapper<T>*/  {

    /** 根据条件查询所有 **/
    public List<T> selectAll(T bean);

    /** 根据条件查询所有的个数 **/
    public int selectTotal(T bean);
    
    /** 根据关联条件查询所有 **/
    public List<T> selectAllForRelation(T bean);

    /** 根据关联条件查询所有的个数 **/
    public int selectTotalForRelation(T bean);

    /** 添加 **/
    public int insert(T bean);

    /** 通过id删除 **/
    public int deleteById(String id);

    /** 通过id修改 **/
    public int updateById(T bean);

    /** 通过id查询 **/
    public T selectById(T bean);

    /** 通过ids集合删除 **/
    public int deleteByIds(String ids);

    /** 批量添加 **/
    public int insertBatch(List<T> list);

}
