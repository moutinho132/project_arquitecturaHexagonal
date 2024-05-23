import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../model/task';
import { Observable } from 'rxjs';
import { endpoint, endpointTask } from '../endpoint';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  getTasks() {
    throw new Error('Method not implemented.');
  }

  private serviceURL = `${endpointTask}task`;
  constructor(
    private http: HttpClient
  ) { }

  private generateId(): number {
    return new Date().getTime();
  }

  newTask(task: Task): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `${token}`);
    return this.http.post(this.serviceURL, task, {headers})
  }

  getAllTask(): Observable<Task[]>{
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `${token}`);
    return this.http.get<Task[]>(this.serviceURL,{headers})
  }

  deleteTask(task: Task): Observable<any>{
    return this.http.delete(`${this.serviceURL}/${task.id}`)
  }

  updateTask(task: Task): Observable<any>{
    console.log(`${this.serviceURL}/${+task.id}`)
    return this.http.put<Task>(`${this.serviceURL}/${task.id}`, task)
  }

}
