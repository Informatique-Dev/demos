import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-educational-qualification',
  templateUrl: './educational-qualification.component.html',
  styleUrls: ['./educational-qualification.component.scss']
})
export class EducationalQualificationComponent implements OnInit {
  qualificationForm!:FormGroup;
  educationalLevelData:string[]=[];
  educationalLevels:string[]=[
    "الإبتدائية","المتوسطة","الثانوية","الجامعية"
]
  educationalYearData:string[]=[];
  educationalYears:string[]=[
    "2015","2016","2017","2018","2019","2020","2021","2022","2023"
];
  languageSkillsData:string[]=[];
  languageSkills:string[]=[
    "العربية","الإنجليزية","الألمانية","الفرنسية"
]
  languageLevelData:string[]=[];
  languageLevels:string[]=[
    "مبتدئ" ,"ما قبل المتوسط","متوسط","فوق المتوسط","متقدم"
];
  constructor(private formBuilder:FormBuilder ){}
  ngOnInit(): void {
    this.Form();
    this.educationalLevel();
    this.educationalYear();
    this.getLanguageSkills();
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
    this.educationalLevelData=this.educationalLevels
  }
  educationalYear(){
    this.educationalYearData=this.educationalYears;
  }
  getLanguageSkills(){
    this.languageSkillsData=this.languageSkills;
  }
  languageLevel(){
    this.languageLevelData=this.languageLevels;
  }
}