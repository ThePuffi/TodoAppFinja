import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';
import { CategoryService } from '../../../services/category.service';
import { ToDoService } from '../../../services/to-do.service';
import { Category } from '../../../models/category';
import { Member } from '../../../models/member';
import { MemberService } from '../../../services/member.service';
import { ToDo } from '../../../models/to-do';


@Component({
  selector: 'app-add-to-do',
  standalone: true,
  imports: [ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatInputModule, MatDatepickerModule],
  providers: [provideNativeDateAdapter(), {provide: MAT_DATE_LOCALE, useValue: 'de-DE'}],
  templateUrl: './add-to-do.component.html',
  styleUrl: './add-to-do.component.scss'
})
export class AddToDoComponent {
  // addTodo als Output für das ToDo-Component festlegen.
  @Output() addTodo: EventEmitter<ToDo> = new EventEmitter();
  // ToDo-Form für das hinzufügen von ToDo's erstellen.
  protected todoForm: FormGroup;
  // Liste für die im Select angezeigten Kategorien erstellen.
  protected categoryList: Category[] = [];
  // Liste für die im Select angezeigten Benutzer/Partner erstellen.
  protected memberList: Member[] = [];

  constructor(
    private todoService: ToDoService,
    private categoryService: CategoryService,
    private memberService: MemberService,
    private formBuilder: FormBuilder
  ) {
    // ToDo-Form mit attributen befüllen.
    this.todoForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      status: new FormControl(false, Validators.required),
      categoryId: new FormControl(0, Validators.required),
      dueDate: new FormControl(new Date(), Validators.required),
      description: new FormControl('', Validators.required),
      members: new FormControl([], Validators.required)
    });
    // Benutzer Liste mit Benutzern aus dem Backend befüllen.
    this.memberService.getAllMembers().subscribe(res => {
      this.memberList = res;
    });
    // Kategorie Liste mit Kategorien aus dem Backend befüllen.
    this.categoryService.getAllCategories().subscribe(res => {
      this.categoryList = res;
    });
  }

  submitTodo() {
    // Prüfen ob die ToDo-Form valide ist (anhand der in dem contructor festgelegten Validator methoden)
    if (this.todoForm.valid) {
      // Die von Nutzer eingebenen Daten an das Backend übersenden.
      this.todoService.addTodo(this.todoForm.value).subscribe(res => {
        // Die von Nutzer eingebenen Daten an das ToDo-Component übersenden.
        console.log(res);        
        this.addTodo.emit(this.todoForm.value);
      });
    }
  }
}
