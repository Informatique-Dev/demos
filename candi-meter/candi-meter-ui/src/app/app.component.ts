import { Component, OnInit } from '@angular/core';
import { Profit } from './profit';
import { RepoService } from './repo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'candi-meter-ui';
  allProfits:Profit[] = [];

  constructor(private profitsRepo:RepoService){}
  getAllProfits():void{
    this.profitsRepo.getList().subscribe((results)=>{
       this.allProfits=results;
       console.log(this.allProfits)
    })
  }

  ngOnInit(): void {
    this.getAllProfits();
    console.log(this.allProfits)
  }
}
