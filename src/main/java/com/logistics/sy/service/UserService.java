package com.logistics.sy.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McusmaVO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaVO;
import com.logistics.md.mapper.BoardMapper;
import com.logistics.sy.domain.UserDTO;
import com.logistics.sy.domain.UserMenuDTO;
import com.logistics.sy.domain.UserRoleProgramDTO;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.domain.GridColumnLayoutDTO;
import com.logistics.sy.domain.GridSettingLayoutDTO;
import com.logistics.sy.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
    MessageSource messageSource;
	@Autowired
	private  MessageSourceAccessor messageSourceAccessor;
    @Autowired
	private UserMapper userMapper;
	@Autowired
    private BoardMapper boardMapper;
	 
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
	Date time = new Date();
	String localTime = format.format(time);
	String creDate = formatDate.format(time);
	String creTime = formatTime.format(time);

	// 사용자 등록
	
	@Transactional(rollbackFor = Exception.class)
	public void insertUser(UserDTO userDTO) {
		// 신규사용자 정보 보안
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDTO.setPasswrd(passwordEncoder.encode(userDTO.getPasswrd()));// 비번 암호화
		
		//약관 동의 check
		userDTO.setPolpryn(userDTO.getPolpryn()== null ? "N" : "Y");
		userDTO.setPolsvyn(userDTO.getPolsvyn()== null ? "N" : "Y");
		userDTO.setPolsmyn(userDTO.getPolsmyn()== null ? "N" : "Y");
		userDTO.setPolemyn(userDTO.getPolemyn()== null ? "N" : "Y");
		
		int insertCnt = userMapper.insertOneUser(userDTO);
		
		if(insertCnt == 0) {
			new InsertCheckedException();
		}
	}
	
	
	/*
	 * CustomUserDetailService 에서 호출:: login form 에서 로그인시 서비스로 넘어옴. 계정에 속한 사용 자중,
	 * 비번일치를 찾아준다.
	 */
	
	public UserVo getUserInfo(String compkey, String useract) {
		// 여기서 받은 유저 패스워드와 비교하여 로그인 인증
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("compkey", compkey);
		paramMap.put("useract", useract);

		UserVo userVo = userMapper.selectUserCompanyAccount(paramMap);
		if (userVo == null) {
			throw new UsernameNotFoundException("User not authorized.");
		}
		return userVo;
	}

	/*
	 * 사용자 role list 가져온다 사용안함 map
	 */
	 
	public List<UserRoleProgramDTO> getUserByRole(String compkey,	  String useract) 
	{ 
		  Map<String, Object> paramMap = new HashMap<String,Object>(); 
		  paramMap.put("compkey", compkey); paramMap.put("useract",	  useract);
	  
		  List<UserRoleProgramDTO> userRoleList = Collections.emptyList(); //여기서 받은 유저 패스워드와 비교하여 로그인 인증 
		  userRoleList = userMapper.selectCompanyUserRoleList(paramMap); return userRoleList; 
	}
	 
	/*
	 * 사용자 role map 가져온다
	 */
	
	public Map<String, UserRoleProgramDTO> getUserByRoleMap(String compkey, String useract) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("compkey", compkey);
		paramMap.put("useract", useract);

		List<UserRoleProgramDTO> userRoleList = Collections.emptyList();
		// 여기서 받은 유저 패스워드와 비교하여 로그인 인증
		userRoleList = userMapper.selectCompanyUserRoleList(paramMap);

		Map<String, UserRoleProgramDTO> UserByRoleMap = new HashMap<String, UserRoleProgramDTO>();
		for (UserRoleProgramDTO item : userRoleList) {
			UserByRoleMap.put(item.getProgrid(), item);
		}

		return UserByRoleMap;
	}

	/*
	 * 사용자 로그인 정보 저장(로그인 시도 이력 정보)
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public void insertLoginUser(String compkey, String useract, String warekey, String loginip, String secchua,
			String secchmb, String secchpf, String haccept, String navplat, String navvers, String loginsf) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("compkey", compkey);
		hashMap.put("useract", useract);
		hashMap.put("warekey", warekey);
		hashMap.put("loginip", loginip);
		hashMap.put("secchua", secchua);
		hashMap.put("secchmb", secchmb);
		hashMap.put("secchpf", secchpf);
		hashMap.put("haccept", haccept);
		hashMap.put("navplat", navplat);
		hashMap.put("navvers", navvers);
		hashMap.put("loginsf", loginsf);
		hashMap.put("creuser", useract);

		int i = userMapper.insertLoginUser(hashMap);
		if (i == 0) {
			throw new InsertCheckedException();
		}
	}

	/*
	 * 사용자 프로그램 실행 이력정보 저장
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public void insertUserProgram(Map<String, Object> params, UserVo userInfo) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("compkey", userInfo.getCompkey());
		hashMap.put("useract", userInfo.getUseract());
		hashMap.put("progrid", params.get("gprogrid"));
		hashMap.put("menunam", params.get("gnatitle"));
		hashMap.put("themety", userInfo.getThemety());
		hashMap.put("creuser", userInfo.getUseract());

		int i = userMapper.insertUserProgram(hashMap);
		if (i == 0) {
			throw new InsertCheckedException();
		}
	}

	/*
	 * 사용자 Menu List
	 */
	
	public List<UserMenuDTO> getUserByMenu(String compkey, String useract) {
		String msg = null;
		String menulky = null;
		String menunam = null;

		List<UserMenuDTO> userMenus = Collections.emptyList();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("compkey", compkey);
		paramMap.put("useract", useract);

		userMenus= userMapper.selectCompanyUserMenuList(paramMap); 
		
		List<UserMenuDTO> userMenuList = new ArrayList<>();
		for (UserMenuDTO item : userMenus) {
			menulky = item.getMenulky();
			menunam = item.getMenunam();
			//System.out.println(Locale.getDefault());
			msg = messageSourceAccessor.getMessage(menulky, Locale.getDefault()).toString();
			item.setMenunam(msg);
			userMenuList.add(item); 
		} 
		return userMenuList;
	}
	/*
	 * 사용자 bookmark map 설정
	 */
	
	public Map<String, String> getUserBookmarkMenu(List<UserMenuDTO> userMenuList) {
		String menukey = null;
		String menugbn = null;
		
		Map<String, String> UserByMenubookmarkMap = new HashMap<String, String>();
		for (UserMenuDTO item : userMenuList) {
			menukey = item.getMenukey();
			menugbn = item.getMenugbn();
			if(menukey.equals("*BOOKMARK") && menugbn.equals("PGM")) {
				UserByMenubookmarkMap.put(item.getProgrid(), "Y");	
			}
		}
		return UserByMenubookmarkMap;
	}
	
	
	/*
	 * 사용자 Menu : 즐겨찾기 추가/삭제
	 */	
	
	@Transactional(rollbackFor = Exception.class)
	public int toggleUserBookmarkMenu(Map<String, Object> params, UserVo userVo) {
		String progrid = params.get("progrid").toString();
		String bookmark = params.get("bookmark").toString();
		
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("compkey", userVo.getCompkey());
		hashMap.put("useract", userVo.getUseract());
		hashMap.put("progrid", progrid);
		
		int retcnt = 0;
		
		if(bookmark.equals("N")) {
			// DB에 있는 즟찾 삭제
			retcnt = userMapper.deleteCompanyUserMenuBookmark(hashMap);
			if (retcnt == 0) {
				throw new InsertCheckedException();
			}
			
			// 메모리에 있는 메뉴(즟찾) 삭제
			for( UserMenuDTO item : userVo.getUserMenuVOList()){			
				if (item.getMenukey().equals("*BOOKMARK")) {
					if ( item.getProgrid().equals(progrid) ) {
						userVo.getUserMenuVOList().remove(item);
						break;
					} 
				}
			}
			
			// 메모리에 있는 북마크 삭제
			userVo.getUserMenubookmarkMap().remove(progrid);
			 
			
		} else {
			// DB에 즟찾 추가
			retcnt = userMapper.insertCompanyUserMenuBookmark(hashMap);
			if (retcnt == 0) {
				throw new InsertCheckedException();
			}
			
			// 메모리에 메뉴(즟찾) 추가
			UserMenuDTO itemNew = new UserMenuDTO();			
			for( UserMenuDTO item : userVo.getUserMenuVOList()){
				if ( item.getProgrid().equals(progrid) ) {
					itemNew = item;
					itemNew.setMenukey("*BOOKMARK");
					itemNew.setMenuiky("0000000000");
					itemNew.setMenulvl(2);
					break;
				}
			}
			userVo.getUserMenuVOList().add(1, itemNew);
			
			// 메모리에 있는 북마크 추가
			userVo.getUserMenubookmarkMap().put(progrid, "Y");
		}   	
		 
					
		//userVo.getUserMenuVOList();
		return retcnt; 
	}
