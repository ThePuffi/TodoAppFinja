import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-delete-category',
  standalone: true,
  imports: [ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatInputModule],
  templateUrl: './delete-category.component.html',
  styleUrl: './delete-category.component.scss'
})
export class DeleteCategoryComponent {
  @Output() deleteCategory: EventEmitter<any> = new EventEmitter();

  constructor(
    public dialogRef: MatDialogRef<DeleteCategoryComponent>,
    @Inject(MAT_DIALOG_DATA) public category: Category,
    private categoryService: CategoryService
  ) { }

  protected confirmDelete() {
    // Dem Backend den Befehl geben die ausgewählte Kategorie zu löschen.
    console.log("delete: ", this.category);
    if (this.category.id) this.categoryService.deleteCategory(this.category.id).subscribe(res => {
      this.deleteCategory.emit();
      this.cancel();
    });
  }
  
  protected cancel() {
    // Den Dialog wieder schließen/Die Aktion abbrechen.
    this.dialogRef.close();
  }

}
