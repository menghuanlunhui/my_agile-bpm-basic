package com.dstz.org.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.org.core.model.OrgRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组织关系 DAO接口
 * @author chenyun
 * @time 2019-10-16 01:07:59
 */
public interface OrgRelationDao extends BaseDao<String, OrgRelation> {


	List<OrgRelation> getPostByUserIdAndGroupId(@Param("userId") String userId, @Param("groupId") String groupId);

	/**
	 * 获取用户的 关系
	 * @param userId 必须
	 * @param relationType 非必须
	 * @return
	 */
	List<OrgRelation> getUserRelation(@Param("userId") String userId, @Param("relationType") String relationType);

	/**
	 * 通过 userId 刪除所有關係
	 * @param userId
	 */
	void removeByUserId(String userId);

	/**
	 * 获取组织岗位
	 * @param groupId
	 * @return
	 */
	List<OrgRelation> getGroupPost(String groupId);

	/**
	 * 删除 组下的岗位
	 * @param id
	 */
	void removeGroupPostByGroupId(String id);

	/**
	 * 通过 参数查询关系列表
	 * @param relationTypes 关系类型
	 * @param userId 用户ID
	 * @param groupId 组织ID
	 * @param roleId 角色ID
	 * @return
	 */
	List<OrgRelation> getRelationsByParam(@Param("relationTypes") List<String> relationTypes, @Param("userId") String userId, @Param("groupId") String groupId, @Param("roleId") String roleId);
	
	/**
	 * @param relation userId,roleId,groupId,relation 存在则相等判断
	 *   relation.id 存在则非匹配
	 * @return
	 */
	int getCountByRelation(OrgRelation relation);
	
	/**
	 * 获取用户角色，含岗位
	 * @param userId
	 * @return
	 */
	List<OrgRelation> getUserRole(String userId);
	
	/**
	 * 获取岗位
	 * @param id
	 * @return
	 */
	OrgRelation getPost(String id);
	
}
