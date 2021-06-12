import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
@Injectable()
export class AppService {
  baseURL: string = environment.api_URL;

  constructor(private httpService: HttpClient) {}
  // GetData(data) {
  //   return this.httpService.post('business/all', data);
  // }

  sendData(data) {
    return this.httpService.post(this.baseURL + 'subscriber', data);
  }

  GetallData() {
    return this.httpService.get(this.baseURL + 'subscriber/all');
  }
  deleteById(id) {
    console.log(id);
    console.log(this.baseURL + 'subscriber?id=' + id);

    return this.httpService.delete(this.baseURL + 'subscriber?id=' + id);
  }

  editData(data) {
    return this.httpService.patch(this.baseURL + 'subscriber', data);
  }
}
