import { BaseServiceService } from '@baseService/base-service.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BusRouter } from 'src/app/model/bus-router';
import { environment } from "@env/environment";

@Injectable({
  providedIn: 'root'
})

export class BusRouterServiceService extends BaseServiceService {
  private URL = environment.baseUrl + "/tuyenxe";

  constructor(http: HttpClient) {
    super(http)
   }

  getRouterById(diemDiId:Int16Array, diemToiId:Int16Array){
    return this.onGet(`${this.URL}/get-tuyen-xe?diem_di_id=${diemDiId}&diem_toi_id=${diemToiId}`);
  }

  getRunTime(id:Int16Array,ngay:string){
    return this.onGet(`${this.URL}/${id}/gio-chay?ngay=${ngay}`);
  }

  getRouterPopular(){
    return this.onGet(this.URL+"/get-tuyen-xe-pho-bien");
  }

  getRouter(){
    return this.onGet(`${this.URL}/tuyenxe/`);
  }

  postReturnRoute(routeId:any){
    let url =`${this.URL}/${routeId}/bat-tuyen-xe`;
    return this.onPost(url,null);
  }

  getAllRoute(){
    return this.onGet(`${this.URL}/`);
  }

  postCreateRoute(route:BusRouter){
    let url = `${this.URL}/create`;
    return this.onPost(url,route);
  }

  postUpdateRoute(routeID: Int16Array, route:BusRouter){
    let url = `${this.URL}/${routeID}/update`;
    return this.onPost(url, route);
  }

  getAllRouteToExport(){
    let url = `${this.URL}/list-tuyen-xe-theo-ve`;
    return this.onPost(url, null);
  }

  postCancelDependency(token:string,data:any){
    let url = `${this.URL}/tao-tuyen-off`;
    return this.onPost(url, data);
  }
}
