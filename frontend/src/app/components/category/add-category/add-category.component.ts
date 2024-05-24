import { Component, EventEmitter, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';
import { Color } from '../../../models/color';

@Component({
  selector: 'app-add-category',
  standalone: true,
  imports: [ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatInputModule],
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.scss'
})
export class AddCategoryComponent {
  // addCategory als Output für das Category-Component festlegen.
  @Output() addCategory: EventEmitter<Category> = new EventEmitter();
  // Category-Form für das hinzufügen von Categories erstellen.
  protected categoryForm: FormGroup;

  constructor(
    private categoryService: CategoryService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<AddCategoryComponent>
  ) {
    // Category-Form mit attributen befüllen.
    this.categoryForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      color: new FormControl('', Validators.required)
    });
  }

  submitCategory() {
    // Prüfen ob die Category-Form valide ist (anhand der in dem contructor festgelegten Validator methoden)
    if (this.categoryForm.valid) {
      // Die von Nutzer eingebenen Daten an das Backend übersenden.
      this.categoryService.addCategory(this.categoryForm.value).subscribe(res => {
        // Die von Nutzer eingebenen Daten an das Category-Component übersenden.
        this.addCategory.emit(this.categoryForm.value);
        // Dialog wieder schließen.
        this.dialogRef.close();
      });
    }
  }
}
