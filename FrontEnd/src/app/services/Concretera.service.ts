import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConcreteraService {
  constructor(private _http: HttpClient) {}

  addConcretera(data: any): Observable<any> {
    return this._http.post('http://localhost:8080/app/Cementeras/saveCementera', data);
  }

  updateConcretera(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:8080/app/Cementeras/update/${id}`, data);
  }

  getConcreteraList(): Observable<any> {
    return this._http.get('http://localhost:8080/app/Cementeras/Cmentera');
  }

  deleteConcretera(id: number): Observable<any> {
    return this._http.delete(`http://localhost:8080/app/Cementeras/delete/${id}`);
  }

  validateUbication(data: any): Observable<any> {
    return this._http.post('http://localhost:8080/app/Cementeras/validarUbicacion', data);
  }
}
