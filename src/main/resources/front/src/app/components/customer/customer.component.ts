import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from './customer.service';
import { Customer } from 'src/app/model/customer';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent implements OnInit {
  [x: string]: any;
  customerForm!: FormGroup;
  customer: Customer = new Customer();

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private toastr: ToastrService,public router: Router,
  ) {}

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      dni: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      reference: ['']
    });
  }

  onSubmit(): void {
    if (this.customerForm.valid) {
      const id = new Date().getTime().toString();

      const formData = this.customerForm.value;

      this.customer = {
        id: +id,
        person: {
          id: 1,
          name: formData.name,
          surname: formData.surname,
          dni: formData.dni,
          dateOfBirth: formData.dateOfBirth
        },
        reference: formData.reference
      };


      this.customerService.addCustomer(this.customer).subscribe(
        (response) => {
          this.toastr.success('Cliente agregado exitosamente');
          this.router.navigate(['task']);
          this.customerForm.reset();
        },
        (error) => {
          console.error('Error al agregar cliente:', error);
        }
      );
    } else {
      console.error('El formulario es inválido.');
    }
  }
}
