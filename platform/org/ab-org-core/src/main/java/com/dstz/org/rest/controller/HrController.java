package com.dstz.org.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.rest.ControllerTools;
import com.dstz.org.core.manager.*;
import com.dstz.org.core.model.*;
import com.dstz.org.service.HrService;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenyun
 * @projectName code_pro
 * @description: TODO
 * @date 2019/10/8 15:21
 */
@Controller
@RequestMapping("/hr/")
public class HrController extends ControllerTools {

    private static Logger log = LoggerFactory.getLogger(HrController.class);

    /****************正式环境配置*************************/
    //同步人力系统部门数据url
    protected static final String  DEPARTMENT_URL = "http://10.10.10.71:8089/v1/ps/get/department/sync";
    //同步人力系统人员数据url
    protected static final String  EMPLOYEE_URL = "http://10.10.10.72:8089/v1/ps/get/employee/sync";
    //同步人力系统岗位数据url
    protected static final String  JOB_URL = "http://10.10.10.72:8089/v1/ps/get/job/sync";
    //同步中台加盟商数据url
    protected static final String  CUSTACCOUNTS_URL = "http://10.10.10.71:8083/v1/oms/custAccounts/read";

    protected static final String  USER_NAME = "SSO";
    protected static final String  USER_PASWORD = "#SSO#@";

    @Autowired
    HrService hrService;

    @Resource
    UserManager userManager;

    @Resource
    GroupManager groupManager;

    @Resource
    OrgRelationManager orgRelationManager;

    @Resource
    RoleManager roleManager;

    @Resource
    JmsInfoManager jmsInfoManager;

    @Resource
    SysDistrManager sysDistrManager;

