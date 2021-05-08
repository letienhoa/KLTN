import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BaseServiceService {
  token: any = null;
  httpOption!: any;

  constructor(private http: HttpClient) {}

  getInstance(token: string) {
    this.httpOption = this.createToken(token);
  }

  createToken(token: string) {
    let httpOption = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        Authorization: token,
      }),
    };
    return httpOption;
  }

  onGet(url: string): Observable<any> {
    this.token = JSON.parse(localStorage.getItem('LogInSuccess')!);

    if (this.token == null) return this.http.get(url);

    this.getInstance(this.token.Token);
    return this.http.get(url, this.httpOption);
  }

  onPost(url: string, data: any): Observable<any> {
    this.token = JSON.parse(localStorage.getItem('LogInSuccess')!);

    if (this.token == null) return this.http.post(url, data);

    this.getInstance(this.token.Token);
    return this.http.post(url, data, this.httpOption);
  }
}
