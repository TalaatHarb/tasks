import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { TasksService } from './services/tasks.service';
import {Task } from './models/task.model';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  tasks: Task[] = [];
  details = '';
  loading = true;

  @ViewChild('taskInput') taskInput?: ElementRef;

  constructor(private tasksService: TasksService){

  }
  ngOnInit(): void {
    this.init();
  }

  init(): void{
    this.loadTasks();
    this.focusInput();
  }

  focusInput(){
    if(this.taskInput){
      const input = this.taskInput as ElementRef;
      input.nativeElement.focus();
    }
  }

  loadTasks(): void {
    this.loading = true;
    this.tasksService.getAllTasks().subscribe((pageOfTasks) =>{
      this.tasks = pageOfTasks.content;
      this.loading = false;
    });
  }

  createTask(details: string): void{
    const task: Task = {details: details, status: false};
    this.tasksService.createTask(task).subscribe((task)=>{
      this.tasks.push(task);
      this.details = '';
      this.focusInput();
    });
  }

  addClicked(): void{
    if(this.details && this.details !== ''){
      this.createTask(this.details)
    }
  }

  reloadTasks(){
    this.loadTasks();
  }


}
