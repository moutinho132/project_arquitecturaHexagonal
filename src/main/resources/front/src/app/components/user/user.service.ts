import { endpointUser } from './../../endpoint';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUser } from './interface/user.interface';
import { endpoint } from 'src/app/endpoint';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private serviceURL = `${endpointUser}users`;

  constructor(private http: HttpClient) { }

  createUser(userData: IUser): Observable<IUser> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<IUser>(this.serviceURL, userData, {headers});
  }
}
