package com.mark.test.framework.web.testng;

import com.Ziroom.qa.AmsScript.AssetHireContractAuditCommon;
import com.Ziroom.qa.utils.CommonFunction;
import com.Ziroom.qa.utils.HttpRequest;
import com.Ziroom.qa.utils.JsoupUtil;
import com.Ziroom.qa.utils.OracleSqlHelper;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MingfengMa .
 * Data : 2017/3/23
 * Desc :
 *
 * http://ams.t.ziroom.com/AMS/security/security!indexAMS.action
 */
public class TestHireHourseAuditFlow {
    Logger logger = Logger.getLogger(TestHireHourseAuditFlow.class);
    HttpRequest httpRequest = new HttpRequest();
    AssetHireContractAuditCommon assetHireContractAuditCommon = new AssetHireContractAuditCommon();
    String prefix = "http://ams.t.ziroom.com";
    String intf = "/AMS/security/security!login.action";
    String selectUrl = String.format("http://ams.t.ziroom.com/AMS/security/security!selectCity.action?territoryId=%s&username=%s",11,"admin");
    String hireContactcode = "";
    OracleSqlHelper oracleSqlHelper = new OracleSqlHelper();
    String hireId = "";

    @BeforeClass
    public void init(){


    }

    @Test(description = "登录资产系统",timeOut = 5000)
    public void testLoginAMS(){
        String ret = assetHireContractAuditCommon.loginAsset(prefix + intf);
        Map<String,String> retMap = JsoupUtil.getSpecifiedElement("option",ret);
        CommonFunction.printMap(retMap);
        Assert.assertTrue(retMap.containsKey("北京"));

    }

    @Test(description = "选择城市",dependsOnMethods = "testLoginAMS")
    public void testSelectCity(){
        logger.info("开始选择城市");
//        assetHireContractAuditCommon.selectCity(selectUrl);
        httpRequest.getGetReturn(selectUrl);

    }

    @Test(description = "登录主页",dependsOnMethods = {"testLoginAMS","testSelectCity"})
    public void testIndexPage(){
        logger.info("登录主页");
    }

    @Test(description = "进入收房合同成交报告",
            dependsOnMethods = {"testLoginAMS","testSelectCity","testIndexPage"})
    public void testHireHourse(){
//        assetHireContractAuditCommon.hiredealReport("http://ams.t.ziroom.com/AMS/hire/hiredealReport!listRentDealReport.action");
        JSONObject jsonObject = httpRequest.getGetReturn("http://ams.t.ziroom.com/AMS/hire/hiredealReport!listRentDealReport.action");
        String html = (String) jsonObject.get("returnValue");
        Map<String,String> retmap = JsoupUtil.getSpecifiedElement("td",html);
        String[] strings ={};
        for (String str:retmap.keySet()){
            strings = str.split(" ");
            for (String string:strings){
                if (string.startsWith("BJW") && !string.contains("废")){
                    logger.info("合同号为: " + string);
                    hireContactcode = string;
                    break;
                }
            }
        }
        Assert.assertTrue(retmap!=null);
    }

    @Test(description = "添加房源",dependsOnMethods = {"testLoginAMS","testSelectCity","testIndexPage","testHireHourse"})
//http://ams.t.ziroom.com/AMS/hire/hiredealReport!addRentDealReport.action
    public void testAddHouse(){
        JSONObject jsonObject = httpRequest.getGetReturn("http://ams.t.ziroom.com/AMS/hire/hiredealReport!addRentDealReport.action");
        String html = (String) jsonObject.get("returnvalue");
        Assert.assertTrue(jsonObject.get("returnStatusCode").equals("200"));

    }

