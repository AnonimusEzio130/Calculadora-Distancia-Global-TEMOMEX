import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CoreService } from '../core/core.service';
import { ConcreteraService } from '../services/Concretera.service';

@Component({
  selector: 'app-emp-add-edit',
  templateUrl: './concretera-add-edit.component.html',
  styleUrls: ['./emp-add-edit.component.scss'],
})
export class EmpAddEditComponent implements OnInit {
  ConcreteraForm: FormGroup;

  constructor(
    private _fb: FormBuilder,
    private _ConcreteraService: ConcreteraService,
    private _dialogRef: MatDialogRef<EmpAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService
  ) {
    this.ConcreteraForm = this._fb.group({
      nombre: '',
      ubicacion: this._fb.group({
        latitud:undefined,
        longitud:undefined
      }),
      radio:undefined
    });
  }

  ngOnInit(): void {
    this.ConcreteraForm.patchValue(this.data);
  }

  onFormSubmit() {
    if (this.ConcreteraForm.valid) {
      if (this.data) {
        this._ConcreteraService
          .updateConcretera(this.data.id, this.ConcreteraForm.value)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('Datos de la concretera actualizados!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
      } else {
        this._ConcreteraService.addConcretera(this.ConcreteraForm.value).subscribe({
          next: (val: any) => {
            this._coreService.openSnackBar('Concretera Añadida Correctamente');
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err);
          },
        });
      }
    }
  }
}
