package com.wm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.mapper.PrinterRepository;
import com.wm.requestDto.PrintReceiptRequestForm;
import com.wm.utils.ContextHolder;
import com.wm.utils.PrinterService;

@RestController
@RequestMapping("/print")
public class PrintController {
	
	@Autowired
    private PrinterService printerService;
	
	@Autowired
	private PrinterRepository printerRepository;
	
	@RequestMapping("/receipt‌")
	public void printReceipt(@RequestBody PrintReceiptRequestForm request) {
		String printerSn = printerRepository.selectPrinterSn(ContextHolder.getTenantId());
		if(!Objects.isNull(printerSn)) {
			printerService.printOrder(request.getContent(), printerSn);
		}
	}
	
	@RequestMapping("/info")
	public Map<String, Object> printInfo() {
		String printerSn = printerRepository.selectPrinterSn(ContextHolder.getTenantId());
		String data = printerService.showPrinterInfo(printerSn);
		Map<String, Object> response = new HashMap<>();
		response.put("data", data);
		return response;
	}
}
