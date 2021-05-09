import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BaseServiceService } from '@baseService/base-service.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VnpayService  {

  URL = "http://localhost:8082/api/payVn/get-code?link=http://localhost:4200/&vnp_IpAddr=192.168.0.106&vnp_OrderInfo=Dat%20ve%20online&vnp_OrderType=DatVe";

  constructor(private http: HttpClient) {

  }


  onVNPay(ticket:any):Observable<any>{
    return this.http.post(this.URL, ticket);
  }

}
