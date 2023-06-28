import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CoreService } from '../core/core.service';
import { UsuarioService } from '../services/Usuario.service';

@Component({
  selector: 'app-emp-add-edit',
  templateUrl: './usuario-add-edit.component.html',
  styleUrls: ['./usuario-add-edit.component.scss'],
})
export class UserAddEditComponent implements OnInit {
  UsuarioForm: FormGroup;

  constructor(
    private _fb: FormBuilder,
    private _userService: UsuarioService,
    private _dialogRef: MatDialogRef<UserAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService
  ) {
    this.UsuarioForm = this._fb.group({
      nombre: '',
      ubicacion: this._fb.group({
        latitud:undefined,
        longitud:undefined
      })
    });
  }

  ngOnInit(): void {
    this.UsuarioForm.patchValue(this.data);
  }

  onFormSubmit() {
    if (this.UsuarioForm.valid) {
      if (this.data) {
        this._userService
          .updateUsuario(this.data.id, this.UsuarioForm.value)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('Datos del usuario actualizados!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
      } else {
        this._userService.addUsuario(this.UsuarioForm.value).subscribe({
          next: (val: any) => {
            this._coreService.openSnackBar('Usuario Añadida Correctamente');
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
