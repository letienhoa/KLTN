import { environment } from '@env/environment';
import { Ticket, TicketTwoWay } from './../../model/ticket';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseServiceService } from '@baseService/base-service.service';

@Injectable({
  providedIn: 'root',
})
export class TicketServiceService extends BaseServiceService {
  private URL = environment.baseUrl + '/ve';

  constructor(http: HttpClient) {
    super(http);
  }

  postCreateTicketOneWay(ticket: any) {
    return this.onPost(`${this.URL}/create`, ticket);
  }

  postCreateTicketTwoWay(ticket: any) {
    return this.onPost(`${this.URL}/create2`, ticket);
  }

  getTicketByCode(code: string) {
    return this.onGet(`${this.URL}/get-ve-by-code?code=${code}`);
  }

  getTicketHistory(customerId: Int16Array) {
    let url = `${this.URL}/thong-ke-theo-khach-hang?khach_hang_id=${customerId}`;
    return this.onGet(url);
  }

  getStatisticsByDateRevenue(date: any, selecter: any) {
    return this.onGet(
      this.URL + '/thong-ke-doanh-thu?time=' + date + '&selecter=' + selecter
    );
  }

  getStatisticsByMonthRevenue(date: any, selecter: any) {
    return this.onGet(
      this.URL + '/thong-ke-doanh-thu?time=' + date + '&selecter=' + selecter
    );
  }

  getStatisticByDateRoute(date: any, selecter: any) {
    return this.onGet(
      this.URL + '/thong-ke-theo-tuyen?time=' + date + '&selecter=' + selecter
    );
  }

  getStatisticsByMonthRoute(date: any, selecter: any) {
    return this.onGet(
      this.URL + '/thong-ke-theo-tuyen?time=' + date + '&selecter=' + selecter
    );
  }
}
