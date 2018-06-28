package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Dxfs;
import com.rjxx.taxeasy.service.DxfsService;
import com.rjxx.utils.SendMessage;

@Service
public class SaveMessage {
	@Autowired
	DxfsService dxfsService;

	public boolean saveMessage(String gsdm, Integer djh, String sjhm, Map<String, String> dxnr, String mbdm,
			String qmmc) {
		try {
			String returnid = SendMessage.sendSms(qmmc, mbdm, dxnr, sjhm);
			Dxfs dxfs = new Dxfs();
			dxfs.setGsdm(gsdm);
			dxfs.setDjh(djh);
			dxfs.setSjhm(sjhm);
			dxfs.setMbdm(mbdm);
			dxfs.setLrsj(new Date());
			dxfs.setReturnid(returnid);
			dxfsService.save(dxfs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean sendMessage(String gsdm, Integer djh, String sjhm, Map<String, String> dxnr, String mbdm,
							   String qmmc) {
		try {
			String returnid = SendMessage.sendSms(qmmc, mbdm, dxnr, sjhm);
			Dxfs dxfs = new Dxfs();
			dxfs.setGsdm(gsdm);
			dxfs.setDjh(djh);
			dxfs.setSjhm(sjhm);
			dxfs.setMbdm(mbdm);
			dxfs.setLrsj(new Date());
			dxfs.setReturnid(returnid);
			dxfsService.save(dxfs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
