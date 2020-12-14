package top.javaguo.core.biz.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.javaguo.utils.GuoStringUtil;

/**
 * @describe Mapper类属性封装对象
 * @author javaGuo
 * @date 2018-12-03
 */
public class MapperParam {

	public MapperParam(Class<?> obj, String notFieldParam) {

		// 表字段集合
		List<String> fields = new ArrayList<String>();

		// 表字段对应get方法集合
		List<Method> fieldMethods = new ArrayList<Method>();

		// 得到类中的所有属性集合
		Field[] field = obj.getDeclaredFields();

		// 通过类名设置表名
		tableName = GuoStringUtil.replaceUpperToLower(obj.getSimpleName());

		// 非表字段属性数组
		String[] notFieldParams = notFieldParam.split(",");

		// 获取表字段集合
		for (int i = 0; i < field.length; i++) {
			Field f = field[i];
			// key:得到属性名
			String key = f.getName();
			boolean flag = true;
			for (int j = 0; j < notFieldParams.length; j++) {
				if (key.equals(notFieldParams[j])) {
					flag = false;
					break;
				}
			}
			if (flag) {
				fields.add(key);
			}
		}

		// 得到类中所有方法的集合
		Method[] fieldMethod = obj.getMethods();
		// 方法名字
		String methodName = "";
		for (int i = 0; i < fieldMethod.length; i++) {
			// 方法
			Method method = fieldMethod[i];
			// 方法对应名字
			methodName = method.getName();
			// 拦截方法名以get打头的方法
			if (methodName.startsWith("get")) {
				// 
				for (int j = 0; j < fields.size(); j++) {
					// 方法为数据库表中对应字段的过滤
					if (fields.get(j).equalsIgnoreCase(methodName.split("get")[1])) {
						// 放入表字段对应get方法集合
						fieldMethods.add(method);
						// 把对应表字段以及表字段get方法加入表字段集合
						fieldAndMethods.put(GuoStringUtil.replaceUpperToLower(fields.get(j)), method);
						break;
					}
				}
				// baseMapper查询条件公共所需get方法
				if ((methodName.split("get")[1]).equalsIgnoreCase("sortingRules")
						|| (methodName.split("get")[1]).equalsIgnoreCase("orderBy")
						|| (methodName.split("get")[1]).equalsIgnoreCase("limit")
						|| (methodName.split("get")[1]).equalsIgnoreCase("offset")
						|| (methodName.split("get")[1]).equalsIgnoreCase("id")) {
					selectCommonMethods.put(method.getName(), method);
				}
			}
		}

	}

	/** 表名 **/
	private String tableName;

	/** 表字段集合 **/
	public Map<String, Method> fieldAndMethods = new HashMap<String, Method>();

	/** 查询条件公共所需get方法集合 **/
	public Map<String, Method> selectCommonMethods = new HashMap<String, Method>();

	/** 获取表名 **/
	public String getTableName() {
		return tableName;
	}

	/** 设置表名 **/
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** 获取表字段集合 **/
	public Map<String, Method> getFieldAndMethods() {
		return fieldAndMethods;
	}

	/** 设置表字段集合 **/
	public void setFieldAndMethods(Map<String, Method> fieldAndMethods) {
		this.fieldAndMethods = fieldAndMethods;
	}

	/** 获取查询条件公共所需get方法集合 **/
	public Map<String, Method> getSelectCommonMethods() {
		return selectCommonMethods;
	}

	/** 设置查询条件公共所需get方法集合 **/
	public void setSelectCommonMethods(Map<String, Method> selectCommonMethods) {
		this.selectCommonMethods = selectCommonMethods;
	}

}
