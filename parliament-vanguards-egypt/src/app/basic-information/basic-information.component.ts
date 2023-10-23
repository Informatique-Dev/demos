import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import governorateDataJson from '../../assets/governorate.json';
@Component({
  selector: 'app-basic-information',
  templateUrl: './basic-information.component.html',
  styleUrls: ['./basic-information.component.scss'],
})
export class BasicInformationComponent implements OnInit {
constructor(private formBuilder:FormBuilder){}
basicInformationForm!:FormGroup;
governorateData:string[]=[];
typeData:string[]=[];
types:string[]=[
  "ذكر","أنثي"
];
religionData:string[]=[];
religions:string[]=[
  "مسلم","مسيحي"
];
disabilityData:string[]=[];
disabilitys:string[]=[
  "نعم","لا"
]
adminDepartmentData:string[]=[];
adminDepartments:string[]=[
  "أول أسيوط",
  "ثان أسيوط"
];
entityData:string[]=[];
entitys:string[]=[
  "أسيوط","قنا","القاهرة","الجيزة"
]
ngOnInit(): void {
  this.Form();
  this.governorates();
  this.getTypes();
  this.getReligions();
  this.disability();
  this.adminDepartment();
  this.entity();
}
Form() {
  this.basicInformationForm = this.formBuilder.group({
    name: ['', [Validators.required,Validators.pattern("^[\u0621-\u064A _-]+$")]],
    nationalId: ['',[Validators.required,Validators.minLength(14),Validators.maxLength(14),Validators.pattern("^[0-9]*$")]],
    entity: ['', [Validators.required]],
    governorate: ['',Validators.required],
    adminDepartment: ['', [Validators.required]],
    birthDate: ['',Validators.required],
    type: ['',Validators.required],
    religion:['',Validators.required],
    disability:['',Validators.required]
  });
}
governorates(){
  this.governorateData=governorateDataJson;
}
getTypes(){
  this.typeData=this.types;
}
getReligions(){
  this.religionData=this.religions;
}
disability(){
  this.disabilityData=this.disabilitys;
}
adminDepartment(){
  this.adminDepartmentData=this.adminDepartments;
}
entity(){
  this.entityData=this.entitys;
}
 
}
