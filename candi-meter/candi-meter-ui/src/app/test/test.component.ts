import { Component, OnInit } from '@angular/core';
import { Profit } from '../domain/model/test';
import { RepositoryService } from '../domain/rep';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  allTests:Profit[]=[];
  constructor(private testepo:RepositoryService) { }

  ngOnInit(): void {
    this.getAlltests()
  }
  getAlltests():void{
    this.testepo.getList().subscribe((results)=>{
       this.allTests=results;
       console.log(this.allTests)
    })
  }

}
