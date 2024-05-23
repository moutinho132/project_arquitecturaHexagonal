import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { UserService } from './user.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  userData = {
    name: '',
    surname: '',
    email: '',
    password: ''
  };


    constructor(private userService: UserService, private toastr: ToastrService, private router:Router) {}

  onSubmit() {
    this.userService.createUser(this.userData)
      .subscribe(response => {
        this.toastr.success('Usuario agregado exitosamente');
        this.userData = {
          name: '',
          surname: '',
          email: '',
          password: ''
        };
        this.router.navigate(['login']);
      }, error => {
        console.error('Error creating user:', error);
        this.toastr.success(`${error}`);

      });
  }
}
