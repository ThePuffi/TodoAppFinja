import { Component } from '@angular/core';
import { ToDo } from '../../models/to-do';
import { CommonModule, DatePipe } from '@angular/common';
import { AddToDoComponent } from './add-to-do/add-to-do.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ToDoService } from '../../services/to-do.service';
import { EditToDoComponent } from './edit-to-do/edit-to-do.component';
import { DeleteToDoComponent } from './delete-to-do/delete-to-do.component';

@Component({
  selector: 'app-to-do',
  standalone: true,
  imports: [DatePipe, CommonModule, AddToDoComponent, EditToDoComponent, DeleteToDoComponent, MatDialogModule],
  templateUrl: './to-do.component.html',
  styleUrl: './to-do.component.scss'
})
export class ToDoComponent {
  // Liste für die im Select angezeigten Status erstellen.
  protected statusList: string[] = ["ALLE", "OFFEN", "GESCHLOSSEN"];
  // Den aktiven Status auf den ersten Index der Status Liste ("ALLE") setzen.
  protected activeStatus: string = this.statusList[0];

  protected showModal: boolean = false;

  constructor(
    private todoService: ToDoService,
    public dialog: MatDialog
  ) {
    this.getAllTodos();
  }

  openAddTodoModal() {
    const dialogRef = this.dialog.open(AddToDoComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllTodos();
    });
  }

  openEditTodoModal() {
    const dialogRef = this.dialog.open(EditToDoComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllTodos();
    });
  }

  openDeleteTodoModal(todo: ToDo) {
    const dialogRef = this.dialog.open(DeleteToDoComponent, {
      data: todo,
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllTodos();
    });
  }

  // Liste für die in der Tabelle angezeigten Spalten erstellen.
  protected columns = [
    { key: 'name', label: 'AUFGABE'},
    { key: 'dueDate', label: 'DEADLINE'},
    { key: 'status', label: 'STATUS'},
    { key: 'actions', label: 'AKTIONEN'}
  ];

  // Liste für die vom Backend erhaltenen ToDo Daten erstellen.
  protected todoData: ToDo[] = [];

  protected getAllTodos() {
    this.todoService.getAllTodos().subscribe(res => {
      this.todoData = res;
      this.displayedTodoData = this.todoData;
    });
    this.todoData = [
      {
        id: 1,
        name: "Projekt erstellen",
        status: false,
        categoryId: 1,
        dueDate: new Date(),
        description: "Description",
        members: [
          {
            id: 1,
            username: "ole_w",
            firstName: "Ole",
            lastName: "W",
            password: "1234",
            email: "ole@mail.de",
          }
        ]
      },
      {
        id: 2,
        name: "Projekt schön machen",
        status: true,
        categoryId: 2,
        dueDate: new Date(),
        description: "Description",
        members: [
          {
            id: 1,
            username: "ole_w",
            firstName: "Ole",
            lastName: "W",
            password: "1234",
            email: "ole@mail.de",
          }
        ]
      }
    ];
    this.displayedTodoData = this.todoData;
  }

  // Die angezeigten ToDo Daten auf die vom Backend gesendeten ToDo Daten setzen.
  protected displayedTodoData: ToDo[] = [];

  protected updateStatus(event: any) {
    // Den Wert aus dem Select wert holen.
    this.activeStatus = event.target.value;
    if (this.activeStatus === "OFFEN") {
      // Die angezeigten ToDo Daten so bearbeiten, dass nur ToDo's mit dem Status "OFFEN" angezeigt werden.
      this.displayedTodoData = this.todoData.filter(data => !data.status);
    } else if (this.activeStatus === "GESCHLOSSEN") {
      // Die angezeigten ToDo Daten so bearbeiten, dass nur ToDo's mit dem Status "GESCHLOSSEN" angezeigt werden.
      this.displayedTodoData = this.todoData.filter(data => data.status); 
    } else {
      // Die angezeigten ToDo Daten wieder auf die ungefiltert setzen. 
      this.displayedTodoData = this.todoData.slice();
    }
  }

  protected updateSearch(event: any) {
    // Den Wert aus dem Select wert holen.
    this.activeStatus = event.target.value;
    if (this.activeStatus === "OFFEN") {
      // Die angezeigten ToDo Daten so bearbeiten, dass nur ToDo's mit dem Status "OFFEN" angezeigt werden.
      this.displayedTodoData = this.todoData.filter(data => data.name == event.target.value);
    } else {
      // Die angezeigten ToDo Daten wieder auf die ungefiltert setzen. 
      this.displayedTodoData = this.todoData.slice();
    }
  }

}