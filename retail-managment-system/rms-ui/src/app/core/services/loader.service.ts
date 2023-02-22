import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  private isLoading:boolean = false
  setLoading(isLoading:boolean){
    this.isLoading = isLoading
  }
  getLoading():boolean{
    return this.isLoading
  }
}
