import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Customer } from 'src/app/model/customer';
import { Task } from 'src/app/model/task';
import { TaskService } from 'src/app/service/task.service';
import { CustomerService } from '../customer/customer.service';
import { ToastrService, IndividualConfig  } from 'ngx-toastr';


@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  @ViewChild('customerSelect') customerSelect!: ElementRef<HTMLSelectElement>


    taskObj: Task  = new Task();
    taskArr: Task[] = [];
    addTaskValue: string = "";
    updateTaskValue : string= '';
    customers: Customer[] = [];
    selectedCustomer: Customer | null = null;
    selectedCustomerId: number | null = null;


    constructor(
      private _taskService: TaskService,
      private _customerService: CustomerService,
      private toastr: ToastrService,
      ){this.selectedCustomer = new Customer();}


      ngOnInit(): void {
        this.updateTaskValue= '';
        this.addTaskValue = '';
        this.taskObj = new Task();
        this.taskArr = [];
        this._customerService.getAllCustomers().subscribe(customers => { this.customers = customers });
  }


  onCustomerSelect(customerId: any ) {
    this.selectedCustomerId = customerId;
}




  newTask() {
    const selectedCustomerId = parseInt(this.customerSelect.nativeElement.value, 10);

    if (!this.addTaskValue || selectedCustomerId === null) {
      return;
    }

    const selectedCustomer = this.customers.find(customer => customer.id == selectedCustomerId);

    if (!selectedCustomer) {
          return        }

      this.taskObj.description = this.addTaskValue;
      this.taskObj.state = "Pendiente";
      this.taskObj.customer = {
          id: selectedCustomer.id,
          person: {
              id: selectedCustomer.person.id,
              name: selectedCustomer.person.name,
              surname: selectedCustomer.person.surname,
              dateOfBirth: selectedCustomer.person.dateOfBirth
          },
          creationTime: new Date().toISOString()
      };

      this._taskService.newTask(this.taskObj).subscribe(
          (resp) => {
              this.ngOnInit();
              this.addTaskValue = '';
              this.selectedCustomerId = null;
              this.taskObj.state = '';

              this.toastr.success('Tarea agregada exitosamente', 'Éxito', {
                timeOut: 3000,
                positionClass: 'toast-top-center',
              });
              alert('tarea agregada exitosamente')
            },

          error => {
              alert('error');
          }
      );
  }


    call(task: Task){
      this.taskObj = task;
    }
  }
