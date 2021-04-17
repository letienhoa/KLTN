package carbook.daoimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;

import carbook.dao.AbstractDao;
import carbook.dao.VeDao;
import carbook.entity.BaseEntity;
import carbook.entity.GiuongMapDataModel;
import carbook.entity.ThongKeDoanhThuModelData;
import carbook.entity.ThongTinVeDataModel;
import carbook.entity.User;
import carbook.entity.Ve;
import carbook.entity.VeCustomerDataModel;
import carbook.entity.VeExcelDataModel;
import carbook.entity.VeForCustomerByCodeDataModel;
import carbook.entity.VeForCustomerByCodeDataModelFinal;
import carbook.entity.VeOverviewDataModel;
import carbook.entity.VeThongKeModelDate;
import carbook.entity.Xe;
import carbook.request.VeRequest;
import carbook.response.GiuongMapDataModelResponse;
import carbook.response.VeExcelVer1Response;
import carbook.service.UtilsService;

@SuppressWarnings("unchecked")
@Transactional
@Repository("veDao")
public class VeDaoImpl extends AbstractDao<Integer, Ve> implements VeDao {

	@Override
	public List<VeThongKeModelDate> spGetTotalRevenueTuyenXe(Date time, int selectTime) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_total_revenue_tuyen_xe",
				VeThongKeModelDate.class);
		procedureCall.registerParameter("startTime", Date.class, ParameterMode.IN).bindValue(time);
		procedureCall.registerParameter("selectTime", Integer.class, ParameterMode.IN).bindValue(selectTime);

		List<VeThongKeModelDate> list = procedureCall.getResultList();
		return list;
	}

	@Override
	public List<ThongKeDoanhThuModelData> spGetTotalRevenueTiket(Calendar time, int selectTime) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_total_revenue_ticket",
				ThongKeDoanhThuModelData.class);
		procedureCall.registerParameter("startTime", Calendar.class, ParameterMode.IN).bindValue(time);
		procedureCall.registerParameter("selectTime", Integer.class, ParameterMode.IN).bindValue(selectTime);

		List<ThongKeDoanhThuModelData> list = procedureCall.getResultList();
		return list;
	}

	@Override
	public List<VeCustomerDataModel> spGetVeForCustomer(int khachHang) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_ve_xe_for_customer",
				VeCustomerDataModel.class);
		procedureCall.registerParameter("khachHangId", Integer.class, ParameterMode.IN).bindValue(khachHang);

		List<VeCustomerDataModel> list = procedureCall.getResultList();
		return list;
	}

	@Override
	public Long create(VeRequest wrapper, String slot, String code) {
		if(wrapper.getDiemXuong() ==null ) {
			wrapper.setDiemXuong("Nhà");                              
		}
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_insert_ve_giuong_map");
		procedureCall.registerParameter("gioChay", Integer.class, ParameterMode.IN).bindValue(wrapper.getGioChay());
		procedureCall.registerParameter("gioKetThuc", Integer.class, ParameterMode.IN)
				.bindValue(wrapper.getGioKetThuc());
		procedureCall.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(wrapper.getIdTuyenXe());
		procedureCall.registerParameter("sdt", String.class, ParameterMode.IN).bindValue(wrapper.getSdt());
		procedureCall.registerParameter("email", String.class, ParameterMode.IN).bindValue(wrapper.getEmail());
		procedureCall.registerParameter("date", Date.class, ParameterMode.IN)
				.bindValue(UtilsService.changeStringToDate(wrapper.getDate()));
		procedureCall.registerParameter("giaVe", Integer.class, ParameterMode.IN)
				.bindValue(wrapper.getGiaVe().intValue());

		procedureCall.registerParameter("code", String.class, ParameterMode.IN).bindValue(code);
		procedureCall.registerParameter("slot", String.class, ParameterMode.IN).bindValue(slot);
		procedureCall.registerParameter("paypalId", String.class, ParameterMode.IN)
		.bindValue(wrapper.getPaypalId());
		procedureCall.registerParameter("vnpayId", String.class, ParameterMode.IN)
		.bindValue(wrapper.getVnpayId());
		procedureCall.registerParameter("diemXuong", String.class, ParameterMode.IN)
		.bindValue(wrapper.getDiemXuong());
		procedureCall.registerParameter("message", Long.class, ParameterMode.OUT);
		procedureCall.execute();
		return (Long) procedureCall.getOutputParameterValue("message");
	}

	@Override
	public Long spUpdateVe(int id, String sdt, String email) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_update_ve");
		procedureCall.registerParameter("idUser", Integer.class, ParameterMode.IN).bindValue(id);
		procedureCall.registerParameter("newSdt", String.class, ParameterMode.IN).bindValue(sdt);
		procedureCall.registerParameter("newEmail", String.class, ParameterMode.IN).bindValue(email);
		procedureCall.registerParameter("message", Long.class, ParameterMode.OUT);
		procedureCall.execute();
		return (Long) procedureCall.getOutputParameterValue("message");

	}

	@Override
	public List<VeExcelDataModel> spXuatFileExcel(int id,int hour) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_xuat_file_excel",
				VeExcelDataModel.class);
		procedureCall.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(id);
		procedureCall.registerParameter("hour", Integer.class, ParameterMode.IN).bindValue(hour);

		List<VeExcelDataModel> veEDM = procedureCall.getResultList();
		return veEDM;
	}

	@Override
	public Long spUpdateVeXeTrangThai(int idTuyenXe) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_update_ve_xe_trang_thai",
				VeExcelDataModel.class);
		procedureCall.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(idTuyenXe);
		procedureCall.registerParameter("message", Long.class, ParameterMode.OUT);

		procedureCall.execute();
		return (Long) procedureCall.getOutputParameterValue("message");
	}

	
	  @Override 
	  public List<VeExcelVer1Response> spGetThongTinVe(int idTuyenXe,int hour) { 
		  ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_thong_tin_xuat_ve",ThongTinVeDataModel.class); 
		  procedureCall.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(idTuyenXe);
			procedureCall.registerParameter("hour", Integer.class, ParameterMode.IN).bindValue(hour);
		  List<ThongTinVeDataModel> list1 =procedureCall.getResultList();

		  ProcedureCall procedureCall1 = this.getSession().createStoredProcedureCall("sp_get_thong_tin_giuong",GiuongMapDataModel.class); 
		  procedureCall1.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(idTuyenXe);
		  procedureCall1.registerParameter("hour", Integer.class, ParameterMode.IN).bindValue(hour);
		  List<GiuongMapDataModel> list2 = procedureCall1.getResultList();
		  
		  List<VeExcelVer1Response> listData = new VeExcelVer1Response().mapToList(list1);
	  
		  for(int i=0;i<listData.size();i++) { 
			  for(int j=0;j<list2.size();j++) {
				  if(list1.get(i).getId()==list2.get(j).getIdVe()) {
					  String g="";
					  if(list2.get(j).getStt()<=22) {
						  if(list2.get(j).getStt()<10){
							  g= "A0"+ String.valueOf(list2.get(j).getStt());
						  }
						  else {
							  g= "A"+ String.valueOf(list2.get(j).getStt());
						  }
					  }else {
						  if(list2.get(j).getStt()<32) {
							  g= "B0"+ String.valueOf(list2.get(j).getStt()-22);
						  }else {
							  g= "B"+ String.valueOf(list2.get(j).getStt()-22);
						  }
					  }
					  GiuongMapDataModelResponse giuongtt =new GiuongMapDataModelResponse();
					  giuongtt.setIdVe(list2.get(j).getIdVe());
					  giuongtt.setId(list2.get(j).getId());
					  giuongtt.setStt(g);
					  giuongtt.setNoiXuong(list2.get(j).getNoiXuong());
					  giuongtt.setDiaChi(list2.get(j).getDiaChi());
					  giuongtt.setTen(list2.get(j).getTen());
					  listData.get(i).getSlotss().add(giuongtt); 
				  	} 
			  } 
		  } return listData; 
	}

	@Override
	public List<VeOverviewDataModel> spGetTuyenXeTrongNgay() {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_tuyen_xe_trong_ngay",VeOverviewDataModel.class); 
		  
		  List<VeOverviewDataModel> list1 =procedureCall.getResultList();
		  return list1;
	}

	@Override
	public VeForCustomerByCodeDataModelFinal spGetVeForCustomerByCode(String code) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_ve_for_customer_by_code",VeForCustomerByCodeDataModel.class); 
		procedureCall.registerParameter("code", String.class, ParameterMode.IN).bindValue(code);
		VeForCustomerByCodeDataModel veCustomer = new VeForCustomerByCodeDataModel();
		
		try {
			veCustomer = (VeForCustomerByCodeDataModel) procedureCall.getSingleResult();
		}	catch (Exception e) {
			veCustomer =null;
		}
		
		VeForCustomerByCodeDataModelFinal listData =new VeForCustomerByCodeDataModelFinal();
		if(veCustomer!=null) {
			listData =new VeForCustomerByCodeDataModelFinal(veCustomer);
			ProcedureCall procedureCall1 = this.getSession().createStoredProcedureCall("sp_get_thong_tin_giuong_detail_ve",GiuongMapDataModel.class); 
			procedureCall1.registerParameter("idVe", Integer.class, ParameterMode.IN).bindValue(veCustomer.getId());
			procedureCall1.registerParameter("hour", Integer.class, ParameterMode.IN).bindValue(veCustomer.getGioChay());
			List<GiuongMapDataModel> list2= new ArrayList<GiuongMapDataModel>();
			
			try {
				list2 = procedureCall1.getResultList();
			}catch (Exception e) {
				list2=null;
			}		
			
			if(list2!=null) {
				for(GiuongMapDataModel x: list2) {
					listData.getSlots().add(String.valueOf(x.getStt()));
				}
					
				return listData;
			} else return null;
		} else return null;

		

	}

	@Override
	public String spGetNameTuyenXe(int idTuyenXe) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_name_tuyen_xe");
		procedureCall.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(idTuyenXe);
		procedureCall.registerParameter("tuyenXe", String.class, ParameterMode.OUT);

		String tuyenXe = (String) procedureCall.getOutputParameterValue("tuyenXe");
		return tuyenXe;
	}

	@Override
	public Ve findOne(int id) {
		CriteriaQuery<Ve> criteria = this.getBuilder().createQuery(Ve.class);
		Root<Ve> root = criteria.from(Ve.class);
		criteria.select(root).where(this.getBuilder().equal(root.get("id"), id));
		Ve ve=new Ve();
		try {
			ve=this.getSession().createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			ve=null;
		}
		return ve;
	}
	 
	@Override
	public void update(BaseEntity ve) {
		this.getSession().update(ve);
	}

	@Override
	public void spDeleteGiuong(int id) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_delecte_giuong");
		procedureCall.registerParameter("idVe", Integer.class, ParameterMode.IN).bindValue(id);
		
		procedureCall.execute();
	}

	@Override
	public Integer spGetCountGiuong(int idVe) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_get_count_giuong");
		procedureCall.registerParameter("idVe", Integer.class, ParameterMode.IN).bindValue(idVe);
		procedureCall.registerParameter("countSlot", Integer.class, ParameterMode.OUT);
		if(procedureCall.getOutputParameterValue("countSlot")!=null)
		{
			return (Integer) procedureCall.getOutputParameterValue("countSlot");
		}else return 0;
	}

	@Override
	public List<Ve> getVeToCancel(Integer idTuyenXe, Integer gio, String time,Integer type) {
		ProcedureCall procedureCall = this.getSession().createStoredProcedureCall("sp_cancel_tuyen_xe",Ve.class);
		procedureCall.registerParameter("idTuyenXe", Integer.class, ParameterMode.IN).bindValue(idTuyenXe);
		procedureCall.registerParameter("gio", Integer.class, ParameterMode.IN).bindValue(gio);
		procedureCall.registerParameter("ngay", Date.class, ParameterMode.IN).bindValue(UtilsService.changeStringToDate(time));
		procedureCall.registerParameter("type", Integer.class, ParameterMode.IN).bindValue(type);
		List<Ve> list =new ArrayList<Ve>();
		try {
			list =(List<Ve>)procedureCall.getResultList();
		} catch (Exception e) {
			list =null;
		}
		
		return list;
	}
	
	 

}
