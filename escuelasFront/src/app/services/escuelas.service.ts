import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EscuelasService {
  url: any= 'http://localhost:8080/api/escuelas';
  constructor(private http:HttpClient) {
   }
   obtenerEscuelas(){
    return  this.http.get(this.url)
  }
}
