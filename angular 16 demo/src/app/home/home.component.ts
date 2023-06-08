import { CommonModule } from '@angular/common';
import { Component, DestroyRef, Input, OnInit, computed, effect, inject, signal } from '@angular/core';
import { RegisterComponent } from '../register/register.component';
import { RouterLink } from '@angular/router';
import { toObservable} from '@angular/core/rxjs-interop';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [CommonModule, RegisterComponent, RouterLink],
  standalone: true
})
export class HomeComponent implements OnInit{
@Input() data!: string;
name: string = "ahmed";
domainName = "www.aprendetypescript.com";
price = 100;
firstName = signal('Ahmed');
lastName = signal('Ali');
fullName= computed(() => `${this.firstName()} ${this.lastName()}`)
count= signal(0);
count$= toObservable(this.count);

constructor(){
  inject(DestroyRef).onDestroy(() => {
    alert("home destroyed on destroyRef");
  })
  effect(()=> console.log("Name changed : " + this.fullName()))
    this.count$.subscribe(res =>{
      console.log(res)
    })
}

ngOnInit(): void {
  console.log(this.data)
}

ngOnDestroy(): void {
  alert("home destroyed on ngOnDerstroy");
}

setName(name: string): void{
  this.firstName.set(name);
}


}
