import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page.model';
import { Task } from '../models/task.model';

const API_URL = environment.apiUrl + '/v1/tasks'

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private httpClient: HttpClient) { }

  createTask(task: Task): Observable<Task> {
    return this.httpClient.post<Task>(API_URL, task);
  }

  getAllTasks(): Observable<Page<Task>> {
    return this.httpClient.get<Page<Task>>(API_URL);
  }
}
