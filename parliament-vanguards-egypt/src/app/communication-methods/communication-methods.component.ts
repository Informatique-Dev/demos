import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-communication-methods',
  templateUrl: './communication-methods.component.html',
  styleUrls: ['./communication-methods.component.scss']
})
export class CommunicationMethodsComponent implements OnInit {
  communicationForm!:FormGroup;
  constructor(private formBuilder:FormBuilder ){}
  ngOnInit(): void {
    this.Form();
  }
  
  Form() {
    this.communicationForm = this.formBuilder.group({
      parentPhone: ['', [Validators.required,Validators.minLength(11),Validators.maxLength(11),Validators.pattern("^[0-9]*$")]],
      phone: ['',[Validators.required,Validators.minLength(11),Validators.maxLength(11),Validators.pattern("^[0-9]*$")]],
      email: ['', [Validators.required,Validators.email]],
      address: ['',Validators.required],
      
    });
  }
}