//	
//	/*
//	 * 사용자 Menu : 즐겨찾기 추가 ... 개발중...
//	 */
//	
//	@Transactional(rollbackFor = Exception.class)
//	public void insertUserBookmarkMenu(Map<String, Object> params, UserVo userVo) {
//		//HashMap<String, Object> hashMap = new HashMap<>();
//		//int i = userMapper.insertCompanyUserMenuBookmark(userBMDTO);
//		//if (i == 0) {
//		//	throw new InsertCheckedException();
//	//	}
//	}
//
//	/*
//	 * 사용자 Menu : 즐겨찾기 삭제
//	 */
//	
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteUserBookmarkMenu(Map<String, Object> params, UserVo userVo) {
//		
//		HashMap<String, Object> hashMap = new HashMap<>();
//		hashMap.put("compkey", userVo.getCompkey());
//		hashMap.put("useract", userVo.getUseract());
//		hashMap.put("progrid", params.get("gprogrid"));  
//		
//		
//		//HashMap<String, Object> hashMap = new HashMap<>();
//		int i = userMapper.deleteCompanyUserMenuBookmark(hashMap);
//		if (i == 0) {
//			throw new DeleteCheckedException();
//		}
//	}
//	
	/*
	 * 사용자 청고 List
	 */
	
	public List<Map<String, Object>> getUserByWarehouse(String compkey, String useract) throws SQLException {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("compkey", compkey);
		paramMap.put("useract", useract);

		List<Map<String, Object>> list;
		// ArrayList<HashMap<String, String>> arrList;

		list = userMapper.selectCompanyUserWarehouseList(paramMap);
		return list;
	}

	
	public Map<String, Object> selectUserList(UserVo userInfo) {
		HashMap<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("compkey", userInfo.getCompkey());
		param.put("useract", userInfo.getUseract());
		param.put("usertyp", userInfo.getUsertyp());
		
		map.put("BINBLUR", userMapper.selectUserList(param));
		if (param.get("usertyp").equals("OWNER")) { // 화주
			map.put("OWNER", userMapper.selectOwnerUserList(param));
		} else if (param.get("usertyp").equals("VENDER")) { // 공장
			map.put("VENDER", userMapper.selectVendorUserList(param));
		} else if (param.get("usertyp").equals("CUSTOMER")) { // 매장
			map.put("CUSTOMER", userMapper.selectCustomerUserList(param));
		}

		return map;
	}

	
	public String autheticateByUserPwd(String passwrd, UserVo userInfo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("compkey", userInfo.getCompkey());
		paramMap.put("useract", userInfo.getUseract());
		
		return userMapper.findUserPwd(paramMap);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public boolean saveUserProfile(Map<String, Object> param , UserVo userInfo, HttpServletRequest req) {
		boolean result = true;

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		UserVo userList = mapper.convertValue(param.get("BINBLUR"), UserVo.class);
		
		String securePasswrd = "";
		
		if (userList.getPasswrd() != null && !userList.getPasswrd().equals("")) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			securePasswrd = encoder.encode(userList.getPassword());
			userList.setPasswrd(securePasswrd);
		}

		userList.setCompkey(userInfo.getCompkey());
		userList.setUseract(userInfo.getUseract());
		userList.setLmouser(userInfo.getUseract());

		userList.setFavoryn(userList.getFavoryn().equals("true") ? "Y" : "N" );
		userMapper.updateUserList(userList);

		if (userList.getUsertyp().equals("OWNER")) {// 화주
			MowrmaDTO ownerList = mapper.convertValue(param.get("OWNER"), MowrmaDTO.class);
			
			ownerList.setCompkey(userInfo.getCompkey());    
			ownerList.setLmouser(userInfo.getUseract());    
			userMapper.updateOwnerList(ownerList);
		} else if (userList.getUsertyp().equals("VENDER")) { // 공장
			MptnmaVO venderList = mapper.convertValue(param.get("VENDER"), MptnmaVO.class);
			
			venderList.setCompkey(userInfo.getCompkey());    
			venderList.setLmouser(userInfo.getUseract());    
			userMapper.updateVenderList(venderList);
		} else if (userList.getUsertyp().equals("CUSTOMER")) { // 매장
			McusmaVO customerList = mapper.convertValue(param.get("CUSTOMER"), McusmaVO.class);
			
			customerList.setCompkey(userInfo.getCompkey());    
			customerList.setLmouser(userInfo.getUseract());    
			customerList.setSundavl(customerList.getSundavl().equals("true") ? "Y" : "N" );
			customerList.setSatdavl(customerList.getSatdavl().equals("true") ? "Y" : "N" );
			customerList.setMondavl(customerList.getMondavl().equals("true") ? "Y" : "N" );
			customerList.setTuedavl(customerList.getTuedavl().equals("true") ? "Y" : "N" );
			customerList.setWeddavl(customerList.getWeddavl().equals("true") ? "Y" : "N" );
			customerList.setThudavl(customerList.getThudavl().equals("true") ? "Y" : "N" );
			customerList.setFridavl(customerList.getFridavl().equals("true") ? "Y" : "N" );
			customerList.setNathavl(customerList.getNathavl().equals("true") ? "Y" : "N" );
			
			userMapper.updateCustomerList(customerList);
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("favoryn", userList.getFavoryn());
		
		return result;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public int changeUserPwd(Map<String, Object> param , UserVo userInfo) {
		Map<String, Object> map = new HashMap<>();
		param.put("compkey", userInfo.getCompkey());
		param.put("useract", userInfo.getUseract());
		int updateCnt = 0;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (param.get("passwrd") != null && !param.get("passwrd").equals("")) {
			String securePasswrd = encoder.encode((String) param.get("passwrd"));
			param.put("passwrd", securePasswrd);
			updateCnt = userMapper.updateChangePwd(param);
			
			if(updateCnt == 0) {
				new UpdateCheckedException();
			}
		}
		
		return updateCnt;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public int signOutUser(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		int updateCnt =  userMapper.updateUserBySingOut(param);
		
		if(updateCnt == 0) {
			new UpdateCheckedException();
		}
		return updateCnt;
	}
	
	
	public List<GridSettingLayoutDTO> gridSettingLayout(Map<String, Object> params, UserVo userVo) {
		params.put("userData", userVo);
		List<GridSettingLayoutDTO> dataList = userMapper.selectGridSettingLayout(params);
		if (dataList == null) {
			return Collections.<GridSettingLayoutDTO>emptyList();
		}
		return dataList;
	}
	
	
	public List<GridColumnLayoutDTO> gridColumnLayout(Map<String, Object> params, UserVo userVo) {
		params.put("userData", userVo);
		List<GridColumnLayoutDTO> dataList = userMapper.selectGridColumnLayout(params);
		if (dataList == null) {
			return Collections.<GridColumnLayoutDTO>emptyList();
		}
		return dataList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU10Save(Map<String, Object> params, UserVo userVo) {
		ArrayList dataList = (ArrayList) params.get("data");
		List<GridColumnLayoutDTO> list = new ArrayList<>();
		
		for(int i=0; i<dataList.size(); i++) {
			GridColumnLayoutDTO gcl = new GridColumnLayoutDTO();
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) dataList.get(i);
			gcl.setDataidx(String.valueOf( map.get("dataIndx")));
			gcl.setPhidden((Boolean) map.get("hidden"));
			gcl.setPqalign(String.valueOf( map.get("align")));
			gcl.setDatatyp(String.valueOf( map.get("dataType")));
			if(map.containsKey("width")) {
				gcl.setPqwidth((int) map.get("width"));
			}
			
			list.add(gcl);
		}
		
		IntStream.range(0, list.size()).forEach(i -> list.get(i).setSortnum(i));

		HashMap<String, Object> map = new HashMap<>();
		map.put("userData", userVo);
		map.put("progrid", params.get("progrid"));
		map.put("pgridid", params.get("pgridid"));
		map.put("list", list);
		int cntCol = userMapper.mergeColumnLayout(map);
		if (cntCol == 0) {
			throw new InsertCheckedException();
		}
		
		GridSettingLayoutDTO dto = new GridSettingLayoutDTO();
		dto.setCompkey(userVo.getCompkey());
		dto.setUseract(userVo.getUseract());
		dto.setProgrid((String) params.get("progrid"));
		dto.setPgridid((String) params.get("pgridid"));
		dto.setNubrcel((Boolean) params.get("nubrcel"));
		dto.setHovermd((String) params.get("hovermd"));
		dto.setFrezcol((int) params.get("frezcol"));
		dto.setFrezrow((int) params.get("frezrow"));
		dto.setColbodr((Boolean) params.get("colbodr"));
		dto.setRowbodr((Boolean) params.get("rowbodr"));
		dto.setStrprow((Boolean) params.get("strprow"));
		dto.setGheight((int) params.get("gheight"));
		
		int cntSet = userMapper.mergeSettingLayout(dto);
		if (cntSet == 0) {
			throw new InsertCheckedException();
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU10Reset(Map<String, Object> params, UserVo userVo) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("userData", userVo);
		map.put("progrid", params.get("progrid"));
		map.put("pgridid", params.get("pgridid"));
		
		userMapper.deleteColumnLayout(map);
		userMapper.deleteSettingLayout(map);
	} 


	
	public boolean findId(Map<String, Object> param) {
		// 아이디 중복 확인
		boolean result = false;
		UserVo userVo = userMapper.selectUserCompanyAccount(param);
		if (userVo == null) {
//			 "중복된 사용자 ID 가 존재합니다."
			result = true;
		}else {
			throw new IllegalArgumentException(
					messageSourceAccessor.getMessage("sy.main.uidDuplication", Locale.getDefault()));
		}
		return result;
	}


	
	public List<Map<String, Object>> getBinBlurList(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		List<Map<String ,Object>> boardMap = boardMapper.getBoardMainList(param);
		
		if(boardMap == null) {
			boardMap = Collections.emptyList();
		}
		return boardMap;
	}


	
	public List<Map<String, Object>> findDesigner(Map<String, Object> param, UserVo userInfo) {
		param.put("userInfo", userInfo);
		List<Map<String, Object>> userMap = userMapper.findDesigner(param);
		
		if(userMap == null) {
			userMap = Collections.emptyList();
		}
		return userMap;
	}


}
