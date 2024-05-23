import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { ToastrService } from 'ngx-toastr';
import { ILogin } from './interface/login.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  loginData: ILogin = {
    email: '',
    password: ''
  };

  constructor(private authService: AuthService, private toastr: ToastrService,
    public router: Router
    ) {}

  onSubmit() {
    this.authService.login(this.loginData)
      .subscribe(response => {
        this.toastr.success('Inicio de sesión exitoso');
        localStorage.setItem('id', response.id.toString());
        localStorage.setItem('name', response.name);
        localStorage.setItem('surname', response.surname);
        localStorage.setItem('email', response.email);
        localStorage.setItem('token', response.token);
        this.loginData = {
          email: '',
          password: ''
        };
        this.router.navigate(['task']);
      }, error => {
        console.error('Error during login:', error);
        this.toastr.error('Error durante el inicio de sesión');
        this.loginData = {
          email: '',
          password: ''
        };
      });
  }
}
