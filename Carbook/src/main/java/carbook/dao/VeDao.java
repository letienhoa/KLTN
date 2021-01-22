package carbook.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import carbook.entity.BaseEntity;
import carbook.entity.ThongKeDoanhThuModelData;
import carbook.entity.Ve;
import carbook.entity.VeCustomerDataModel;
import carbook.entity.VeExcelDataModel;

import carbook.entity.VeForCustomerByCodeDataModelFinal;
import carbook.entity.VeOverviewDataModel;
import carbook.entity.VeThongKeModelDate;
import carbook.request.VeRequest;
import carbook.response.VeExcelVer1Response;

public interface VeDao {
	
	Long spUpdateVe(int id,String sdt,String email);
	
	Long create(VeRequest wrapper, String slot,String code);
	
	List<VeThongKeModelDate> spGetTotalRevenueTuyenXe(Date time,int selectTime);
	
	List<VeCustomerDataModel> spGetVeForCustomer(int khachHang);
	
	List<ThongKeDoanhThuModelData> spGetTotalRevenueTiket(Calendar time,int selectTime);
	
	List<VeExcelDataModel> spXuatFileExcel(int id,int hour);
	
	Long spUpdateVeXeTrangThai(int idTuyenXe);
	
	List<VeExcelVer1Response> spGetThongTinVe(int idTuyenXe,int hour);
	
	List<VeOverviewDataModel> spGetTuyenXeTrongNgay();
	
	VeForCustomerByCodeDataModelFinal spGetVeForCustomerByCode(String code);
	
	String spGetNameTuyenXe(int idTuyenXe);
	
	Ve findOne(int id);
	
	void update(BaseEntity ve);
	
	void spDeleteGiuong(int id);
	
	Integer spGetCountGiuong(int idVe);
	
	List<Ve> getVeToCancel(Integer idTuyenXe,Integer gio,String time,Integer type);
}
