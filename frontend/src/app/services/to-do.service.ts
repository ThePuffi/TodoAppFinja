import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ToDo } from '../models/to-do';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToDoService {
  private url: string = environment.url + "api/todo/";

  constructor(private http: HttpClient) { }

  public addTodo(todo: ToDo): Observable<ToDo> {
    return this.http.post<ToDo>(this.url + "addTodo", todo);
  }

  public editTodo(todo: ToDo): Observable<ToDo> {
    return this.http.put<ToDo>(this.url + "updateTodo", todo);
  }

  public getTodo(id: number): Observable<ToDo> {
    let httpParams = new HttpParams().set("TodoId", id);
    return this.http.get<ToDo>(this.url + "findTodoById", {params: httpParams});
  }

  public deleteTodo(id: number): Observable<ToDo> {
    let httpParams = new HttpParams().set("TodoId", id);
    return this.http.delete<ToDo>(this.url + "deleteTodo", {params: httpParams});
  }

  public getAllTodos(): Observable<ToDo[]> {
    return this.http.get<ToDo[]>(this.url + "getAllTodos");
  }
}
