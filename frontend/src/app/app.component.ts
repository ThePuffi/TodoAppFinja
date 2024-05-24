import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidenavComponent } from "./components/sidenav/sidenav.component";
import { AuthService } from './services/auth.service';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss',
    imports: [RouterOutlet, SidenavComponent]
})
export class AppComponent {

  title = 'ToDo App';
  isLoggedIn: boolean = false;
  constructor(private auth: AuthService) {
    this.auth.isLoggedIn.subscribe((status: boolean) => {
      this.isLoggedIn = status;
    });
  }

  logout() {
    this.auth.logout();
    }
}
