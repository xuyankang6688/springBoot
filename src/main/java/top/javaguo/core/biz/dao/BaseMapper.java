package top.javaguo.core.biz.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import top.javaguo.core.util.SnowflakeIdWorkerUtil;

/**
 * @describe 基础mapper
 * @author javaGuo
 * @date 2018-05-25
 */
@SuppressWarnings("all")
@Mapper
public class BaseMapper<T> {

    /** 表名 **/
    protected String tableName;

    /** 表字段以及对应方法集合 **/
    public Map<String, Method> fieldAndMethods = new HashMap<String, Method>();

    /** 查询条件公共所需get方法集合 **/
    public Map<String, Method> selectCommonMethods = new HashMap<String, Method>();

    /**
     * 构造函数时初始化配置信息
     *
     **/
    public BaseMapper() {
        initConfig(AllMybatisBean.allMybatisBean.get(getClassForGeneric().getSimpleName()));
    }

    /**
     * 通过泛型获取对应class
     */
    public Class getClassForGeneric() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * 初始化配置参数
     */
    public void initConfig(MapperParam mapperParam) {
        // 表名
        this.tableName = mapperParam.getTableName();
        // 表字段集合
        this.fieldAndMethods = mapperParam.getFieldAndMethods();
        // 查询条件公共所需get方法集合
        this.selectCommonMethods = mapperParam.getSelectCommonMethods();
    }

