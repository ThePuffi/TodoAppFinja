import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Appointment } from '../models/appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

 private url: string = environment.url + "/api/category/";

  constructor(private http: HttpClient) { }

  public addAppointment(category: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(this.url + "addAppointment", category);
  }

  public editAppointment(category: Appointment): Observable<Appointment> {
    return this.http.put<Appointment>(this.url + "updateAppointment", category);
  }

  public getAppointment(id: number): Observable<Appointment> {
    let httpParams = new HttpParams().set("AppointmentId", id);
    return this.http.get<Appointment>(this.url + "findAppointmentById", {params: httpParams});
  }

  public deleteAppointment(id: number): Observable<Appointment> {
    let httpParams = new HttpParams().set("AppointmentId", id);
    return this.http.delete<Appointment>(this.url + "deleteAppointment", {params: httpParams});
  }

  public getAllAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.url + "getAllAppointments");
  }
}