    @Test(description = "查询房源",
            dependsOnMethods = {"testLoginAMS","testSelectCity","testIndexPage","testHireHourse","testAddHouse"})
    //http://ams.t.ziroom.com/AMS/hire/hiredealReport!selectHouse.action
    public void testQueryHouse(){
        JSONObject jsonObject = httpRequest.getGetReturn("http://ams.t.ziroom.com/AMS/hire/hiredealReport!selectHouse.action");
        String html = (String) jsonObject.get("returnValue");
        Map<String,String> retMap = JsoupUtil.getSpecifiedElement("td",html);
//        logger.info("返回map信息: " + retMap);
        Set<Map.Entry<String, String>> mapEntry = retMap.entrySet();
        Iterator it = mapEntry.iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.contains("BJW")){
                hireContactcode = key;
                logger.info("返回测试数据key : " + key + "返回value : " + value);
                break;
            }


        }
//        Assert.assertTrue(jsonObject.get("returnStatusCode").equals("200"));
    }

    @Test(description = "保存房源",
            dependsOnMethods = {"testLoginAMS","testSelectCity","testIndexPage","testHireHourse","testAddHouse","testQueryHouse"})
    //http://ams.t.ziroom.com/AMS/hire/hiredealReport!saveRentDealReport.action post hiredid
    public void testAddAndSaveHouse(){
        logger.info("合同ID为 : " + hireContactcode);
        Map<String,String> ret;
        String queryhireId = String.format("SELECT ID FROM BZ_HIRE_CONTRACT WHERE HIRE_CONTRACT_CODE='%s'",hireContactcode);
        List<Map<String,String>> retlist = oracleSqlHelper.getTableData(queryhireId,1,"svdp");
        if (retlist!= null){
            hireId = retlist.get(0).get("ID");
            logger.info("hiredId为:"+ hireId);
            Map<String,String> requestMap = Maps.newHashMap();
            requestMap.put("hireId",hireId);

            ret = httpRequest.
                    getPostReturnStrValue("http://ams.t.ziroom.com/AMS/hire/hiredealReport!saveRentDealReport.action",requestMap);

        }

    }

    @Test(description = "显示审批合同" ,
            dependsOnMethods = {"testLoginAMS","testSelectCity","testIndexPage","testHireHourse","testAddHouse","testQueryHouse","testAddAndSaveHouse"})
    //http://ams.t.ziroom.com/AMS/hire/audit!listContractAudit.action
    public void testListAuditContract(){
        Map<String,String> requestMap = Maps.newHashMap();
        requestMap.put("filter_and_hireContractCode_LIKE_S", hireContactcode);
        requestMap.put("_pageNum", "1");
        requestMap.put("_pageSize", "12");
        Map<String,String> retMap = httpRequest.
                getPostReturnStrValue("http://ams.t.ziroom.com/AMS/hire/audit!listContractAudit.action",requestMap);
        String html = retMap.get("returnValue");
        Map<String,String> htmlmap = JsoupUtil.getSpecifiedElement("a",html);
        logger.info("retMap : " + htmlmap);
        Assert.assertTrue(htmlmap.containsKey(hireContactcode));

    }

    @Test(description = "点击审批通过",
            dependsOnMethods = {"testLoginAMS","testSelectCity","testIndexPage",
                    "testHireHourse","testAddHouse","testQueryHouse","testAddAndSaveHouse","testListAuditContract"})
    //http://ams.t.ziroom.com/AMS/hire/hireContract!indexHireContract.action?isPass=0&isDisabled=1&isAudit=audit
    public void testAuditContract(){
        Map<String,String> requestMap = Maps.newHashMap();
        requestMap.put("filter_and_hireContractCode_LIKE_S",hireContactcode);
        requestMap.put("_pageNum","1");
        requestMap.put("_pageSize","12");
        requestMap.put("isDefault","0");
        requestMap.put("id",hireId);
        Map<String,String> retmap = httpRequest.
                getPostReturnStrValue("http://ams.t.ziroom.com/AMS/hire/hireContract!indexHireContract.action?isPass=0&isDisabled=1&isAudit=audit",requestMap);

    }


    @AfterClass
    public void close(){
//        extentReports.close();
    }


}
