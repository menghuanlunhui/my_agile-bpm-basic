package com.dstz.org.core.script;

import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.bus.model.BusinessData;
import com.dstz.org.util.HttpXmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dstz.base.api.exception.BusinessMessage;
import com.dstz.base.core.util.StringUtil;
import com.dstz.org.api.constant.GroupTypeConstant;
import com.dstz.org.api.model.IGroup;
import com.dstz.org.api.model.IUser;
import com.dstz.org.api.service.GroupService;
import com.dstz.org.api.service.UserService;
import com.dstz.sys.api.groovy.IScript;
import com.dstz.sys.api.model.DefaultIdentity;
import com.dstz.sys.api.model.SysIdentity;
import com.dstz.sys.util.ContextUtil;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * 描述：常用org的脚本
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2019年5月26日
 * 版权:summer
 * </pre>
 */
@Component
public class OrgScript implements IScript {
	@Resource
	GroupService groupService;
	@Resource
	UserService userService;

	private static Logger log = LoggerFactory.getLogger(OrgScript.class);

	/**
	 * <pre>
	 * 获取某个组织下的某些角色的人员列表
	 * </pre>
	 *
	 * @param groupIds
	 *            组织id，多个以“,”分隔；为空，则取当前用户所在主组织
	 * @param roleCodes
	 *            角色编码，多个以“,”分隔
	 * @return
	 */
	public Set<SysIdentity> getSisByGroupAndRole(String groupIds, String roleCodes) {
		Set<SysIdentity> identities = new HashSet<>();
		if (StringUtil.isEmpty(groupIds)) {
			if (ContextUtil.getCurrentGroup() == null) {
				throw new BusinessMessage("请为当前人员分配组织");
			}
			groupIds = ContextUtil.getCurrentGroup().getGroupId();
		}
		for (String gi : groupIds.split(",")) {
			List<? extends IUser> users = userService.getUserListByGroup(GroupTypeConstant.ORG.key(), gi);
			users.forEach(user -> {
				List<? extends IGroup> roles = groupService.getGroupsByGroupTypeUserId(GroupTypeConstant.ROLE.key(), user.getUserId());
				roles.forEach(role -> {
					if (roleCodes.contains(role.getGroupCode())) {
						SysIdentity identity = new DefaultIdentity(user.getUserId(), user.getFullname(), SysIdentity.TYPE_USER);
						identities.add(identity);
					}
				});
			});
		}

		return identities;
	}

	/**
	 * 运营物品申请调用外部接口
	 * @param businessDataList 附属信息
	 * @param applyContents
	 * @return
	 */
	@CatchErr("运营物品申请调用外部接口")
	@RequestMapping("pushToOrder")
	public Object pushToOrder(List<BusinessData> businessDataList, String applyContents) throws Exception{
		JSONObject json = new JSONObject();
		if(businessDataList.size()>0){
			for (int i = 0; i < businessDataList.size(); i++) {
				Map<String, Object> object =businessDataList.get(i).getData();
				String jmsCode=object.get("jmsCode").toString();
				String actualRebateAmount=object.get("actualRebateAmount").toString();
				Map params1 = new HashMap();
				params1.put("entranceversions","1");
				params1.put("ssouserid","7684");
				params1.put("pageId","0");
				params1.put("pageNum","8");
				String re="";
				try {
					re = HttpXmlUtil.doPost("http://uk.zhibang.com/sso/web/entrance/getByParam.do", params1);
					System.out.println(re);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}else{
			log.info("运营物品申请调用下单接口，附属信息为空");
		}
		return json;
	}

	/**
	 * 审批成功更新加盟商的累计返利金额
	 * @param businessDataList
	 */
	@CatchErr("审批成功更新加盟商的累计返利金额")
	@RequestMapping("updateRebateMoney")
	public void updateRebateMoney(List<BusinessData> businessDataList){
		if(businessDataList.size()>0){
			for (int i = 0; i < businessDataList.size(); i++) {
				Map<String, Object> object =businessDataList.get(i).getData();
				String jmsCode=object.get("jmsCode").toString();
				String actualRebateAmount=object.get("actualRebateAmount").toString();
				String brandCode=object.get("brandCode").toString();
				int j=userService.updateRebateMoney(jmsCode,actualRebateAmount,brandCode);
				if(j>0){
					System.out.println("加盟商编码："+jmsCode+",更新成功");
				}else{
					System.out.println("加盟商编码："+jmsCode+",更新失败");
				}

			}
		}
	}

	/**
	 * 校验运营发起是否是同一省区同一品牌，若是，汇总省区本年累计返利金额
	 * @param businessDataList
	 */
	@CatchErr("校验运营发起是否是同一省区同一品牌")
	@RequestMapping("judgeSqCodeAndBrand")
	public String judgeSqCodeAndBrand(List<BusinessData> businessDataList){
		JSONObject jsonObject = new JSONObject();
		String brandCode="";
		String sqCode="";
		String sqbnljflje="0";
		if(businessDataList.size()>0){
			for (int i = 0; i < businessDataList.size(); i++) {
				Map<String, Object> businessData =businessDataList.get(i).getData();
				//校验运营发起是否是同一品牌
				String currentBrandCode=businessData.get("brandCode").toString();
				if("".equals(brandCode)){
					brandCode=currentBrandCode;
				}else{
					if(!brandCode.equals(currentBrandCode)){//选择的品牌不一致
						/*jsonObject.put("msg","选择的品牌不一致");
						jsonObject.put("state",false);*/
						return sqbnljflje;
					}
				}
				//校验运营发起是否是同一省区
				String currentSqCode=businessData.get("sqCode").toString();
				if("".equals(sqCode)){
					sqCode=currentSqCode;
				}else{
					if(!sqCode.equals(currentSqCode)){//选择的省区不一致
						/*jsonObject.put("msg","选择的省区不一致");
						jsonObject.put("state",false);*/
						return sqbnljflje;
					}
				}
			}
			//汇总省区返利金额
			sqbnljflje=userService.getSqCurrentYearRebate(sqCode);
		}
		if(sqbnljflje==null){
			sqbnljflje="0";
		}
		return sqbnljflje;
	}


}
