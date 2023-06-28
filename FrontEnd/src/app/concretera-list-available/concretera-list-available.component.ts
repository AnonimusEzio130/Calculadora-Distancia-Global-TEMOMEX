import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CoreService } from '../core/core.service';
import { ConcreteraService } from '../services/Concretera.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-concretera-list-available',
  templateUrl: './concretera-list-available.component.html'
})
export class ConcreteraListAvailableComponent {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  public idUsuario: number;
  public lon: number;
  public lat: number;
  dataSourceconcretera!: MatTableDataSource<any>;
  ConcreteradisplayedColumns: string[] = [
    'id',
    'Name',
    'Radius',
    'Latitude',
    'Longitude',
  ];

  constructor(
    private _dialogRef: MatDialogRef<ConcreteraListAvailableComponent>,
    private _empService: ConcreteraService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService
  ) {
    console.log(this.data);
    this.idUsuario = this.data.userId;
    this.lat = this.data.lat;
    this.lon = this.data.lon;
  }

  ngOnInit(): void {
    this.getConcreteraList();
  }

  getConcreteraList() {
    
    let ubicacion = {
      latitud:this.lat,
      longitud:this.lon
    }
    this._empService.validateUbication(ubicacion).subscribe({
      next: (res) => {
        this.dataSourceconcretera = new MatTableDataSource(res);
        this.dataSourceconcretera.sort = this.sort;
        this.dataSourceconcretera.paginator = this.paginator;
      },
      error: console.log,
    });
  }

}
