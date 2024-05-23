import { Component, OnInit, TemplateRef } from '@angular/core';
import { Task } from 'src/app/model/task';
import { TaskService } from 'src/app/service/task.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

    taskObj: Task  = new Task();
    taskArr: Task[] = [];
    addTaskValue: string = "";
    updateTaskValue : string= '';
    filteredTasks: Task[] = [];
    currentPage: number = 1;
    pageSize: number = 9;
    pagedTaskArr: Task[] | undefined;



    constructor( private _taskService: TaskService){}


  ngOnInit(): void {
    this.updateTaskValue= '';
    this.taskObj = new Task();
    this.taskArr = [];
    this.getAllTask();
    this.filteredTasks = [];
    
  }


  
  getAllTask(){
    this._taskService.getAllTask().subscribe((resp: Task[]) => {  
      this.taskArr = resp;
      this.currentPage = 1;
      this.updatePagedTasks();
    },
    error => {
      alert(error)
    })
  }
  
  
  updateTask(){
    this.taskObj.description = this.updateTaskValue;
    this._taskService.updateTask(this.taskObj).subscribe((resp) => { 
      this.ngOnInit();
    }, error =>{
      console.log('Error updating task: ' + JSON.stringify(error)); 
    })
}

    deleteTask(task: Task){
      this._taskService.deleteTask(task).subscribe((resp) => { 
        this.ngOnInit();
      }, error =>{
        alert('error')
      })
    }
    
    call(task: Task){
      this.taskObj = task;
      this.updateTaskValue = task.description;
    }

   
    updatePagedTasks() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = Math.min(startIndex + this.pageSize, this.taskArr.length);
      this.pagedTaskArr = this.taskArr.slice(startIndex, endIndex);
    }
  
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
        this.updatePagedTasks();
      }
    }
  
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.updatePagedTasks();
      }
    }
  
    get totalPages(): number {
      return Math.ceil(this.taskArr.length / this.pageSize);
    }

  }