package com.wm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.requestDto.PrintReceiptRequestForm;
import com.wm.utils.PrinterService;

@RestController
@RequestMapping("/print")
public class PrintController {
	
	@Autowired
    private PrinterService printerService;
	
	@RequestMapping("/receipt‌")
	public void printReceipt(@RequestBody PrintReceiptRequestForm request) {
		printerService.printOrder(request.getContent());
	}
	
	@RequestMapping("/info")
	public Map<String, Object> printInfo() {
		String data = printerService.showPrinterInfo();
		Map<String, Object> response = new HashMap<>();
		response.put("data", data);
		return response;
	}
}
