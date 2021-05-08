import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Bus } from 'src/app/model/bus';
import { environment } from '@env/environment';
import { BaseServiceService } from '@baseService/base-service.service';

@Injectable({
  providedIn: 'root',
})
export class BusServiceService extends BaseServiceService {
  private URL = environment.baseUrl + '/xe';

  constructor(http: HttpClient) {
    super(http);
  }

  getAllBus() {
    let url = `${this.URL}/`;
    return this.onGet(url);
  }

  postCreateBus(bus: Bus) {
    let url = `${this.URL}/create`;
    return this.onPost(url, bus);
  }

  postUpdateBus(busID: Int16Array, bus: Bus) {
    let url = `${this.URL}/update/${busID}`;
    return this.onPost(url, bus);
  }
}
