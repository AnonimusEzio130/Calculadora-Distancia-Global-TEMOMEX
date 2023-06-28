import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EmpAddEditComponent } from './concretera-add-edit/emp-add-edit.component';
import { UserAddEditComponent} from './usuario-add-edit/usuario-add-edit.component';
import { ConcreteraListAvailableComponent } from './concretera-list-available/concretera-list-available.component';
import { ConcreteraService } from './services/Concretera.service';
import { UsuarioService } from './services/Usuario.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CoreService } from './core/core.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  ConcreteradisplayedColumns: string[] = [
    'id',
    'Name',
    'Radius',
    'Latitude',
    'Longitude',
    'action',
  ];
  UsuariodisplayedColumns: string[] = [
    'id',
    'Name',
    'Latitude',
    'Longitude',
    'action',
  ];
  dataSourceconcretera!: MatTableDataSource<any>;
  dataSourceUsuario!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    private _dialog: MatDialog,
    private _empService: ConcreteraService,
    private _empuserService : UsuarioService,
    private _coreService: CoreService
  ) {}
  ngOnInit(): void {
    this.getConcreteraList();
    this.getUserList();
  }
  openAddEditEmpForm() {
    const dialogRef = this._dialog.open(EmpAddEditComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getConcreteraList();
          this.getUserList();
        }
      },
    });
  }
  openAddEditUserForm() {
    const dialogRef = this._dialog.open(UserAddEditComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getConcreteraList();
          this.getUserList();
        }
      },
    });
  }
  getConcreteraList() {
    this._empService.getConcreteraList().subscribe({
      next: (res) => {
        this.dataSourceconcretera = new MatTableDataSource(res);
        this.dataSourceconcretera.sort = this.sort;
        this.dataSourceconcretera.paginator = this.paginator;
      },
      error: console.log,
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceUsuario.filter = filterValue.trim().toLowerCase();

    if (this.dataSourceUsuario.paginator) {
      this.dataSourceUsuario.paginator.firstPage();
    }
  }

  deleteConcretera(id: number) {
    this._empService.deleteConcretera(id).subscribe({
      next: (res) => {
        this._coreService.openSnackBar('Employee deleted!', 'done');
        this.getConcreteraList();
      },
      error: console.log,
    });
  }

  openEditForm(data: any) {
    const dialogRef = this._dialog.open(EmpAddEditComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getConcreteraList();
        }
      },
    });
  }
  //Seccion de Usuario
  getUserList() {
    this._empuserService.getUsuarioList().subscribe({
      next: (res) => {
        this.dataSourceUsuario = new MatTableDataSource(res);
        this.dataSourceUsuario.sort = this.sort;
        this.dataSourceUsuario.paginator = this.paginator;
      },
      error: console.log,
    });
  }
  deleteUsuario(id: number) {
    this._empuserService.deleteUsuario(id).subscribe({
      next: (res) => {
        this._coreService.openSnackBar('Employee deleted!', 'done');
        this.getConcreteraList();
      },
      error: console.log,
    });
  }
  viewCementeras(row: any){
    const dialogRef = this._dialog.open(ConcreteraListAvailableComponent, {
      data: {
        userId: row.id,
        lat: row.ubicacion.latitud,
        lon: row.ubicacion.longitud
      }
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getConcreteraList();
          this.getUserList();
        }
      },
    });
  }
}
