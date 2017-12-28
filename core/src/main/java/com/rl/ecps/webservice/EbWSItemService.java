package com.rl.ecps.webservice;

import javax.jws.WebService;

@WebService
public interface EbWSItemService {
	
	public String publishItem(Long itemId, String password) throws Exception;

}
