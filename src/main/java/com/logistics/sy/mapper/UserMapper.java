package com.logistics.sy.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.McusmaVO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaVO;
import com.logistics.sy.domain.GridColumnLayoutDTO;
import com.logistics.sy.domain.GridSettingLayoutDTO;
import com.logistics.sy.domain.RoleGroupVo;
import com.logistics.sy.domain.UserDTO;
import com.logistics.sy.domain.UserMenuDTO;
import com.logistics.sy.domain.UserRoleHeaderDTO;
import com.logistics.sy.domain.UserRoleProgramDTO;
import com.logistics.sy.domain.UserRoleWarehouseDTO;
import com.logistics.sy.domain.UserVo;

/* xml mapper :  mybatis header */
@Mapper
public interface UserMapper { 
	
	// 회원가입 : 사용자 등록
	void insertUserBySignUp(UserVo userVo);
		
    // 사용자   
	void insertUser(UserVo userVo);
	void insertUser(UserDTO userDTO);
	
	UserVo selectUserCompanyAccount(Map<String, Object> paramMap);    
    // User By Role
	List<UserRoleProgramDTO> selectCompanyUserRoleList(Map<String, Object> paramMap); 
	// User by Menu
	List<UserMenuDTO> selectCompanyUserMenuList(Map<String, Object> paramMap);
	// user warehouse
	List<Map<String, Object>> selectCompanyUserWarehouseList(Map<String, Object> paramMap);	
	
	// user combobox
	List<UserVo> selectSusrmaBox(Map<String, Object> paramMap);
	
	
	//SU1 : 사용자리스트
	List<UserDTO> selectCompanyUser(Map<String, Object> paramMap);
	int updateUser(HashMap<String, Object> map);
	int insertUser(HashMap<String, Object> map);
    
	//SU2 : Menu Header List 조회
	List<UserMenuDTO> selectHeaderMenu(Map<String, Object> map);
	int insertHeaderMenu(UserMenuDTO dto);
	int updateHeaderMenu(UserMenuDTO dto);
	int deleteHeaderMenu(HashMap<String, Object> map);
	List<Map<String, Object>> selectMenuBox(Map<String, Object> map);
	
	//SU2 : Menu Item List 조회
	List<UserMenuDTO> selectItemMenu(Map<String, Object> map);
	int insertItemMenu(HashMap<String, Object> map);
	int updateItemMenu(HashMap<String, Object> map);
	int deleteItemMenu(HashMap<String, Object> map);
	
		
	//SU3 : Role Master List 조회
	List<UserRoleHeaderDTO> selectRoleMaster(Map<String, Object> map);
	int updateRoleMaste(UserRoleHeaderDTO dto);	
	int insertRoleMaste(UserRoleHeaderDTO dto);	
	int deleteRoleMaste(UserRoleHeaderDTO dto);
	List<Map<String, Object>> selectRoleBox(Map<String, Object> map);
	
	//SU3 : Role Program List 조회
	List<UserRoleProgramDTO> selectRoleProgram(Map<String, Object> map);
	int updateRoleProgram(Map<String, Object> map);
	int insertRoleProgram(Map<String, Object> map);
	int deleteRoleProgram(Map<String, Object> map);
	int deleteALLRoleProgram(UserRoleHeaderDTO dto);
	
	//SU3 : Role Warehouse List 조회
	List<UserRoleWarehouseDTO> selectRoleWarehouse(Map<String, Object> map);
	int updateRoleWarehouse(Map<String, Object> map);	
	int insertRoleWarehouse(Map<String, Object> map);	
	int deleteRoleWarehouse(Map<String, Object> map);
	int deleteALLRoleWarehouse(UserRoleHeaderDTO dto);
	
	//SYU4 : Role Group List 조회
	List<RoleGroupVo> selectGroup(Map<String, Object> map);
	int deleteGroup(Map<String, Object> map);	
	int updateGroup(Map<String, Object> map);
	int insertGroup(Map<String, Object> map);
	List<Map<String, Object>> selectGroupBox(Map<String, Object> map);
	
	//사용자 정보 조회
	Map<String, Object> selectUserList(Map<String, Object> param);
	Map<String, Object> selectOwnerUserList(Map<String, Object> param);
	Map<String, Object> selectVendorUserList(Map<String, Object> param);
	Map<String, Object> selectCustomerUserList(Map<String, Object> param);
	
	String findUserPwd(Map<String, Object> paramMap);
	List<Map<String, Object>> findDesigner(Map<String, Object> paramMap);
	
	//사용자 정보 수정
	int updateUserList(UserVo param);
	int updateOwnerList(MowrmaDTO param);
	int updateVenderList(MptnmaVO param);
	int updateCustomerList(McusmaVO param);
	int updateChangePwd(Map<String, Object> param);
	int updateUserBySingOut(Map<String, Object> param);
	
	//사용자 로그인 정보 저장(로그인 시도 이력 정보)
	int insertLoginUser(HashMap<String, Object> map);
	//사용자 프로그램 실행 이력정보 저장
	int insertUserProgram(HashMap<String, Object> map);
	
	//사용자 Grid Setting Layout 조회
	List<GridSettingLayoutDTO> selectGridSettingLayout(Map<String, Object> params);
	//사용자 Grid Column Layout 조회
	List<GridColumnLayoutDTO> selectGridColumnLayout(Map<String, Object> params);
	
	//사용자 Grid Setting Layout 저장
	int mergeSettingLayout(GridSettingLayoutDTO dto);
	//사용자 Grid Column Layout 저장
	int mergeColumnLayout(HashMap<String, Object> map);
	
	//사용자 Grid Setting Layout 삭제
	void deleteColumnLayout(HashMap<String, Object> map);
	//사용자 Grid Column Layout 삭제
	void deleteSettingLayout(HashMap<String, Object> map);
	
	//사용자 메뉴 : 즐겨찾기
	int insertCompanyUserMenuBookmark(HashMap map);
	int deleteCompanyUserMenuBookmark(HashMap map);
	
	// 회원가입 : 유저등록
	int insertOneUser(UserDTO userDTO);

}