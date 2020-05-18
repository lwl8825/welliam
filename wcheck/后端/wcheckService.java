package com.temp.common.service;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.temp.admin.dto.wcheckDTO;
import com.temp.common.mapper.wcheckMapper;
import com.temp.common.model.entity.wcheck;
import com.temp.ntoe.controller.NoteController;
import com.temp.ntoe.controller.WcheckController;

import net.sf.json.JSONArray;


@Service
public class wcheckService {
	private static String aa="日期时间";					
	private static String bb="员工编号";
	private static String cc="员工姓名";
	private static String dd="上班时间";
	private static String ee="下班时间";
	private static String ff="出勤说明";
	private static String gg="考勤状态";
	wcheck note=new wcheck();
	public void wcheckService() {
	}
	@Autowired
    private wcheckMapper mapper;

    @Autowired
    private Mapper mapperTrans;
    public int testaa,testbb;
    public List<wcheck> getPageList(wcheckDTO container) {
    	wcheck noteSearch = mapperTrans.map(container, wcheck.class);
    	//System.out.println("获取note====>"+noteSearch);
    	System.out.println("第二步获取service中的zt===>"+noteSearch.getKqzt());
        Map<String, Object> map = new HashMap<>();
        map.put("note", noteSearch);
        System.out.println("获取page.zt===>"+container.getKqzt());
        map.put("page", container.getCurrentPage());
        map.put("size", container.getLimit());
        System.out.println("第三步步报错NoteSerVice"+map);
        System.out.println("map:::::::::::"+map.toString());
        
        //List<wcheck> noteList = mapper.queryPageList(map);
       // List<wcheck> noteList = 
        return (List<wcheck>) mapper.queryPageList1(noteSearch);
//        List<wcheckDTO> list = new ArrayList<>();
//        for (wcheck note : noteList) {
//        	System.out.println("第三步报错NoteSerVice"+note.getKqzt());
//        	wcheckDTO dto = mapperTrans.map(note, wcheckDTO.class);
//            list.add(dto);
//        }
//        System.out.println("第三步报错NoteSerVice"+list);
       // return noteList;
    }
    
