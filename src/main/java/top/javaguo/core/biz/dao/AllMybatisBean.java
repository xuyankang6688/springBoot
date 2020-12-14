package top.javaguo.core.biz.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.javaguo.core.util.PackageUtil;

/**
 * @describe 应用中数据库表对应的所有bean的mapperParam集合
 * @author javaGuo
 * @date 2018-12-03
 */
public class AllMybatisBean {

	/** mybatis表对应bean集合 **/
	public static Map<String, MapperParam> allMybatisBean = new HashMap<>();

	/**
	 * 多模块则用','分割(例如：top.javaguo.biz.system.bean,top.javaguo.biz.system.sso)，如若其他模块未应用到baseMapper则不需配置扫描(例如：top.javaguo.biz.system.sso)
	 **/
	public static String packagePaths = "top.javaguo.biz.system.bean";

	static {
		String[] packagePath = packagePaths.split(",");


		for (int i = 0; i < packagePath.length; i++) {
			initClassList(packagePath[i]);
		}
	}

	/**
	 * 扫描packagePaths包下的类并初始化baseMapper所需对应属性
	 */
	private static List<String> initClassList(String packagePath) {
		// 根据路径获取路径下所有类
		List<String> classList = PackageUtil.getClassWithPath(packagePath);

		for (int i = 0; i < classList.size(); i++) {
			Class<?> clazz;
			try {
				// 通过类路径得到对应类
				clazz = Class.forName(classList.get(i));
				// 获取非表字段属性get方法
				Method method = clazz.getDeclaredMethod("getNotFieldParams");
				if (method != null) {
					allMybatisBean.put(clazz.getSimpleName(),
							new MapperParam(clazz, (String) method.invoke(clazz.newInstance())));

				}
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				e.printStackTrace();
			}

		}
		return classList;
	}

}
