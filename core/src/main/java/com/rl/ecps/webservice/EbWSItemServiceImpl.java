package com.rl.ecps.webservice;

import java.util.HashMap;
import java.util.Map;

import com.rl.ecps.utils.ResourcesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.utils.FMutil;

@Service
public class EbWSItemServiceImpl implements EbWSItemService {

	@Autowired
	private EbItemDao itemDao;
	
	public String publishItem(Long itemId, String password) throws Exception {
		String pass = ResourcesUtils.readProp("ws_pass");
		if(StringUtils.equals(password, pass)){
			EbItem item  = itemDao.selectItemDetail(itemId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("item", item);
			FMutil fm = new FMutil();
			fm.ouputFile("productDetail.ftl", item.getItemId()+".html", map);
			return "success";
		}else{
			return "pass_error";
		}
	}

}
