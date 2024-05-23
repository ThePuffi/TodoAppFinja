import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MemberService } from '../../services/member.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerForm: FormGroup;
  showErrorMessage: string | undefined;

  constructor(private fb: FormBuilder, private router: Router, private memberService: MemberService, private auth: AuthService) {
    this.registerForm = this.fb.group({
      firstname: [''],
      lastname: [''],
      username: [''],
      password: [''],
      email: ['']
    });
  }

  //On-Click Funktion um sich einzuloggen
  //Value der LoginForm entspricht dem Member-Objekt
  //Response und error wird hier gehandelt
  //subscribe ist durchgestrichen, weil diese Art von Errorhandling veraltet ist
  onSubmit() {
    this.memberService.register(this.registerForm.value).subscribe(
      //Wenn response ohne Fehler da ist
      (response) => {
        if(response != null) {
          this.auth.login();
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
