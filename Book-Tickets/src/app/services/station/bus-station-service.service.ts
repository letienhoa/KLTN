import { BaseServiceService } from '@baseService/base-service.service';
import { BusStation } from 'src/app/model/bus-station';
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root',
})
export class BusStationServiceService extends BaseServiceService {
  private URL = environment.baseUrl + '/ben';

  constructor(http: HttpClient) {
    super(http);
  }

  getAllBusStation() {
    return this.onGet(`${this.URL}`);
  }

  getDestinationStationsById(id: Int16Array) {
    return this.onGet(`${this.URL}/get-list-ben-toi?ben_di_id=${id}`);
  }

  getAllStation() {
    return this.onGet(`${this.URL}`);
  }

  getBusStationDetailById(id: Int16Array){
    return this.onGet(`${this.URL}/get-detail/${id}`);
  }

  postCreateStation(busStation: BusStation) {
    let url = `${this.URL}/create`;
    return this.onPost(url, busStation);
  }

  postUpdateStation(busStationID: Int16Array, busStation: BusStation) {
    let url = `${this.URL}/${busStationID}/update`;
    return this.onPost(url, busStation);
  }
}