    public Integer getTotal(wcheckDTO container) {
    	getcxa(container);
    	wcheck noteSearch = mapperTrans.map(container, wcheck.class);
        return mapper.queryCount(noteSearch);
    }
    public void getcxa(wcheckDTO container) {
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date())+"执行一次进入测试");
    	//1全部
        wcheck noteSearch = mapperTrans.map(container, wcheck.class);
    	testaa=mapper.queryCount(noteSearch);
    	System.out.println("结果:a"+testaa);
    	//2今天
    	container.setTime(sdf.format(new Date()));
    	wcheck noteSearch1 = mapperTrans.map(container, wcheck.class);
    	testbb=mapper.queryCount(noteSearch1);
    	System.out.println("结果:b"+testbb);
    }

    public Integer save(wcheck note2) {
    	
        if (note2.getId() != null) {
            note = mapper.queryOne(note2.getId());
            if (note == null) {
               note = new wcheck();
            }
        } else {
        	note = new wcheck();
        }
        note.setTime(note2.getTime());
        note.setUsercode(note2.getUsercode());
        note.setUsername(note2.getUsername());
        note.setStime(note2.getStime());
        note.setEtime(note2.getEtime());
        note.setOutdesc(note2.getOutdesc());
        note.setKqzt(note2.getKqzt());
        if (note2.getId() != null) {
        	System.out.println("有ID修改时候getid==="+note.getId()+"|||getUsername==="+note.getUsername()+"||"+note.getUsercode()+"||"+note.getStime()+"||"+note.getEtime()+"||"+note.getOutdesc()+"||"+note.getTime()+"||"+note.getKqzt());
            return mapper.update(note);
        } else {
        	System.out.println("新建时候getid==="+note.getId()+
        			"|||getUsername==="+note.getUsername()+
        			"||"+note.getUsercode()+
        			"||"+note.getStime()+
        			"||"+note.getEtime()+
        			"||"+note.getOutdesc()+
        			"||"+note.getKqzt()+
        			"||"+note.getTime());
        	return mapper.add(note);
        }
    }
    public Integer delete(Integer id) {
        return mapper.delete(id);
    }


    //@Transactional(readOnly = false,rollbackFor = Exception.class)
    @SuppressWarnings({ "rawtypes", "unchecked", "null" })
	public JSONArray batchImport(String fileName, MultipartFile file) throws Exception {
    	JSONArray jal=new JSONArray();
    	HashMap Map =new HashMap();
    	boolean notNull = false;
        String a1 ="";
        String a2 ="";
        String a3 ="";
        String a4 ="";
        String a5 ="";
        String a6 ="";
        String a7 ="";
        WcheckController aa = new WcheckController();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        Row row1 = sheet.getRow(0);	
        a1 = row1.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
    	a2 = row1.getCell(1).getStringCellValue();//得到每一行第一个单元格的值
    	a3 = row1.getCell(2).getStringCellValue();//得到每一行第一个单元格的值
    	a4 = row1.getCell(3).getStringCellValue();//得到每一行第一个单元格的值
    	a5 = row1.getCell(4).getStringCellValue();//得到每一行第一个单元格的值
    	a6 = row1.getCell(5).getStringCellValue();//得到每一行第一个单元格的值
    	a7 = row1.getCell(6).getStringCellValue();//得到每一行第一个单元格的值
    	
    	boolean test=isRigthCoulm(a1,a2,a3,a4,a5,a6,a7);
    	if(test) {
    		System.out.println("验证通过,列名匹配!!"+test);
    		Map.put("STATE", "success");
    		Map.put("MSG", "导入成功!");
    		jal.add(Map);
    		Map.clear();
    		for (int r = 1; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
                Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
                if (row == null){
                    continue;
                }
                
                if(r==0) {
                	
                }
                //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
                
                //System.out.println("1:"+row.getCell(0).getDateCellValue());
                //System.out.println("2:"+row.getCell(0).getStringCellValue());
//                System.out.println("1:"+row.getCell(0).getCellType());
//                System.out.println("2:"+row.getCell(0).getCellStyle().getDataFormatString());
//                if("yyyy/mm;@".equals(row.getCell(0).getCellStyle().getDataFormatString()) || "m/d/yy".equals(row.getCell(0).getCellStyle().getDataFormatString())
//                        || "yy/m/d".equals(row.getCell(0).getCellStyle().getDataFormatString()) || "mm/dd/yy".equals(row.getCell(0).getCellStyle().getDataFormatString())
//                        || "dd-mmm-yy".equals(row.getCell(0).getCellStyle().getDataFormatString())|| "yyyy/m/d".equals(row.getCell(0).getCellStyle().getDataFormatString())){
//                    System.out.println("3"+new SimpleDateFormat("yyyy/MM/dd").format(row.getCell(0).getDateCellValue()));
//                }
                

//                if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
//                	System.out.println("5:"+row.getCell(0).getCellType());
//                    throw new Exception("导入失败(第"+(r+1)+"行,请设为文本格式)");
//                }
                if(row.getCell(0).getStringCellValue()=="") {
                	 System.out.println("开始塞值"+row.getCell(0).getDateCellValue());
                	 throw new Exception("导入失败(第"+(r+1)+"行,请设为文本格式)");
                }
                String a = row.getCell(0).getStringCellValue().toString();//得到每一行第一个单元格的值
                System.out.println("结束塞值");
     
                if(a == null || a.isEmpty()){//判断是否为空
                    throw new Exception("导入失败(第"+(r+1)+"行,学院未填写)");
                }
     
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
     
                String b = row.getCell(1).getStringCellValue();
     
                if(b==null || b.isEmpty()){
                    throw new Exception("导入失败(第"+(r+1)+"行,班级未填写)");
                }
     
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
     
                String c = row.getCell(2).getStringCellValue();
     
                if(c==null || c.isEmpty()){
                    throw new Exception("导入失败(第"+(r+1)+"行,学院未填写)");
                }
     
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
     
                String d = row.getCell(3).getStringCellValue();
     
                if(d==null || d.isEmpty()){
                	d=null;
                    //throw new Exception("导入失败(第"+(r+1)+"行,学号未填写)");
                }
     
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
     
                String e = row.getCell(4).getStringCellValue();
     
                if(e==null || e.isEmpty()){
                	e=null;
                    //throw new Exception("导入失败(第"+(r+1)+"行,姓名未填写)");
                }
     
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
     
                String f = row.getCell(5).getStringCellValue();
     
                if(f==null || f.isEmpty()){
                    throw new Exception("导入失败(第"+(r+1)+"行,性别未填写)");
                }
                
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
                
                String g = row.getCell(6).getStringCellValue();
     
                if(g==null || g.isEmpty()){
                    throw new Exception("导入失败(第"+(r+1)+"行,性别未填写)");
                }
                System.out.println("第"+r+"行:"+a+","+b+","+c+","+d+","+e+","+f+","+g+".....");
                //完整的循环一次 就组成了一个对象
                note.setId(null);
                note.setTime(a);
                note.setUsercode(b);
                note.setUsername(c);
                note.setStime(d);
                note.setEtime(e);
                note.setOutdesc(f);
                note.setKqzt(g);
                //jal.add(note);
                Map.put(a1, a);
                Map.put(a2, b);
                Map.put(a3, c);
                Map.put(a4, d);
                Map.put(a5, e);
                Map.put(a6, f);
                Map.put(a7, g);
                jal.add(Map);
                save(note);
            }
    	}else {
    		Map.put("STATE", "falie");
    		Map.put("MSG", "导入失败,列名不匹配!!");
    		jal.add(Map);
    	}
        System.out.println("{结束啦!!!!}");
        return jal;
    }
    
    public static boolean isRigthCoulm(String a1,String b1,String c1,String d1,String e1,String f1,String g1)  {
        if(aa.equals(a1)&&bb.equals(b1)&&cc.equals(c1)&&dd.equals(d1)&&ee.equals(e1)&&ff.equals(f1)&&gg.equals(g1)) {
        	return true;
        }
        else {
        	System.out.println(aa+"||"+a1);
        	System.out.println(bb+"||"+b1);
        	System.out.println(cc+"||"+c1);
        	System.out.println(dd+"||"+d1);
        	System.out.println(ee+"||"+e1);
        	System.out.println(ff+"||"+f1);
        	System.out.println(gg+"||"+g1);
        	return false;
        }
    }
    
}
