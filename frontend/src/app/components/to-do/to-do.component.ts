import { Component } from '@angular/core';
import { ToDo } from '../../models/to-do';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-to-do',
  standalone: true,
  imports: [DatePipe, CommonModule],
  templateUrl: './to-do.component.html',
  styleUrl: './to-do.component.scss'
})
export class ToDoComponent {
  
  constructor() { }

  protected columns = [
    { key: 'name', label: 'AUFGABE'},
    { key: 'dueDate', label: 'DEADLINE'},
    { key: 'status', label: 'STATUS'},
    { key: 'actions', label: 'AKTIONEN'}
  ];

  protected displayedTodoData: ToDo[] = [
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

  addTodo() {

  }

  protected statuses: string[] = ["ALLE", "OFFEN", "GESCHLOSSEN"];

  protected updateFilter(event: Event) {
    console.log(event);
    
  }

}