    /** 执行方法 **/
    protected Object methodInvoke(Method method, T bean) {
        Object object = new Object();
        try {
            object = method.invoke(bean);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    /** 代表单引号的值 **/
    protected String quotationMark = "-zjterj8888.-";

    /** 判断字符串是否为空 **/
    protected boolean isEmpty(String param) {
        return param == null || param.length() <= 0;
    }

    /** 判断字符串是否为null **/
    protected boolean isNull(String param) {
        return param == null ? true : false;
    }

    /** 给字符串两边加单引号 **/
    protected String addQuotationMark(String param) {
        return quotationMark + param + quotationMark;
    }

    /** 获取新增字段以及字段值 **/
    public String[] getInsertBatchFieldsAndValues(List<T> list) {
        // 新增字段以及字段值
        String[] fildsAndValues = new String[list.size() + 1];
        // 字段
        String fields = "id,create_time,update_time";
        for (int i = 0; i < list.size(); i++) {
            String id = (String) methodInvoke(selectCommonMethods.get("getId"), list.get(i));
            // 字段值
            String values = ((isEmpty(id)) ? SnowflakeIdWorkerUtil.SIWU.nextId() : id) + ",now() " + ",now()";
            // 迭代表字段以及对应方法集合
            for (Entry<String, Method> method : fieldAndMethods.entrySet()) {
                if (method.getKey().equals("id") || method.getKey().equals("create_time")
                        || method.getKey().equals("update_time")) {
                    continue;
                }
                try {
                    // 当bean中属性为null时拦截
                    if (!isNull((String) method.getValue().invoke(list.get(i)))) {
                        if(i == 0) {
                            fields += "," + method.getKey();
                        }
                        values += "," + addQuotationMark((String) method.getValue().invoke(list.get(i)));
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if(i == 0){
                fildsAndValues[i] = fields;
            }
            fildsAndValues[ i+1 ] = values;
        }
        return fildsAndValues;
    }

    /** 获取新增字段以及字段值 **/
    public String[] getInsertFieldsAndValues(T bean) {
        // 新增字段以及字段值
        String[] fildsAndValues = new String[2];
        // 字段
        String fields = "id,create_time,update_time";
        String id = (String) methodInvoke(selectCommonMethods.get("getId"), bean);
        // 字段值
        String values = ((isEmpty(id)) ? SnowflakeIdWorkerUtil.SIWU.nextId() : id) + ",now() " + ",now()";
        // 迭代表字段以及对应方法集合
        for (Entry<String, Method> method : fieldAndMethods.entrySet()) {
            if (method.getKey().equals("id") || method.getKey().equals("create_time")
                    || method.getKey().equals("update_time")) {
                continue;
            }
            try {
                // 当bean中属性为null时拦截
                if (!isNull((String) method.getValue().invoke(bean))) {
                    fields += "," + method.getKey();
                    values += "," + addQuotationMark((String) method.getValue().invoke(bean));
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        fildsAndValues[0] = fields;
        fildsAndValues[1] = values;
        return fildsAndValues;
    }

    /** 获取修改字段以及字段值 **/
    public String getUpdateFieldsAndValues(T bean) {
        String temp = "update_time = now() ";
        for (Entry<String, Method> method : fieldAndMethods.entrySet()) {
            if (method.getKey().equals("id") || method.getKey().equals("create_time")
                    || method.getKey().equals("update_time")) {
                continue;
            }
            try {
                // 当bean中属性为null时拦截
                if (!isNull((String) method.getValue().invoke(bean))) {
                    temp += updateAddField(method.getKey(), (String) method.getValue().invoke(bean));
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    public String commonWhere(T bean) {
        return "";
    };

    /**
     * 获取查询语句
     */
    public String getSelectSql(T bean, String method, String fields, String tableName) {
        return getSelectSql(method, fields, tableName,
                (String) methodInvoke(this.selectCommonMethods.get("getSortingRules"), bean),
                (String) methodInvoke(this.selectCommonMethods.get("getOrderBy"), bean),
                (Integer) methodInvoke(this.selectCommonMethods.get("getOffset"), bean),
                (Integer) methodInvoke(this.selectCommonMethods.get("getLimit"), bean), commonWhere(bean));
    }

    /** 替换字符串中的除首字母以外的大写字母为_加小写字母 **/
    protected String replaceUpperToLower(String param) {
        String temp = "";
        if (!isEmpty(param)) {
            for (int i = 0; i < param.length(); i++) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (i != 0)
                        temp += '_';
                    temp += String.valueOf(c).toLowerCase();
                } else
                    temp += c;
            }
        }
        return temp;
    }

    /**
     * 获取查询语句
     *
     * @param method
     *            查询所有数据或数量的方法
     * @param fields
     *            字段信息
     * @param tableName
     *            表名
     * @param sortingRules
     *            排序规则
     * @param orderBy
     *            排序对象
     * @param whereSql
     *            条件语句
     * @param offset
     *            页码
     * @param limit
     *            获取记录条数
     * @return
     */
    protected String getSelectSql(String method, String fields, String tableName, String sortingRules, String orderBy,
                                  Integer offset, Integer limit, String whereSql) {
        if (isEmpty(tableName))
            tableName = this.tableName + " t ";
        String sql = "SELECT " + fields + " FROM " + tableName + whereSql;
        if (!"total".equals(method)) {
            sql += baseOrderBy(orderBy, sortingRules);
            sql += baseLimit(offset, limit);
        }
        return escapeExprSpecialWord(sql);
    }

    /** 排序条件 */
    protected String baseOrderBy(String orderBy, String sortingRules) {
        String sql = "";
        if (!isEmpty(orderBy)) {
            sql += " ORDER BY t." + replaceUpperToLower(orderBy) + " ";
            if (!isEmpty(sortingRules)) {
                if (!"desc".equals(sortingRules))
                    sql += " ASC ";
                else
                    sql += " DESC ";
                // order by后面加id是考虑到mysql的bug，当order by 和 limit混用时且order
                // by的字段有重复时会产生查询错误,这时候加入一个唯一主键进行排序就好了
                sql += " , t.id ";
            }
        } else
            sql += " ORDER BY t.id DESC ";
        return sql;
    }

    /**
     * 公用查询条件添加过滤
     *
     * @param name
     *            字段名加过滤方式
     * @param value
     *            字段值
     * @return
     */
    protected String whereAddFilter(String name, String value) {
        return isEmpty(value) ? "" : " AND " + name + addQuotationMark(value);
    }

    /**
     * 修改语句增加字段
     *
     * @param name
     *            字段名
     * @param value
     *            字段值
     * @return
     */
    protected String updateAddField(String name, String value) {
        return isNull(value) ? "" : ", " + name + " = " + addQuotationMark(value);
    }

    /** 分页条件 */
    protected String baseLimit(Integer offset, Integer limit) {
        return (offset == null || limit == null) ? "" : " LIMIT " + (offset - 1) * limit + "," + limit;
    }

    /** 添加 **/
    protected String getInsertSql(String[] insertFieldsAndValues) {
        return escapeExprSpecialWord("INSERT INTO " + tableName + " (" + insertFieldsAndValues[0] + ") VALUES ("
                + insertFieldsAndValues[1] + ")");
    }

    /**
     * 批量添加
     * @date 20190306
     * **/
    protected String getInsertBatchSql(String[] insertFieldsAndValues) {
        String temp = "";
        for (int i = 1; i < insertFieldsAndValues.length; i++) {
            temp += "(" + insertFieldsAndValues[i] + ")";
            if(i != insertFieldsAndValues.length - 1 ){
                temp += ", ";
            }
        }
        return escapeExprSpecialWord("INSERT INTO " + tableName + " (" + insertFieldsAndValues[0] + ") VALUES " + temp );
    }

    /** 通过id修改 **/
    protected String getUpdateByIdSql(String updateFieldsAndValues, String id) {
        return escapeExprSpecialWord(
                "UPDATE " + tableName + " SET " + updateFieldsAndValues + " WHERE id = " + addQuotationMark(id));
    }

    /** 通过id删除 **/
    public String deleteById(@Param("id") String id) {
        return escapeExprSpecialWord("DELETE FROM " + tableName + " WHERE id = " + addQuotationMark(id));
    }

    /** 通过ids集合删除 **/
    public String deleteByIds(@Param("ids") String ids) {
        return escapeExprSpecialWord("DELETE FROM " + tableName + " WHERE id IN (" + ids + ")");
    }

    /**
     * 公用查询条件添加模糊过滤
     *
     * @param fields
     *            字段名数组
     * @param value
     *            字段值
     * @date 2018-06-05
     * @author 孙浩
     * @qq 873519747
     * @return
     */
    protected String whereAddLikeFilter(String[] fields, String value) {
        String sql = "";
        if (fields.length != 0 && !isEmpty(value)) {
            sql = " AND ( ";
            for (int i = 0; i < fields.length; i++) {
                if (i != 0)
                    sql += " OR ";
                sql += fields[i] + " LIKE " + addQuotationMark("%" + value + "%");
            }
            sql += " ) ";
        }
        return sql;
    }

    /** 矫正sql **/
    protected String correctSql(String sql) {
        if (!isEmpty(sql)) {
            sql += ";";
            String[] tempSql = sql.split(quotationMark);
            sql = "";
            for (int i = 0; i < tempSql.length; i++) {
                sql += tempSql[i];
                if (i == tempSql.length - 1)
                    continue;
                sql += "'";
            }
        }
        return sql;
    }

    /** 转义sql中的特殊符号 **/
    protected String escapeExprSpecialWord(String sql) {
        if (!isEmpty(sql)) {
            String[] fbsArr = { "'", "\"" };
            for (String key : fbsArr) {
                if (sql.contains(key)) {
                    sql = sql.replace(key, "\\" + key);
                }
            }
        }
        return correctSql(sql);
    }

    /** 添加 **/
    public String insert(@Param("bean") T bean) {
        return getInsertSql(getInsertFieldsAndValues(((Map<String, T>) bean).get("bean")));
    }

    /**
     * 批量添加
     * @date 20190306
     * **/
    public String insertBatch(@Param("list") List<T> list) {
        String[] temp = getInsertBatchFieldsAndValues(list);
        return getInsertBatchSql(temp);
    }

    /** 通过id修改 **/
    public String updateById(@Param("bean") T bean) {
        return getUpdateByIdSql(getUpdateFieldsAndValues(((Map<String, T>) bean).get("bean")),
                (String) methodInvoke(selectCommonMethods.get("getId"), ((Map<String, T>) bean).get("bean")));
    }

    /** 根据条件查询所有数据 **/
    public String selectAll(@Param("bean") T bean) {
            return getSelectSql(((Map<String, T>) bean).get("bean"), "all", "*", "");
    }

    /** 根据条件查询所有数量 **/
    public String selectTotal(@Param("bean") T bean) {
        return getSelectSql(((Map<String, T>) bean).get("bean"), "total", "count(*)", "");
    }

    /** 根据id查询 **/
    public String selectById(@Param("bean") T bean) {

        return getSelectSql(((Map<String, T>) bean).get("bean"), "all", "*", "");
    }

}
