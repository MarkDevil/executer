package com.mark.test.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.Map;

/**
 * Created by mark .
 * Data   : 2019-03-22
 */
@Component
@Slf4j
public class JsonCompareUtil {


    /**
     * @function 比较两个JSONObject的key-value对的个数,内容是否一致---忽略顺序
     * @param json1
     * @param json2
     * @return
     */
    public boolean compareTwoJSONObject(JSONObject json1,JSONObject json2) {
        if (json1==null&&json2==null) {
            return true;
        }else if (json1==null && json2!=null) {
            return false;
        }else if(json1!=null&&json2==null) {
            return false;
        }else if (json1.size()!=json2.size()) {
            return false;
        }
        for(String key:json1.keySet()) {
            if(!json2.containsKey(key)) {
                return false;
            }else if(json1.get(key)==null&&json2.get(key)!=null) {
                return false;
            }
            try {
                JSONObject sonJSON1 = json1.getJSONObject(key);
                JSONObject sonJSON2 = json2.getJSONObject(key);
                if(!compareTwoJSONObject(sonJSON1, sonJSON2)) {
                    return false;
                }
            }catch (Exception e) {
                try {
                    JSONArray sonArray1 = json1.getJSONArray(key);
                    JSONArray sonArray2 = json2.getJSONArray(key);
                    if(!compareTwoJSONArray(sonArray1, sonArray2)) {
                        return false;
                    }
                } catch (Exception e1) {
                    try {
                        Object o1=json1.get(key);
                        Object o2=json2.get(key);
                        if (!o1.equals(o2)) {
                            return false;
                        }
                    } catch (Exception e2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @function 比较两个JSONArray元素个数,内容是否一致---忽略顺序
     * @param array1
     * @param array2
     * @return	一致返回true,不一致返回false
     */
    public boolean compareTwoJSONArray(JSONArray array1,JSONArray array2) {
        if (array1==null&&array2==null) {
            return true;
        }else if (array1==null && array2!=null) {
            return false;
        }else if(array1!=null&&array2==null) {
            return false;
        }else if (array1.size()!=array2.size()) {
            return false;
        }
        for(int index=0;index<array1.size();index++) {
            //array1的第index个元素还是JSONArray,则遍历array2的所有元素,递归比较...
            try {
                JSONArray sonArray1 = array1.getJSONArray(index);
                boolean flag = false;
                for(int index2=0;index2<array2.size();index2++) {
                    JSONArray sonArray2 = array2.getJSONArray(index2);
                    if(compareTwoJSONArray(sonArray1,sonArray2)) {
                        flag = true;
                    }
                }
                if (!flag) {
                    return false;
                }
            } catch (Exception e) {
                //array1的第index个元素是JSONObject,则遍历array2的所有元素,递归比较...
                try {
                    JSONObject sonJSON1 = array1.getJSONObject(index);
                    boolean flag = false;
                    for(int index2=0;index2<array2.size();index2++) {
                        JSONObject sonJSON2 = array2.getJSONObject(index2);
                        if(compareTwoJSONObject(sonJSON1,sonJSON2)) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                } catch (Exception e1) {
                    //array1的第index个元素非JSONArray&&非JSONObject,则遍历array2的所有元素,递归比较...
                    try {
                        Object o1 = array1.get(index);
                        boolean flag = false;
                        for (Object o2 : array2) {
                            if ((o1 == null && o2 == null) || o1.equals(o2)) {
                                flag = true;
                            }
                        }
                        if (!flag) {
                            return false;
                        }
                    }catch (Exception e2) {
                        array1.equals(array2);
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 比较两个JsonObject指定字段是否相等
     * @param actual
     * @param expect
     * @return
     */
    public void compareJsonObject(String actual, String expect){
        JSONObject actualobj = JSON.parseObject(actual);
        JSONObject expectobj = JSON.parseObject(expect);
        for (Map.Entry<String,Object> entry:actualobj.entrySet()) {
            if (entry.getValue() instanceof JSONArray){
                JSONArray act = (JSONArray) entry.getValue();
                log.info("Expect obj:{} , Actual obj:{}",expectobj.get(entry.getKey()),act);
                Assert.assertEquals((JSONArray) expectobj.get(entry.getKey()),act,"数组比较不一致");
            }else{
                log.info("Expect obj:{} , Actual obj:{}",expectobj.get(entry.getKey()),entry.getValue());
                Assert.assertEquals(expectobj.get(entry.getKey()),entry.getValue(),"对象比较不一致");
            }
        }
    }

    /**
     * 接口返回是否成功
     * @param ret
     * @return
     */
    public boolean isRetSucess(String ret){
        JSONObject jsonObject = JSON.parseObject(ret);
        return "200".equalsIgnoreCase(jsonObject.getString("code"));
    }

    public static void main(String[] args) {
        String actual = "{\n" +
                "  \"accountType\": \"0\",\n" +
                "  \"bizTypes\": [\"order\",\"gift\",\"egg\"],\n" +
                "  \"orderType\": \"normal\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.03\",\n" +
                "      \"crPrice\": -0.5800,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.03\",\n" +
                "      \"crPrice\": 9.6600,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"1405.05\",\n" +
                "      \"crPrice\": 300.0000,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"6402.02\",\n" +
                "      \"crPrice\": 0,\n" +
                "      \"drPrice\": 300.0000\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.04\",\n" +
                "      \"crPrice\": 0,\n" +
                "      \"drPrice\": 209.5900\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2221.01.02\",\n" +
                "      \"crPrice\": 28.9100,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"6051.01\",\n" +
                "      \"crPrice\": 180.6800,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.04\",\n" +
                "      \"crPrice\": 209.5900,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.01\",\n" +
                "      \"crPrice\": 5460.7500,\n" +
                "      \"drPrice\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"packOrderId\": \"37814053\",\n" +
                "  \"serialNumber\": \"1903141000124310266\"\n" +
                "}";


        String expect = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.03\",\n" +
                "      \"crPrice\": -0.5800,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.03\",\n" +
                "      \"crPrice\": 9.6600,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"1405.05\",\n" +
                "      \"crPrice\": 300.0000,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"6402.02\",\n" +
                "      \"crPrice\": 0,\n" +
                "      \"drPrice\": 300.0000\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.04\",\n" +
                "      \"crPrice\": 0,\n" +
                "      \"drPrice\": 209.5900\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2221.01.02\",\n" +
                "      \"crPrice\": 28.9100,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"6051.01\",\n" +
                "      \"crPrice\": 180.6800,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.04\",\n" +
                "      \"crPrice\": 209.5900,\n" +
                "      \"drPrice\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"accountSubjectId\": \"2401.01\",\n" +
                "      \"crPrice\": 5460.7500,\n" +
                "      \"drPrice\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"packOrderId\": \"37814053\",\n" +
                "  \"serialNumber\": \"1903141000124310266\",\n" +
                "  \"accountType\": \"0\",\n" +
                "  \"bizTypes\": [\"order\",\"gift\",\"egg\"],\n" +
                "  \"orderType\": \"normal\"\n" +
                "}";

        JsonCompareUtil compareUtil = new JsonCompareUtil();
        compareUtil.compareJsonObject(actual,expect);
    }




}
