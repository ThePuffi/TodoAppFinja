import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Category } from '../../../models/category';
import { Member } from '../../../models/member';
import { ToDo } from '../../../models/to-do';
import { CategoryService } from '../../../services/category.service';
import { MemberService } from '../../../services/member.service';
import { ToDoService } from '../../../services/to-do.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-to-do',
  standalone: true,
  imports: [ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatInputModule, MatDatepickerModule],
  providers: [provideNativeDateAdapter()],
  templateUrl: './edit-to-do.component.html',
  styleUrl: './edit-to-do.component.scss'
})
export class EditToDoComponent {
  // editTodo als Output für das ToDo-Component festlegen.
  @Output() editTodo: EventEmitter<ToDo> = new EventEmitter();
  // ToDo-Form für das hinzufügen von ToDo's erstellen.
  protected todoForm: FormGroup;
  // Liste für die im Select angezeigten Kategorien erstellen.
  protected categoryList: Category[] = [];
  // Liste für die im Select angezeigten Benutzer/Partner erstellen.
  protected memberList: Member[] = [];
  
  protected selectedMembers: Member[] = [];

  protected _user: Observable<Member> = new Observable<Member>;
  protected user?: Member;

  constructor(
    private todoService: ToDoService,
    private categoryService: CategoryService,
    private memberService: MemberService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EditToDoComponent>,
    @Inject(MAT_DIALOG_DATA) public todo: ToDo
  ) {
    // ToDo-Form mit attributen befüllen.
    this.todoForm = this.formBuilder.group({
      id: todo.id,
      name: new FormControl(todo.name, Validators.required),
      status: new FormControl(todo.status, Validators.required),
      categoryId: new FormControl(todo.categoryId, Validators.required),
      dueDate: new FormControl(todo.dueDate, Validators.required),
      description: new FormControl(todo.description),
      members: new FormControl(todo.members, Validators.required)
    });   
    this.selectedMembers = todo.members

    let userId = localStorage.getItem("userId");
    if (userId) this._user = this.memberService.getMember(parseInt(userId));
    this._user.subscribe(res => {
      this.user = res;
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
      this.todoService.editTodo(this.todoForm.value).subscribe(res => {
        // Die von Nutzer eingebenen Daten an das ToDo-Component übersenden.
        this.editTodo.emit(this.todoForm.value);
        // Dialog wieder schließen.
        this.dialogRef.close();
      });
    }
  }
}
