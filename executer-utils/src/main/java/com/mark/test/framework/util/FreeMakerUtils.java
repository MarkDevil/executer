package com.mark.test.framework.util;

import freemarker.template.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


/**
 * mingfeng.ma
 * 2018/8/23
 */
public class FreeMakerUtils {
    Logger logger = LoggerFactory.getLogger(FreeMakerUtils.class);

    private static Configuration cfg = null;
    private static String templePath = "/temp";


    /**
     * 获取freemarker的配置 freemarker本身支持classpath,目录和从ServletContext获取.
     *
     * @return 返回Configuration对象
     */
    private static Configuration getConfiguration() {
        if (null == cfg) {
            cfg = new Configuration();
            // 这里有三种方式读取
            // （一个文件目录）
            try {
                cfg.setDirectoryForTemplateLoading(new File(FreeMakerUtils.class.getResource(templePath).getPath()));
                // classpath下的一个目录（读取jar文件）
                // cfg.setClassForTemplateLoading(this.getClass(),"/templates");
                // 相对web的根路径来说 根目录
                // setEncoding这个方法一定要设置国家及其编码，不然在flt中的中文在生成html后会变成乱码
                cfg.setEncoding(Locale.getDefault(), "UTF-8");

                // 设置对象的包装器
                cfg.setObjectWrapper(new DefaultObjectWrapper());
                // 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cfg;
    }

    /**
     * @param ftl        模板文件名,相对上面的模版根目录templates路径,例如/module/view.ftl templates/module/view.ftl
     * @param data       填充数据
     * @param targetFile 要生成的静态文件的路径,相对设置中的根路径,例如 "jsp/user/1.html"
     * @return
     */
    public static boolean createFile(String ftl, Map<String, Object> data, String targetFile) {

        try {
            // 创建Template对象
            Configuration cfg = getConfiguration();
            Template template = cfg.getTemplate(ftl);
            template.setOutputEncoding("UTF-8");
            // 生成静态页面
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
            template.process(data, out);
            out.flush();
            out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 读取文件生成dto文件
     *
     * @param filepath
     */
    public static List<Map<String, Object>> genDtoClass(String filepath) {
        String val = "String";
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<String> lines;
        try {
            lines = FileUtils.readLines(new File(FreeMakerUtils.class.getClass().getResource(filepath).getPath()), "utf-8");
            for (String line : lines) {
                Map<String, Object> map = new HashMap<>();
                String key = line.split(":")[0];
                map.put(key, val);
                mapList.add(map);
            }
            return mapList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据模板文件生成bean文件
     *
     * @param beanName
     * @param datas
     * @param targetFile
     */
    public static void createBean(String beanName, List<Map<String, Object>> datas, String targetFile) {
        //注意必须有一个根结点 data-model
        Map<String, Object> root = new HashMap<>();
        root.put("class", beanName);
        Collection<Map<String, Object>> properties = new HashSet<>();
        for (Map<String, Object> map : datas) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry entry : entries) {
                Map<String, Object> param = new HashMap<>();
                param.put("name", entry.getKey());
                param.put("type", entry.getValue());
                properties.add(param);
            }
        }
        root.put("properties", properties);
        createFile("genBean.ftl", root, targetFile);
    }


    public static void main(String[] args) {
        createBean("ShopAdminSaveAddressReqDto",
                Objects.requireNonNull(genDtoClass("/temp/saveAddress.txt")),
                "ShopAdminSaveAddressReqDto.java");

    }

}
