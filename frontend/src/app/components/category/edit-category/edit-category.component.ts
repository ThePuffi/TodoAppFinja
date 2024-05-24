import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-edit-category',
  standalone: true,
  imports: [ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatInputModule],
  templateUrl: './edit-category.component.html',
  styleUrl: './edit-category.component.scss'
})
export class EditCategoryComponent {
  // editCategory als Output für das Category-Component festlegen.
  @Output() editCategory: EventEmitter<Category> = new EventEmitter();
  // Category-Form für das hinzufügen von Category's erstellen.
  protected categoryForm: FormGroup;

  constructor(
    private categoryService: CategoryService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EditCategoryComponent>,
    @Inject(MAT_DIALOG_DATA) public category: Category
  ) {
    // Category-Form mit attributen befüllen.
    this.categoryForm = this.formBuilder.group({
      id: category.id,
      name: new FormControl(category.name, Validators.required),
      color: new FormControl(category.color, Validators.required)
    });
  }
  
  submitCategory() {
    // Prüfen ob die Category-Form valide ist (anhand der in dem contructor festgelegten Validator methoden)
    if (this.categoryForm.valid) {
      // Die von Nutzer eingebenen Daten an das Backend übersenden.
      this.categoryService.editCategory(this.categoryForm.value).subscribe(res => {
        // Die von Nutzer eingebenen Daten an das Category-Component übersenden.
        this.editCategory.emit(this.categoryForm.value);
        // Dialog wieder schließen.
        this.dialogRef.close();
      });
    }
  }
}
