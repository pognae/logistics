package com.logistics.md.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MshrhdVO;
import com.logistics.md.domain.MshritVO;
import com.logistics.md.mapper.VehicleMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDV2Service {
 
	@Autowired 
	private VehicleMapper vehicleMapper ;

	
	public List<MshrhdVO> getMshrhdList(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MshrhdVO> dataList = vehicleMapper.selectMshrhdList(params);
		if (dataList == null) { 
			return Collections.<MshrhdVO>emptyList();
		}
		return dataList;
	}
	
    
    public List<MshritVO> getMshritList(Map<String, Object> params, UserVo userInfo)  {
    	List<MshritVO> dataList = vehicleMapper.selectMshritList(params);
        if(dataList == null) {
        	return Collections.<MshritVO>emptyList();
        }
        return dataList;
    }
    
    
	@Transactional(rollbackFor = Exception.class)
    public void setHeadDelete(Map<String, Object> params, UserVo userInfo) { 
    	List<MshrhdVO> head = convertMshrhdVOList((ArrayList)params.get("data"));
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("headList", head);
    	int deleteMshrhdCnt = vehicleMapper.deleteMshrhd(map);
    	if(deleteMshrhdCnt == 0) {
    		throw new DeleteCheckedException();
    	}
    	
    	List<MshritVO> item = new ArrayList<>();
    	MshritVO vo = new MshritVO();
    	vo.setCompkey(head.get(0).getCompkey());
    	vo.setShtrtky(head.get(0).getShtrtky());
    	item.add(vo);
    	
    	map.put("itemList", item);
    	int deleteMshritCnt = vehicleMapper.deleteMshrit(map);
    	if(deleteMshritCnt == 0) {
    		throw new DeleteCheckedException();
    	}
    }
    
    
	@Transactional(rollbackFor = Exception.class)
    public void setItemDelete(Map<String, Object> params, UserVo userInfo) { 
    	List<MshritVO> list = convertMshritVOList((ArrayList)params.get("data"));
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("itemList", list);
    	int deleteMshritCnt = vehicleMapper.deleteMshrit(map);
    	if(deleteMshritCnt == 0) {
    		throw new DeleteCheckedException();
    	}
    }
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMDV2Save(Map<String, Object> params, UserVo userInfo) { 
		List<MshrhdVO> head = convertMshrhdVOList((ArrayList)params.get("head"));
		Map item = (Map) params.get("item");
		List<MshritVO> updateList = convertMshritVOList((ArrayList)item.get("updateList"));
		List<MshritVO> addList = convertMshritVOList((ArrayList)item.get("addList"));
		List<MshritVO> deleteList = convertMshritVOList((ArrayList)item.get("deleteList"));
		
		String shtrtky = "";
		
		if(head.get(0).getRowkey() == null) {
			shtrtky = vehicleMapper.getShtrtky();
			if(!shtrtky.isEmpty()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("userData", userInfo);
				map.put("shtrtky", shtrtky);
				map.put("list", head);
				int inserCnt = vehicleMapper.insertMshrhd(map);
				if(inserCnt == 0) {
	    			throw new InsertCheckedException();
	    		}
			}
		}else {
			shtrtky = head.get(0).getShtrtky();
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", head);
			int updateCnt = vehicleMapper.updateMshrhd(map);
			if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}
		
		//item List
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("shtrtky", shtrtky);
    		map.put("list", addList);
    		int inserCnt = vehicleMapper.insertMshrit(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
		if(!updateList.isEmpty()) {
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	int updateCnt = vehicleMapper.updateMshrit(map);
        	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
		if(!deleteList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
        	map.put("itemList", deleteList);
        	int deleteCnt = vehicleMapper.deleteMshrit(map);
        	if(deleteCnt == 0) {
        		throw new DeleteCheckedException();
        	}
		}
		
	}
	
    private List<MshrhdVO> convertMshrhdVOList(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MshrhdVO.class)); //변환하고자 하는 class 
    }
    
    private List<MshritVO> convertMshritVOList(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MshritVO.class)); //변환하고자 하는 class 
    }   
}

