package carbook.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carbook.dao.VeDao;
import carbook.entity.VeExcelDataModel;
import carbook.response.BaseResponse;
import carbook.response.VeExcelResponse;
import carbook.response.VeExcelVer1Response;
import carbook.response.VeTraVeChoDinhResponse;
import carbook.service.UtilsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/excel")
public class ExcelOurputController {
	
	@Autowired
	private VeDao veDao;
	
	@RequestMapping(value ="/xuat-file", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> test(
			@RequestParam("id_tuyen_xe")int idTuyenXe,
			@RequestParam("gio")int hour){
		BaseResponse response = new BaseResponse();
		
		//lấy thông tin list vé 
		List<VeExcelVer1Response> listVe= veDao.spGetThongTinVe(idTuyenXe,hour);


	    List<VeExcelDataModel> list = veDao.spXuatFileExcel(idTuyenXe,hour);
	    
	    //List danh sách thogno tin cơ bản của tuyến xe
        List<VeExcelResponse> listData = new VeExcelResponse().mapToList(list);
	    
	    VeTraVeChoDinhResponse listDinh= new VeTraVeChoDinhResponse();
	    
	    listDinh.setTuyenXe(listData.get(0).getTuyenXe());
	    listDinh.setTongVe(listData.size());
	    listDinh.setDate(listData.get(0).getDate());
	    listDinh.setGioChay(String.valueOf(listData.get(0).getGioChay())+"h");
	    listDinh.setListVe(listVe);
	    

	    Long messageSql= veDao.spUpdateVeXeTrangThai(idTuyenXe);
	    if(messageSql==1) {
	    	response.setMessageError("Lỗi cập nhập trạng thái của vé xe !!!");
	    }
	    response.setData(listDinh);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	
	}
	
	
	
	
	
	@RequestMapping(value ="/xuat-file1", method = RequestMethod.POST )
	public ResponseEntity<BaseResponse> test1(
			@RequestParam("id_tuyen_xe")int idTuyenXe,
			@RequestParam("gio")int hour){
		BaseResponse response = new BaseResponse();
		
		//lấy thông tin list vé 
		List<VeExcelVer1Response> listVe= veDao.spGetThongTinVe(idTuyenXe,hour);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Customer_Info");
	    int rowNum = 0;
	    Row firstRow = sheet.createRow(rowNum++);
	    Cell firstCell = (Cell) firstRow.createCell(0);
	    firstCell.setCellValue("Danh Sách vé xe");
	    List<VeExcelDataModel> list = veDao.spXuatFileExcel(idTuyenXe,hour);
	    
	    //List danh sách thogno tin cơ bản của tuyến xe
	    List<VeExcelResponse> listData = new VeExcelResponse().mapToList(list);
	    
	   
	    Cell cell;
        Row row;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Tuyến");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Tổng vé");
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Ngày");
        cell = row.createCell(3 ,CellType.STRING);
        cell.setCellValue("Giờ chạy");
        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(listData.get(0).getTuyenXe());
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(listData.size());
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue(listData.get(0).getDate());
        cell = row.createCell(3 ,CellType.STRING);
        cell.setCellValue(String.valueOf(listData.get(0).getGioChay())+"h");
        rowNum++;
        rowNum++;
        row = sheet.createRow(rowNum);
	    

        
        
        // danh sách
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Tên Khách Hàng");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Số điện thoại");
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Mã vé");
        cell = row.createCell(3 ,CellType.STRING);
        cell.setCellValue("Vị trí giường");


        
	    
	    for (VeExcelVer1Response ve : listVe) {
	    	rowNum++;
	         row = sheet.createRow(rowNum);
	        Cell cell1 = row.createCell(0);
	        cell1.setCellValue(ve.getTenKh());
	        
	        Cell cell2 = row.createCell(1);
	        cell2.setCellValue(ve.getSdt());
	        
	        Cell cell3 = row.createCell(2);
	        cell3.setCellValue(ve.getCode());
	        
	        Cell cell4 = row.createCell(3);
	        cell4.setCellValue(UtilsService.convertListObjectToJsonArrayt(ve.getSlotss()));
	        
	      }
	    
	    try {
	    	 File file = new File("D:/employee.xls");
	         file.getParentFile().mkdirs();
	    	
	        FileOutputStream outputStream = new FileOutputStream(file);
	        workbook.write(outputStream);
	        workbook.close();
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }

	    Long messageSql= veDao.spUpdateVeXeTrangThai(idTuyenXe);
	    if(messageSql==1) {
	    	response.setMessageError("Lỗi cập nhập trạng thái của vé xe !!!");
	    }
	    response.setData(listVe);
		return new ResponseEntity<BaseResponse>(response,HttpStatus.OK);
	
	}
	
	
}
