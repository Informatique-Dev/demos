import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import educationalLevelDataJson from '../../assets/educational-level.json';
import educationalYearDataJson from '../../assets/educational-year.json';
import languageSkillsDataJson from '../../assets/language-skills.json';
import languageLevelDataJson from '../../assets/language-level.json';
@Component({
  selector: 'app-educational-qualification',
  templateUrl: './educational-qualification.component.html',
  styleUrls: ['./educational-qualification.component.scss']
})
export class EducationalQualificationComponent implements OnInit {
  qualificationForm!:FormGroup;
  educationalLevelData:string[]=[];
  educationalYearData:string[]=[];
  languageSkillsData:string[]=[];
  languageLevelData:string[]=[];
  constructor(private formBuilder:FormBuilder ){}
  ngOnInit(): void {
    this.Form();
    this.educationalLevel();
    this.educationalYear();
    this.languageSkills();
    this.languageLevel();
  }
  
  Form() {
    this.qualificationForm = this.formBuilder.group({
      educationalLevel: ['', [Validators.required]],
      educationalYear: ['',[Validators.required]],
      schoolName: ['', [Validators.required]],
      languageSkills: ['',Validators.required],
      languageLevel:['',Validators.required],
      trainingCourses:['',Validators.required],
      
    });
  }

  educationalLevel(){
    this.educationalLevelData=educationalLevelDataJson
  }
  educationalYear(){
    this.educationalYearData=educationalYearDataJson;
  }
  languageSkills(){
    this.languageSkillsData=languageSkillsDataJson;
  }
  languageLevel(){
    this.languageLevelData=languageLevelDataJson;
  }
}