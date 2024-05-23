import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MemberService } from '../../services/member.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,  RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup; //Gruppe für das Form-Tag in der HTML
  showErrorMessage: string | undefined; //Globale Deklarierung für mögliche Error-Message

  //constructor wird beim initialen Aufruf der Component ausgeführt
  constructor(private fb: FormBuilder, private router: Router, private memberService: MemberService, private auth: AuthService) {
    //initialisieren der Formgroup
    this.loginForm = this.fb.group({
      username: [''],
      password: ['']
    });
  }

  //On-Click Funktion um sich einzuloggen
  //Value der LoginForm entspricht dem Member-Objekt
  //Response und error wird hier gehandelt
  //subscribe ist durchgestrichen, weil diese Art von Errorhandling veraltet ist
  onSubmit() {
    this.memberService.login(this.loginForm.value).subscribe(
      //Wenn response ohne Fehler da ist
      (response) => {
        if(response != null) {
          this.auth.login(); //setzen der Projektweiten Variable, um festzulegen, ob man eingeloggt ist
          this.router.navigate(["/dashboard"])
        }
      },
      //Wenn Fehler auftaucht
      (error) => {
          this.showErrorMessage = "Etwas ist schiefgelaufen: Status " + error.status
      }
    )
  }

}
