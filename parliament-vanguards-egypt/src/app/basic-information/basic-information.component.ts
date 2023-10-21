import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import governorateDataJson from '../../assets/governorate.json';
import typeDataJson from '../../assets/type.json';
import religionDataJson from '../../assets/religion.json';
import disabilityDataJson from '../../assets/disability.json';
import adminDepartmentDataJson from '../../assets/admin-department.json';
import entityDataJson from '../../assets/entity.json';
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
religionData:string[]=[];
disabilityData:string[]=[];
adminDepartmentData:string[]=[];
entityData:string[]=[];
ngOnInit(): void {
  this.Form();
  this.governorates();
  this.types();
  this.religions();
  this.disability();
  this.adminDepartment();
  this.entity();
}
Form() {
  this.basicInformationForm = this.formBuilder.group({
    name: ['', [Validators.required,Validators.pattern("^[أ-ي]*$")]],
    nationalId: ['',[Validators.required,Validators.minLength(14),Validators.pattern("^[0-9]*$")]],
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
types(){
  this.typeData=typeDataJson;
}
religions(){
  this.religionData=religionDataJson;
}
disability(){
  this.disabilityData=disabilityDataJson;
}
adminDepartment(){
  this.adminDepartmentData=adminDepartmentDataJson
}
entity(){
  this.entityData=entityDataJson
}
 
}
