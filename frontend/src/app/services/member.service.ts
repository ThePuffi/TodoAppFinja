import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Member } from '../models/member';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private url: string = environment.url + "api/member/";

  constructor(private http: HttpClient) { }

  public login(member: Member): Observable<Member> {
    return this.http.post<Member>(this.url + "login", member);
  }

  public register(member: Member): Observable<Member> {
    return this.http.post<Member>(this.url + "registrate", member);
  }
  public editMember(member: Member): Observable<Member> {
    return this.http.put<Member>(this.url + "updateMember", member);
  }

  public getMember(id: number): Observable<Member> {
    let httpParams = new HttpParams().set("MemberId", id);
    return this.http.get<Member>(this.url + "findMemberById", {params: httpParams});
  }

  public deleteMember(id: number): Observable<Member> {
    let httpParams = new HttpParams().set("MemberId", id);
    return this.http.delete<Member>(this.url + "deleteMember", {params: httpParams});
  }

  public getAllMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(this.url + "getAllMembers");
  }


}
