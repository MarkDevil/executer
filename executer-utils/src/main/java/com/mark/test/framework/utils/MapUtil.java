package com.mark.test.framework.utils;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class MapUtil {
	
	/**
	 * javaBean 转 Map
	 * @param object
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> beanToMap(Object object) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		Class cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(object));
		}
		return map;
	}


	/**
	 * javaBean及bean的所有超类 转 Map
	 * @param object
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> beansToMap(Object object) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		Class cls = object.getClass();
		for(;cls != Object.class;cls= cls.getSuperclass()) {
			Field[] fieldes = cls.getDeclaredFields();
			for (Field field : fieldes) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(object));
			}
		}
		return map;
	}


	/**
	 * 需要转换的map
	 * @param map
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object mapToBean(Map<String, Object> map, Class cls)throws Exception {
		Object object = cls.newInstance();
		BeanUtils.populate(object, map);
		return object;
	}
}
