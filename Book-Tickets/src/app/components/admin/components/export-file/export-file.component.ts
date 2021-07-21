import { onCompareDate } from 'src/app/shared/compare-date/compare-date';
import { seat } from './../../../../model/seats';
import { Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { ExcelJson } from '@model/excel-json';
import { RouterForExport } from '@model/router';
import { ExportExcelService } from 'src/app/services/export-excel/export-excel.service';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { ExcelService } from 'src/app/services/excel/excel.service';
import { ExportCustomer } from '@model/customer';

@Component({
  selector: 'app-export-file',
  templateUrl: './export-file.component.html',
  styleUrls: ['./export-file.component.css'],
})
export class ExportFileComponent implements OnInit {
  isDetail = false; 
  isExport = true;

  busRouterForExport: RouterForExport[] = [] as RouterForExport[];
  busRouterDetail: RouterForExport = {} as RouterForExport;
  date: any;
  today:string = "";
  type!:number;
  isLoad = true;
  isLoadDetail= false;

  customers: ExportCustomer[] = [] as ExportCustomer[];

  constructor(
    private busRouterService: BusRouterServiceService,
    private exportExcelService: ExportExcelService,
    private toartService: ToastrService,
    private excelService: ExcelService
  ) {}

  ngOnInit(): void {
    let day = new Date();
    this.date =
      day.getFullYear() +
      '-' +
      ('0' + (day.getMonth() + 1)).slice(-2) +
      '-' +
      ('0' + day.getDate()).slice(-2);
    this.today = this.date;
    this.type = 1;
    this.onGetBusesRouterToExport(this.date);
  }

  onChangeDate($event: any) {
    if($event.value!=this.today){
      this.type = 2;
    }
    else{
      this.type = 1;
    }

    this.isExport = !onCompareDate($event.value, this.today);

    this.isDetail = false;
    this.date = $event.value;
    this.onGetBusesRouterToExport(this.date);
  }

  onGetBusesRouterToExport(date: string) {
    this.isLoad = true;
    let time = ('0' + new Date(date).getDate()).slice(-2) + '/' + ('0' + (new Date(date).getMonth() + 1)).slice(-2) + '/' + new Date(date).getFullYear();
    this.busRouterService.getAllRouteToExport(time).subscribe((data) => {
      this.busRouterForExport = data.data;
      if (this.busRouterForExport.length == 0) {
        this.toartService.info('Không có tuyến nào trong ngày ' + time);
      } else {
        this.busRouterForExport.forEach(
          (b) => (b.tuyen_xe_name = b.tuyen_xe_name.replace('->',' ⇒ '))
        );
      }
      this.isLoad = false;
    });
  }

  onDetail(bus:RouterForExport){
    this.isDetail = true;
    this.busRouterDetail = bus;
    let dateD = this.date.split("-");
    this.isLoadDetail = true;
    this.excelService.postExcel(bus.id_tuyen_xe, bus.gio_chay, this.type, dateD[2]+"/"+dateD[1]+"/"+dateD[0])
    .subscribe(
      data => {
        this.customers = data.data.danh_sach_ve;
        this.isLoadDetail = false;
      }
    )
  }

  onExportPDFFile() {
    let divToPrint = document.getElementById('router-infor-detail');
    let newWin = window.open('');
    newWin!.document.write(divToPrint!.outerHTML);
    newWin!.print();
    newWin!.close();
  }

  onExportExcelFile() {
    const edata: Array<ExcelJson> = [];

    const udt: ExcelJson = {
      data: [
        {
          B: 'STT',
          C: 'Tên Khách',
          D: 'Số Điện Thoại',
          E: 'Số lượng vé',
          F: 'Ghế',
        }, // table header  
      ],
      skipHeader: true,
    };


    this.customers.forEach((customer, index) => {
      let seat ="";
      for(let vt of customer.vi_tri_giuong){
        seat += vt.stt + ", "
      }
      udt.data.push({
        B: index,
        C: customer.ten_khach_hang,
        D: customer.so_dien_thoai,
        E: customer.vi_tri_giuong.length + ' vé',
        F: seat,
      });
    });
    edata.push(udt);
    this.exportExcelService.exportJsonToExcel(edata, 'TP.HCM ⇒ PY');
  }
}
