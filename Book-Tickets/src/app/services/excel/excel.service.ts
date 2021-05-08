import { BaseServiceService } from '@baseService/base-service.service';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root',
})
export class ExcelService extends BaseServiceService {
  private URL = environment.baseUrl + '/excel/';

  constructor(http: HttpClient) {
    super(http);
  }

  postExcel(routeID: string, time: string) {
    let url = `${this.URL}/xuat-file?id_tuyen_xe=${routeID}&gio=${time}`;
    return this.onPost(url, null);
  }
}
