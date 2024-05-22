import { Component } from '@angular/core';
import { ToDo } from '../../models/to-do';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-to-do',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './to-do.component.html',
  styleUrl: './to-do.component.scss'
})
export class ToDoComponent {
  
  constructor() { }

  protected columns = [
    { key: 'name', label: 'Title'},
    { key: 'dueDate', label: 'Due Date'}
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
    }
  ];

  addTodo() {

  }

}
