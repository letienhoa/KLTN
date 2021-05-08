import { HttpClient } from '@angular/common/http';
import { BaseServiceService } from '@baseService/base-service.service';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminServiceService extends BaseServiceService {
  private URL = environment.baseUrl + '/admin';

  constructor(http: HttpClient) {
    super(http);
  }

  postCreateAccount(account: Account) {
    let url = `${this.URL}/create`;
    return this.onPost(url, account);
  }
}