    /**
     * 从人力系统同步部门信息
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/departmentSync")
    @ResponseBody
    public JSONObject departmentSync(HttpServletResponse response)throws Exception{
        response.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject resultJson = new JSONObject();
        String body="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ps=\"http://ps.hr.service/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ps:getDepartment/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        //发送请求代码
        URL url = new URL(DEPARTMENT_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
       /* conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);*/
        String encoding = new String(Base64.encode(new String(USER_NAME+":"+USER_PASWORD).getBytes()));
        conn.setRequestProperty( "Authorization","Basic "+encoding);
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(body.getBytes("utf-8"));//params就是上面生成的xml内容
        dos.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer strBuf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuf.append(line);
        }
        dos.close();
        reader.close();
        String rs = strBuf.toString();
        resultJson.put("status","success");

        List<Map<String,Object>> mapList = hrService.parse(rs);
        for (int i = 0; i < mapList.size(); i++) {
            Group group = new Group();
            Map<String,Object> map =mapList.get(i);
            if(map.get("departmentCode")!=null){
                group.setId(map.get("departmentCode").toString());
                group.setCode(map.get("departmentCode").toString());
            }
            if(map.get("departmentFullName")!=null){
                group.setName(map.get("departmentFullName").toString());
            }
            if(map.get("departmentShortName")!=null){
                group.setDesc(map.get("departmentShortName").toString());
            }
            if(map.get("parentDepartmentCode")!=null){
                group.setParentId(map.get("parentDepartmentCode").toString());
            }
            if(group.getCode()!=null && !"".equals(group.getCode())){
                Group selectGroup = groupManager.getByCode(group.getCode());
                if(selectGroup!=null){
                    groupManager.update(group);
                }else{
                    groupManager.create(group);
                }
            }
        }
        resultJson.put("msg","success");
        return resultJson;
    }

    /**
     * 从人力系统同步人员信息
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/employeeSync")
    @ResponseBody
    public JSONObject employeeSync(HttpServletResponse response)throws Exception{
        response.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject resultJson = new JSONObject();
        String body="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ps=\"http://ps.hr.service/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ps:getEmployee/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        //发送请求代码
        URL url = new URL(EMPLOYEE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
       /* conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);*/
        String encoding = new String(Base64.encode(new String(USER_NAME+":"+USER_PASWORD).getBytes()));
        conn.setRequestProperty( "Authorization","Basic "+encoding);
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(body.getBytes("utf-8"));//params就是上面生成的xml内容
        dos.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer strBuf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuf.append(line);
        }
        dos.close();
        reader.close();
        String rs = strBuf.toString();
        resultJson.put("status","success");

        List<Map<String,Object>> mapList = hrService.parse(rs);
        for (int i = 0; i < mapList.size(); i++) {
            User user = new User();
            OrgRelation orgRelation = new OrgRelation();
            Map<String,Object> map =mapList.get(i);
            if(map.get("employeeCode")!=null){
                user.setId(map.get("employeeCode").toString());
                user.setAccount(map.get("employeeCode").toString());
                user.setFrom("PS系统同步");
                user.setPassword("a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=");
                //人员所在组织信息
                orgRelation.setGroupId(map.get("departmentCode").toString());
                orgRelation.setUserId(user.getId());
                orgRelation.setRoleId(map.get("jobCode").toString());
                orgRelation.setType("groupUserRole");

            }
            if(map.get("employeeName")!=null){
                user.setFullname(map.get("employeeName").toString());
            }
            if(map.get("cellPhone")!=null){
                user.setMobile(map.get("cellPhone").toString());
            }
            if(map.get("address")!=null){
                user.setAddress(map.get("address").toString());
            }
            if(map.get("sex")!=null){
                user.setSex(map.get("sex").toString());
            }

            if(map.get("employeeStatus")!=null){
                if("A".equals(map.get("employeeStatus").toString())){//A有效
                    user.setStatus(1);
                }else{
                    user.setStatus(0);
                }
            }

            //新增或更新人员信息
            if(user.getAccount()!=null && !"".equals(user.getAccount())){
                User selectUser = userManager.getByAccount(user.getAccount());
                if(selectUser!=null){
                    userManager.update(user);
                }else{
                    userManager.create(user);
                }
            }

            //新增或更新人员组织关联信息
            if(orgRelation.getGroupId()!=null && !"".equals(orgRelation.getGroupId())){
                List<OrgRelation> orgRelationList = orgRelationManager.getPostByUserIdAndGroupId(orgRelation.getUserId(),orgRelation.getGroupId());
                if(orgRelationList.size()>0){
                    orgRelation.setId(orgRelationList.get(0).getId());
                    orgRelationManager.update(orgRelation);
                }else{
                    orgRelationManager.create(orgRelation);
                }
            }
        }
        resultJson.put("msg","success");
        return resultJson;
    }


    /**
     * 获取职位信息
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/jobSync")
    @ResponseBody
    public JSONObject jobSync(HttpServletResponse response)throws Exception{
        response.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject resultJson = new JSONObject();
        String body="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ps=\"http://ps.hr.service/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ps:getJob/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        //发送请求代码
        URL url = new URL(JOB_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
       /* conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);*/
        String encoding = new String(Base64.encode(new String(USER_NAME+":"+USER_PASWORD).getBytes()));
        conn.setRequestProperty( "Authorization","Basic "+encoding);
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(body.getBytes("utf-8"));//params就是上面生成的xml内容
        dos.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer strBuf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuf.append(line);
        }
        dos.close();
        reader.close();
        String rs = strBuf.toString();
        resultJson.put("status","success");

        List<Map<String,Object>> mapList = hrService.parse(rs);
        for (int i = 0; i < mapList.size(); i++) {
            Role role = new Role();
            Map<String,Object> map =mapList.get(i);
            if(map.get("jobCode")!=null){
                role.setId(map.get("jobCode").toString());
            }
            if(map.get("jobName")!=null){
                role.setName(map.get("jobName").toString());
                role.setDescription(map.get("jobName").toString());
                role.setAlias(ToPinyin(map.get("jobName").toString()));
            }

            if(map.get("jobStatus")!=null){
                if("A".equals(map.get("jobStatus").toString())){//A有效
                    role.setEnabled(1);
                }else{
                    role.setEnabled(0);
                }
            }

            if(role.getId()!=null && !"".equals(role.getId())){
                Role selectRole = roleManager.get(role.getId());
                if(selectRole!=null){
                    roleManager.update(role);
                }else{
                    roleManager.create(role);
                }
            }
        }
        resultJson.put("msg","同步成功");
        return resultJson;
    }

    /**
     * 汉字转为拼音
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }


    /**
     * 同步加盟商信息
     * @param response
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @throws Exception
     */
    @RequestMapping("/jmsSync")
    @ResponseBody
    public JSONObject jmsSync(HttpServletResponse response,String startDate,String endDate)throws Exception{
        startDate="2010-10-01";
        endDate="2019-10-17";
        response.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject resultJson = new JSONObject();
        System.out.println("开始同步中台数据---------------------------");
        //请求时间为空时，默认查询
        if(StringUtils.isEmpty(startDate)||StringUtils.isEmpty(endDate)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = new Date();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            calendar2.add(Calendar.DAY_OF_MONTH, -3);
            date2 = calendar2.getTime();
            startDate=sdf.format(date2);

            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            date = calendar.getTime();
            endDate=sdf.format(date);
        }

        String requestPara = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:zbom=\"http://zbom.oms.customer.read.service/\"><soapenv:Header/><soapenv:Body><zbom:customerRead><startDate>"
                + startDate + "</startDate>\r\n" + "<endDate>" + endDate
                + "</endDate></zbom:customerRead></soapenv:Body></soapenv:Envelope>";

        //发送请求代码
        URL url = new URL(CUSTACCOUNTS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
       /* conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);*/
        String encoding = new String(Base64.encode(new String(USER_NAME+":"+USER_PASWORD).getBytes()));
        conn.setRequestProperty( "Authorization","Basic "+encoding);
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(requestPara.getBytes("utf-8"));//params就是上面生成的xml内容
        dos.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer strBuf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuf.append(line);
        }
        dos.close();
        reader.close();
        String rs = strBuf.toString();
        resultJson.put("status","success");

        List<Map<String,Object>> mapList = hrService.parse(rs);
        for (int i = 0; i < mapList.size(); i++) {
            Map<String,Object> map =mapList.get(i);
            if(map.get("accountNumber")!=null){//加盟商
                JmsInfo jmsInfo = new JmsInfo();
                if(map.get("custAccountId")!=null){
                    jmsInfo.setCustAccountId(map.get("custAccountId").toString());
                }
                if(map.get("accountNumber")!=null){
                    jmsInfo.setAccountNumber(map.get("accountNumber").toString());
                }
                if(map.get("accountName")!=null){
                    jmsInfo.setAccountName(map.get("accountName").toString());
                }
                if(map.get("accountShortName")!=null){
                    jmsInfo.setAccountShortName(map.get("accountShortName").toString());
                }
                if(map.get("taxRegistrationNum")!=null){
                    jmsInfo.setTaxRegistrationNum(map.get("taxRegistrationNum").toString());
                }
                if(map.get("registrationAddress")!=null){
                    jmsInfo.setRegistrationAddress(map.get("registrationAddress").toString());
                }
                if(map.get("legalPerson")!=null){
                    jmsInfo.setLegalPerson(map.get("legalPerson").toString());
                }
                if(map.get("legalIdentification")!=null){
                    jmsInfo.setLegalIdentification(map.get("legalIdentification").toString());
                }
                if(map.get("contactPhone")!=null){
                    jmsInfo.setContactPhone(map.get("contactPhone").toString());
                }
                if(map.get("accountOwner")!=null){
                    jmsInfo.setAccountOwner(map.get("accountOwner").toString());
                }
                if(map.get("accountIdentification")!=null){
                    jmsInfo.setAccountIdentification(map.get("accountIdentification").toString());
                }
                if(map.get("organizationType")!=null){
                    jmsInfo.setOrganizationType(map.get("organizationType").toString());
                }
                if(map.get("province")!=null){
                    jmsInfo.setProvince(map.get("province").toString());
                }
                if(map.get("city")!=null){
                    jmsInfo.setCity(map.get("city").toString());
                }
                if(map.get("accountLevel")!=null){
                    jmsInfo.setAccountLevel(map.get("accountLevel").toString());
                }
                if(map.get("status")!=null){
                    jmsInfo.setStatus(map.get("status").toString());
                }

                if(jmsInfo.getAccountNumber()!=null && !"".equals(jmsInfo.getAccountNumber())){
                    JmsInfo selectJmsInfo  = jmsInfoManager.getJmsInfoByAccountNumber(jmsInfo.getAccountNumber());
                    if(selectJmsInfo!=null){
                        jmsInfo.setUpdateTime(new Date());
                        jmsInfoManager.updateByAccountNumber(jmsInfo);
                    }else{
                        jmsInfoManager.createJmsInfo(jmsInfo);
                    }
                }
            }else if(map.get("custAccountId")!=null && map.get("accountNumber")==null){//加盟商品牌
                SysDistr sysDistr = new SysDistr();
                sysDistr.setCustAccountId(map.get("custAccountId").toString());
                JmsInfo jmsInfo =jmsInfoManager.getJmsInfoByCustAccountId(map.get("custAccountId").toString());
                if(jmsInfo!=null){
                    sysDistr.setDistrcode(jmsInfo.getAccountNumber());
                    sysDistr.setDistrname(jmsInfo.getAccountName());
                    sysDistr.setProvince(jmsInfo.getProvince());
                    sysDistr.setProvinceName(jmsInfo.getProvinceName());
                    sysDistr.setCity(jmsInfo.getCity());
                    sysDistr.setCityName(jmsInfo.getCityName());
                    sysDistr.setAccountShortName(jmsInfo.getAccountShortName());
                    sysDistr.setRegistrationAddress(jmsInfo.getRegistrationAddress());
                    sysDistr.setLegalPerson(jmsInfo.getLegalPerson());
                    sysDistr.setSource("中台系统同步");
                    if(map.get("segment1")!=null){
                        sysDistr.setCenterCode(map.get("segment1").toString());
                    }
                    if(map.get("segment1Name")!=null){
                        sysDistr.setCenterName(map.get("segment1Name").toString());
                    }
                    if(map.get("segment2")!=null){
                        sysDistr.setSqCode(map.get("segment2").toString());
                    }
                    if(map.get("segment2Name")!=null){
                        sysDistr.setSqName(map.get("segment2Name").toString());
                    }
                    if(map.get("segment3")!=null){
                        sysDistr.setPqCode(map.get("segment3").toString());
                    }
                    if(map.get("segment3Name")!=null){
                        sysDistr.setPqName(map.get("segment3Name").toString());
                    }
                    if(map.get("operationStatus")!=null){
                        if("NORMAL".equals(map.get("operationStatus").toString())){
                            sysDistr.setDistrstatus(1);
                        }else{
                            sysDistr.setDistrstatus(-1);
                        }
                    }
                    if(map.get("unitId")!=null){
                        sysDistr.setBrandCode(map.get("unitId").toString());
                        if("104".equals(map.get("unitId").toString())){
                            sysDistr.setBrandName("志邦厨柜");
                        }else if("112".equals(map.get("unitId").toString())){
                            sysDistr.setBrandName("志邦衣柜");
                        }else if("161".equals(map.get("unitId").toString())){
                            sysDistr.setBrandName("志邦木门");
                        }
                        if("104".equals(map.get("unitId").toString())||"112".equals(map.get("unitId").toString())||"161".equals(map.get("unitId").toString())){
                            SysDistr selectSysDistr = sysDistrManager.getSysDistrByCodeAndId(sysDistr.getBrandCode(),sysDistr.getCustAccountId());
                            if(selectSysDistr!=null){
                                sysDistr.setUpdateTime(new Date());
                                sysDistr.setDistrid(selectSysDistr.getDistrid());
                                sysDistrManager.updateSysDistrByAccountNumber(sysDistr);
                            }else{
                                sysDistrManager.createSysDistr(sysDistr);
                            }
                        }
                    }
                }
            }
        }

        resultJson.put("msg","同步成功");
        return resultJson;
    }

}
