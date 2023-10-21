import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-attachments',
  templateUrl: './attachments.component.html',
  styleUrls: ['./attachments.component.scss']
})
export class AttachmentsComponent implements OnInit {
  attachmentForm!:FormGroup;
  constructor(private formBuilder:FormBuilder ){}
  ngOnInit(): void {
    this.Form();
  }
  
  Form() {
    this.attachmentForm = this.formBuilder.group({
      personalImage: ['', [Validators.required]],
      cardImage: ['',[Validators.required]],
      youthCenterCard: ['', [Validators.required]],
      proofAcademicEnrollment: ['',Validators.required],
      
    });
  }
}
