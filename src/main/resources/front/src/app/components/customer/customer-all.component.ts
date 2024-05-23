import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/model/customer';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-customer-all',
  templateUrl: './customer-all.component.html',
  styleUrls: ['./customer-all.component.css']
})
export class CustomerAllComponent implements OnInit {
  customers: Observable<Customer[]> = new Observable<Customer[]>();

  constructor(private customerService: CustomerService) {
    this.customers = this.customerService.getAllCustomers();
  }


  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customers = this.customerService.getAllCustomers();
  }
}
