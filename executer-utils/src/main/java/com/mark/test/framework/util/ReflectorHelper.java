package com.mark.test.framework.util;

import com.alibaba.fastjson.JSON;
import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 反射工具类
 * @author mark
 */
public class ReflectorHelper {

	static Logger logger = LoggerFactory.getLogger(ReflectorHelper.class);

    /**
     * 获取类中方法
     * @param classObj      传入类对象
     * @param methodName    方法名
     * @return
     */
	public static Method getMethod(Class<?> classObj, String methodName) {
		Method[] allMethods = classObj.getDeclaredMethods();
		Method selectedMethod = null;
		for (Method method : allMethods) {
			if (method.getName().equalsIgnoreCase(methodName)) {
				selectedMethod = method;
				break;
			}
		}
		return selectedMethod;
	}

    /**
     * 对象转成map
     * @param obj   传入对象
     * @return
     */
	public static Map objectToMap(Object obj) {
		String jsonStr = JSON.toJSONString(obj);
		return JSON.parseObject(jsonStr, Map.class);
	}

    /**
     * 实例化类对象
     * @param className  类名
     * @return
     * @throws ClassNotFoundException
     */
	public static Class<?> loadClass(String className) throws ClassNotFoundException {
		return Class.forName(className);
	}

	public static <T> T createInstance(Class<T> paramterType, Object value) {
		String jsonStr = JSON.toJSONString(value);
		return JSON.parseObject(jsonStr, paramterType);
	}



	/**
	 * 获取指定函数的入参类型
	 * @param claz			指定类名
	 * @param methodname	指定方法名
	 * @return
	 * @throws ClassNotFoundException
     */

	public static String[] getMethodParamTypes(Class claz ,String methodname) {
		if (claz != null && methodname != null){
			logger.info("The input parameter are {} ,{}",claz,methodname);
			Class[] params;
			String[] types = null;
			Method[] methods = claz.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodname)) {
					params = method.getParameterTypes();
					types = new String[params.length];
					for (int j = 0; j < params.length; j++) {
						types[j] = params[j].getName();
					}
					break;
				}
			}
			logger.info("parameters types: {}", Arrays.toString(types));
			return types;
		}else{
			try {
				throw new Exception(new Throwable(String.format("The input parameter is incorrect {%s} ,{%s}",claz,methodname)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

    /**
     * 动态修改class中的配置
     * @param clz
     * @param field
     * @param value
     */
	public static void modifyValue(Class<?> clz,String field,String value){
		ClassPool classPool = ClassPool.getDefault();
		try {
			//新建一个classLoader加载新编译生成的class文件
			Loader loader = new Loader(classPool);
			CtClass ctClass = classPool.get(clz.getCanonicalName());
			CtMethod ctMethod = CtMethod.make("public Integer getInteger() " +
					"{ " +
					 "System.out.println(\"hello invoke successfully\");" +
					  "return null; " +
					"}",ctClass);
			ctClass.addMethod(ctMethod);
			logger.info("获取添加后的方法:{}", Arrays.toString(ctClass.getDeclaredMethods()));
			Class clz1 = loader.loadClass(clz.getCanonicalName());
			Object object = clz1.newInstance();
			clz1.getMethod("getInteger",null).invoke(object,null);
		} catch (NotFoundException | CannotCompileException | InstantiationException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
        String[] paramtypes = getMethodParamTypes(ReflectorHelper.class,"getMethodParamTypes");
        logger.info(Arrays.toString(paramtypes));

    }
}
