import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);  

  constructor(private router: Router) {
    let userId = localStorage.getItem("userId");
    if (userId) this.login();
  }
  
  login() {
    this.loggedIn.next(true);
  }

  logout() {
    localStorage.removeItem("userId");
    this.loggedIn.next(false);
    this.router.navigate(["login"])
  }

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
}
