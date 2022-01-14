import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const API_URL = environment.apiUrl + '/v1/tasks'

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private httpClient: HttpClient) { }

  createTask(task: Task): Observable<Task>{
    return this.httpClient.post<Task>(API_URL, task);
  }

  getAllTasks(): Observable<Task[]>{
    return this.httpClient.get<Task[]>(API_URL);
  }
}
