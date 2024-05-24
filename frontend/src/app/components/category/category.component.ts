import { DatePipe, CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/category.service';
import { AddCategoryComponent } from '../category/add-category/add-category.component';
import { DeleteCategoryComponent } from '../category/delete-category/delete-category.component';
import { EditCategoryComponent } from '../category/edit-category/edit-category.component';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [DatePipe, CommonModule, AddCategoryComponent, EditCategoryComponent, DeleteCategoryComponent, MatDialogModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.scss'
})
export class CategoryComponent {
  // Liste für die im Select angezeigten Status erstellen.
  protected statusList: string[] = ["ALLE", "OFFEN", "GESCHLOSSEN"];
  // Den aktiven Status auf den ersten Index der Status Liste ("ALLE") setzen.
  protected activeStatus: string = this.statusList[0];

  protected showModal: boolean = false;

  constructor(
    private categoryService: CategoryService,
    public dialog: MatDialog
  ) {
    this.getAllCategories();
  }

  openAddCategoryModal() {
    const dialogRef = this.dialog.open(AddCategoryComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllCategories();
    });
  }

  openEditCategoryModal(category: Category) {
    const dialogRef = this.dialog.open(EditCategoryComponent, {
      data: category,
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllCategories();
    });
  }

  openDeleteCategoryModal(category: Category) {
    const dialogRef = this.dialog.open(DeleteCategoryComponent, {
      data: category,
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllCategories();
    });
  }

  // Liste für die in der Tabelle angezeigten Spalten erstellen.
  protected columns = [
    { key: 'name', label: 'KATEGORIE'},
    { key: 'color', label: 'FARBE'},
    { key: 'actions', label: 'AKTIONEN'}
  ];

  // Liste für die vom Backend erhaltenen Category Daten erstellen.
  protected categoryData: Category[] = [];

  protected getAllCategories() {
    this.categoryService.getAllCategories().subscribe(res => {
      console.log(res);
      
      this.categoryData = res;
      this.displayedCategoryData = this.categoryData;
    });
  }

  // Die angezeigten Category Daten auf die vom Backend gesendeten Category Daten setzen.
  protected displayedCategoryData: Category[] = [];

  protected updateSearch(event: any) {
    // Die angezeigten ToDo Daten so bearbeiten, dass nur ToDo's mit dem gesuchten Keyword angezeigt werden.
    this.displayedCategoryData = this.categoryData.filter(data => data.name.includes(event.target.value));
  }

}