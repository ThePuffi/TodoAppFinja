import { Routes } from '@angular/router';
import { ToDoComponent } from './components/to-do/to-do.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CategoryComponent } from './components/category/category.component';

export const routes: Routes = [
    { path: "todos", component: ToDoComponent },
    { path: "categories", component: CategoryComponent },
    { path: "dashboard", component: DashboardComponent },
    { path: "calendar", component: CalendarComponent },
    { path: '',   redirectTo: 'dashboard', pathMatch: 'full' }
];
