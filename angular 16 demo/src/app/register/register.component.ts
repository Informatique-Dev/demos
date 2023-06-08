import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  @Input({required : true}) name!: string;
  registerForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
  });


  sendForm() {
    console.log(this.registerForm.value);
  }
}
