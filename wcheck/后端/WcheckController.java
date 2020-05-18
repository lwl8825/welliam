package com.temp.ntoe.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.temp.admin.dto.wcheckDTO;
import com.temp.common.model.ResponseData;
import com.temp.common.model.entity.wcheck;
import com.temp.common.service.wcheckService;
import com.temp.common.util.FormatUtil;

import io.swagger.annotations.ApiOperation;


@RestController
@Component
@RequestMapping("/wcheck")//定向前台请求路径
public class WcheckController {
	@Autowired
	private wcheckService service;
	@ApiOperation(value = "考勤列表", notes = "根据参数查询考勤")
    @RequestMapping(value = "/cx", method = { RequestMethod.POST })//定向请求参数
    public ResponseData cx(@RequestBody wcheckDTO container) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("第一步contorller"+container.getKqzt());
        map.put("cx", service.getPageList(container));
        map.put("total", service.getTotal(container));
        return FormatUtil.success(map);
    }
	
	@ApiOperation(value = "新增/修改考勤列表", notes = "根据ID确定是否存在考勤信息，进行新增或修改")
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public ResponseData save(@RequestBody wcheck note) {
        return FormatUtil.success(service.save(note));
    }
    
	@RequestMapping(value = "/import",method = RequestMethod.POST)
    public ResponseData exImport(@RequestParam(value = "filename")MultipartFile file, HttpSession session) throws Exception {
		String fileName = file.getOriginalFilename();
		return FormatUtil.success(service.batchImport(fileName, file));
    }
	
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public ResponseData exImport1(@RequestBody wcheckDTO container) {
    	Map<String, Object> map = new HashMap<>();
        System.out.println("导出报错Notecontorller"+container.getId());
        map.put("cx", service.getPageList(container));
        map.put("total", service.getTotal(container));
        return FormatUtil.success(map);
    }
}
