import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  private isLoading:boolean = false
  constructor() { }
  setLodaing(isLoading:boolean){
    this.isLoading = isLoading
  }
  getLoading():boolean{
    return this.isLoading
  }
}
