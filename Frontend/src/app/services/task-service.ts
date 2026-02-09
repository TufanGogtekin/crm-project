import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TaskInterface } from '../interfaces/task-interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskService {

  constructor(private http: HttpClient) { }

  private taskPath = "http://localhost:8080/rest/api/task";

  saveTask(data: TaskInterface){
    return this.http.post<TaskInterface>(this.taskPath + "/save", data);
  }

  getAllTask(){
    return this.http.get<TaskInterface>(this.taskPath + "/list");
  }
}
