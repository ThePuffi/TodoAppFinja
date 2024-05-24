import { Component } from '@angular/core';
import { ToDoService } from '../../services/to-do.service';
import { ToDo } from '../../models/to-do';
import { CommonModule, DatePipe } from '@angular/common';
import { Appointment } from '../../models/appointment';
import { AppointmentService } from '../../services/appointment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DatePipe, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  protected statusList: string[] = ["ALLE", "OFFEN", "GESCHLOSSEN"];

  constructor(
    private todoService: ToDoService,
    private appointmentService: AppointmentService,
    private router: Router
  ) {
    this.getAllTodos();
    this.getAllAppointments();
  }

  protected columnsTodo = [
    { key: 'name', label: 'AUFGABE'},
    { key: 'dueDate', label: 'DEADLINE'},
    { key: 'status', label: 'STATUS'},
  ];

    protected columnsAppointment = [
    { key: 'title', label: 'TITEL'},
    { key: 'dueDate', label: 'DATUM'},
  ];

  protected displayedTodoData: ToDo[] = [];
  protected displayCalendarData: Appointment[] = [];
  protected todoData: ToDo[] = []; //can be deleted in prod

   protected getAllTodos() {
    this.todoService.getAllTodos().subscribe(res => {
      const currentDate = new Date();
      res.forEach(element => {
        if(element.dueDate.getDate() <= currentDate.getDate() + 14) {
          this.displayedTodoData = res;
        }

      });
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
            firstname: "Ole",
            lastname: "W",
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
            firstname: "Ole",
            lastname: "W",
            password: "1234",
            email: "ole@mail.de",
          }
        ]
      }
    ];
    this.displayedTodoData = this.todoData;
  }

  protected getAllAppointments() {
    this.appointmentService.getAllAppointments().subscribe(res => {
      this.displayCalendarData = res.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
    });
    this.displayCalendarData = [
      {
        id: 1,
        title: "Projekt erstellen",
        description: "something",
        date:  new Date(),
        members: []
      },
            {
        id: 1,
        title: "Projekt erstellen",
        description: "something",
        date:  new Date(),
        members: []
      },
        
    ]
    this.displayedTodoData = this.todoData;
  }

  navigate(path: string){
    this.router.navigate([path]);
  }

}
