import { Component } from '@angular/core';
import { ToDoService } from '../../services/to-do.service';
import { ToDo } from '../../models/to-do';
import { CommonModule, DatePipe } from '@angular/common';
import { Appointment } from '../../models/appointment';
import { AppointmentService } from '../../services/appointment.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Member } from '../../models/member';
import { MemberService } from '../../services/member.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DatePipe, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  protected statusList: string[] = ["ALLE", "OFFEN", "GESCHLOSSEN"];
  protected _user: Observable<Member> = new Observable<Member>;
  protected user?: Member;

  constructor(
    private todoService: ToDoService,
    private memberService: MemberService,
    private appointmentService: AppointmentService,
    private router: Router
  ) {

    let userId = localStorage.getItem("userId");
    if (userId) this._user = this.memberService.getMember(parseInt(userId));
    this._user.subscribe(res => {
      this.user = res;
      this.getAllTodos();
      this.getAllAppointments();
    });
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

   protected getAllTodos() {
    if (this.user && this.user.id)
    this.todoService.getAllTodosByMemberId(this.user.id).subscribe(res => {
      const currentMonth = new Date().getMonth(); // Aktueller Monat (0-basiert, also Januar ist 0)
      const currentYear = new Date().getFullYear(); // Aktuelles Jahr

      this.displayedTodoData = res.filter(todo => {
        const todoDate = new Date(todo.dueDate); // Annahme: `creationDate` ist das Feld mit dem Erstellungsdatum
        return todoDate.getMonth() === currentMonth && todoDate.getFullYear() === currentYear;
      });
    });
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
  }

  navigate(path: string){
    this.router.navigate([path]);
  }

}
