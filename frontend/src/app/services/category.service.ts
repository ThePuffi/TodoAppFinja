import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private url: string = environment.url + "api/category/";

  constructor(private http: HttpClient) { }

  public addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.url + "addCategory", category);
  }

  public editCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(this.url + "updateCategory", category);
  }

  public getCategory(id: number): Observable<Category> {
    let httpParams = new HttpParams().set("CategoryId", id);
    return this.http.get<Category>(this.url + "findCategoryById", {params: httpParams});
  }

  public deleteCategory(id: number): Observable<Category> {
    let httpParams = new HttpParams().set("CategoryId", id);
    return this.http.delete<Category>(this.url + "deleteCategory", {params: httpParams});
  }

  public getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url + "getAllCategories");
  }
}
