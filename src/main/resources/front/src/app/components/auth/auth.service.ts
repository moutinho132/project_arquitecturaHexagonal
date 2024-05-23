import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ILogin } from './interface/login.interface';
import { IUser } from '../user/interface/user.interface';
import { endpoint } from 'src/app/endpoint';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private serviceURL = `${endpoint}`;
  // private serviceURL = `${endpoint}login`;

  constructor(private http: HttpClient) { }

   login(loginData: ILogin): Observable<any> {
     return this.http.post(this.serviceURL, loginData);
   }

  /*login(loginData: { email: string, password: string }): Observable<any> {
    return new Observable(observer => {
      this.http.get<IUser[]>(this.serviceURL + `?email=${loginData.email}`)
        .subscribe(users => {
          if (users.length > 0) {
            const user = users[0];
            const token = this.generateTestToken(user.id ? user.id.toString() : '');
            observer.next({ ...user, token });
            observer.complete();
          } else {
            observer.error('Correo electrónico no encontrado');
          }
        }, error => {
          observer.error('Error en la consulta');
        });
    });
  }*/

  private generateTestToken(userId: string): string {
    return `TEST_TOKEN_${userId ?? ''}`;
  }
}

